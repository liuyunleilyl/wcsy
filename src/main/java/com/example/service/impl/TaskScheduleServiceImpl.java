package com.example.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.common.excelutil.ExcelUtil;
import com.example.common.exceptionhandler.FailException;
import com.example.common.pagehelper.PageFactory;
import com.example.common.tool.ToolUtil;
import com.example.entity.TaskPlanT;
import com.example.entity.TaskScheduleT;
import com.example.entity.TaskT;
import com.example.entity.UserT;
import com.example.mapper.TaskPlanTMapper;
import com.example.mapper.TaskScheduleTMapper;
import com.example.mapper.TaskTMapper;
import com.example.mapper.UserTMapper;
import com.example.model.vo.TaskScheduleTResVO;
import com.example.model.vo.TaskScheduleTVO;
import com.example.service.TaskScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.StrUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: liuyl
 * @Date: 2020/6/10 10:19
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class TaskScheduleServiceImpl extends ServiceImpl<TaskScheduleTMapper, TaskScheduleT> implements TaskScheduleService {
    @Autowired
    private UserTMapper userTMapper;
    @Autowired
    private TaskTMapper taskTMapper;
    @Autowired
    private TaskPlanTMapper taskPlanTMapper;
    @Override
    public TaskScheduleTResVO newTaskScheduleInit(String userCode, String taskId,String dlfq) {
        TaskScheduleTResVO resVO = new TaskScheduleTResVO();
        List<TaskScheduleTResVO> taskScheduleTS = this.baseMapper.newTaskScheduleInit(userCode,taskId,dlfq);
        if(ToolUtil.isNotEmpty(taskScheduleTS)){
            resVO = taskScheduleTS.get(0);
        }else{//赋予默认值
            resVO = TaskScheduleTResVO.builder()
                    .userCode(userCode)
                    .taskId(taskId)
                    .build();
            List<UserT> users = userTMapper.selectList(
                    new EntityWrapper<UserT>()
                            .eq("USER_CODE", userCode)
            );
            TaskT taskT = taskTMapper.selectById(taskId);
            List<TaskPlanT> planList = taskPlanTMapper.selectList(
                    new EntityWrapper<TaskPlanT>()
                            .eq("TASK_ID", taskId)
                            .eq("USER_CODE", userCode)
                            .eq("dlfq",dlfq)
            );
            //赋值用户信息
            if(ToolUtil.isNotEmpty(users) && ToolUtil.isNotEmpty(users.get(0))){
                UserT userT = users.get(0);
                resVO.setUserName(userT.getUserName());
            }
            //赋值任务信息
            if(ToolUtil.isNotEmpty(taskT)){
                resVO.setTaskYear(taskT.getTaskYear());
                resVO.setTaskName(taskT.getTaskName());
            }
            //赋值任务信息
            if (ToolUtil.isNotEmpty(planList)){
                TaskPlanT taskPlanT = planList.get(0);
                resVO.setDlfq(taskPlanT.getDlfq());
                resVO.setJcyName(taskPlanT.getJcyName());
            }
        }
        return resVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void newTaskSchedule(TaskScheduleTVO taskScheduleTVO) {
        log.error("taskScheduleTVO{}",taskScheduleTVO.toString());
        TaskScheduleT insertTaskScheduleT
                = new TaskScheduleT();
        if(ToolUtil.isEmpty(taskScheduleTVO)){
            throw new FailException("0001","请传入正确数据！");
        }
        ToolUtil.copyProperties(taskScheduleTVO,insertTaskScheduleT);
        //进度表id
        String taskScheduleId = insertTaskScheduleT.getTaskScheduleId();
        //判断任务完成标记
        String wcbj = getWcbj(insertTaskScheduleT);//完成标记
        if(ToolUtil.isEmpty(taskScheduleId)){//新增
            /*Integer count = this.baseMapper.selectCount(
                    new EntityWrapper<TaskScheduleT>()
                            .eq("TASK_ID", taskScheduleTVO.getTaskId())
                            .eq("USER_CODE", taskScheduleTVO.getUserCode())
            );
            if(count != 0){
                throw new FailException("0001","该任务进度已经存在，请勿重复添加！");
            }*/
            insertTaskScheduleT.setWcbj(wcbj);
            //插入进度表
            this.baseMapper.insert(insertTaskScheduleT);
        }else{//修改
            insertTaskScheduleT.setWcbj(wcbj);
            //修改进度表
            this.baseMapper.updateById(insertTaskScheduleT);
        }
        //任务进度完成之后，同时修改<该人和该任务>的任务计划的完成标记位置为1
        if("1".equals(wcbj) && ToolUtil.isNotEmpty(insertTaskScheduleT.getTaskId())
                    && ToolUtil.isNotEmpty(insertTaskScheduleT.getUserCode())){//任务进度已经完成
            TaskPlanT build = TaskPlanT.builder()
                    .wcbj("1")
                    .build();
            taskPlanTMapper.update(build,
                    new EntityWrapper<TaskPlanT>()
                    .eq("USER_CODE",insertTaskScheduleT.getUserCode())
                    .eq("TASK_ID",insertTaskScheduleT.getTaskId())
            );
        }
        /**
         * 任务进度和任务计划有出入，出来一项任务，必须把该任务的任务计划全部分配完才可以填写任务进度，
         * 完成一项任务进度，同时更新任务计划，等该任务的所有任务计划完毕后，才可以修改任务表的wcbj
         */
        Integer count = this.taskPlanTMapper.selectCount(
                new EntityWrapper<TaskPlanT>()
                        .eq("TASK_ID", insertTaskScheduleT.getTaskId())
                        .eq("WCBJ", "0")
        );
        if(count == 0){//该任务的所有人的任务计划都完了，更新任务表wcbj为1，已完成
            TaskT taskT = new TaskT();
            taskT.setWcbj("1");
            taskT.setTaskId(insertTaskScheduleT.getTaskId());
            taskTMapper.updateById(taskT);
        }
    }

    @Override
    public Page<TaskScheduleTResVO> unDoneAllSchedule(String userCode) {
        Page<TaskScheduleTResVO> page = PageFactory.defaultPage();
        //由于前端做的数据分页，默认每页查询出来所有数据
        page.setSize(1000000);
        List<TaskScheduleTResVO> list = this.baseMapper.unDoneAllSchedule(userCode,page);
        page.setRecords(list);
        return page;
    }

    private String getWcbj(TaskScheduleT insertTaskScheduleT){
        String cj = insertTaskScheduleT.getCj();//采集
        String hc = insertTaskScheduleT.getHc();//核查
        String bj = insertTaskScheduleT.getBj();//编辑
        String zj = insertTaskScheduleT.getZj();//质检
        String ec = insertTaskScheduleT.getEc();//二查
        String hk = insertTaskScheduleT.getHk();//合库
        String sj = insertTaskScheduleT.getSj();//上交
        if("100".equals(cj) && "100".equals(hc) &&
           "100".equals(bj) && "100".equals(zj) &&
           "100".equals(ec) && "100".equals(hk) &&
           "100".equals(sj)){//完成
            return "1";
        }
        return "0";//未完成
    }

    @Override
    public void downloadUnDoneAllSchedule(String userCode,String taskId, HttpServletRequest request, HttpServletResponse response) {
        //数据源
        List<TaskScheduleTResVO> list = this.baseMapper.uploadUnDoneAllSchedule(userCode,taskId);
        //做导出逻辑
        downloadExcel(list,request,response);
    }

    /** 
     * @Author: liuyl
     * @Date: 2020/11/10 10:18
     * @Param: [list, request, response]
     * @Return: void
     * @Description: 根据数据源导出数据
     */
    private void downloadExcel(List<TaskScheduleTResVO> list, HttpServletRequest request, HttpServletResponse response) {
        //excel标题
        String[] title = {"市","县","作业员","检查员",
                "采集完成时间（%）","核查完成时间（%）","编辑完成时间（%）","一查完成时间（%）","二查完成时间（%）","合库完成时间（%）","提交完成时间（%）","任务名称"};
        //excel文件名
        String fileName = "基础测绘全流程管理系统-进度公示" + System.currentTimeMillis()+".xls";
        //sheet名
        String sheetName = "进度公示";
        String[][] content  = new String[list.size()+1][title.length];
        for (int i = 0; i < list.size(); i++) {
            TaskScheduleTResVO taskScheduleTResVO = list.get(i);
            if(ToolUtil.isNotEmpty(taskScheduleTResVO)){
                String dlfqSource = taskScheduleTResVO.getDlfq();
                if(StrUtil.isNotEmpty(dlfqSource)){
                    List<List<String>> lists = getCityAndCounty();
                    for (List<String> tempList:lists) {
                        for (String dlfq:tempList) {
                            if(dlfqSource.indexOf(dlfq) != -1){
                                content[i][0] = tempList.get(0);
                                content[i][1] = dlfq;
                            }
                        }
                    }
                }
                /*content[i][0] = "宿州市";
                content[i][1] = "埇桥区";*/
                content[i][2] = taskScheduleTResVO.getUserName();
                content[i][3] = taskScheduleTResVO.getJcyName();
                content[i][4] = taskScheduleTResVO.getCj();
                content[i][5] = taskScheduleTResVO.getHc();
                content[i][6] = taskScheduleTResVO.getBj();
                content[i][7] = taskScheduleTResVO.getZj();
                content[i][8] = taskScheduleTResVO.getEc();
                content[i][9] = taskScheduleTResVO.getHk();
                content[i][10] = taskScheduleTResVO.getSj();
                content[i][11] = taskScheduleTResVO.getTaskYear()+taskScheduleTResVO.getTaskName();
            }
        }
        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        try {
            ExcelUtil.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author: liuyl
     * @Date: 2020/11/10 16:39
     * @Param: []
     * @Return: java.util.List<java.util.List<java.lang.String>>
     * @Description:  写死市县
     */
    public List<List<String>> getCityAndCounty(){
        /**
         * 以下全是安徽省的市和县
         */
        //宿州市
        List<String> szs = Arrays.asList("宿州市","埇桥区", "砀山县", "萧县", "灵璧县", "泗县");
        //马鞍山市
        List<String> mass = Arrays.asList("马鞍山市","马鞍山市辖区", "当涂县", "含山县", "和县");
        //蚌埠市
        List<String> bfs = Arrays.asList("蚌埠市","蚌埠市辖区", "怀远县", "五河县", "固镇县");
        //宣城市
        List<String> xcs = Arrays.asList("宣城市","宣州区", "郎溪县", "泾县", "绩溪县", "旌德县", "宁国市", "广德市");
        List<List<String>> lists = Arrays.asList(szs, mass, bfs, xcs);
        return lists;
    }
}
