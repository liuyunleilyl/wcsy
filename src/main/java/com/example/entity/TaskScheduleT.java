package com.example.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author liuly 
 */
@TableName("task_schedule_t")
@Data
public class TaskScheduleT {
    /**
     * 任务进度表id
     */
    @TableId(value = "TASK_PLAN_ID", type = IdType.UUID)
    private String taskPlanId;

    /**
     * 任务ID
     */
    @TableField("TASK_ID")
    private String taskId;

    /**
     * 作业员用户账号
     */
    @TableField("USER_CODE")
    private String userCode;

    /**
     * 检查员中文名
     */
    @TableField("JCY_NAME")
    private String jcyName;

    /**
     * 地理分区
     */
    @TableField("DLFQ")
    private String dlfq;

    /**
     * 采集
     */
    @TableField("CJ")
    private String cj;

    /**
     * 核查
     */
    @TableField("HC")
    private String hc;

    /**
     * 编辑
     */
    @TableField("BJ")
    private String bj;

    /**
     * 质检
     */
    @TableField("ZJ")
    private String zj;

    /**
     * 二查
     */
    @TableField("EC")
    private String ec;

    /**
     * 合库
     */
    @TableField("HK")
    private String hk;

    /**
     * 上交
     */
    @TableField("SJ")
    private String sj;

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     */
    @TableField("WCBJ")
    private String wcbj;

    /**
     * 任务进度表id
     * @return TASK_PLAN_ID 任务进度表id
     */
    public String getTaskPlanId() {
        return taskPlanId;
    }

    /**
     * 任务进度表id
     * @param taskPlanId 任务进度表id
     */
    public void setTaskPlanId(String taskPlanId) {
        this.taskPlanId = taskPlanId;
    }

    /**
     * 任务ID
     * @return TASK_ID 任务ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 任务ID
     * @param taskId 任务ID
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 作业员用户账号
     * @return USER_CODE 作业员用户账号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 作业员用户账号
     * @param userCode 作业员用户账号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 检查员中文名
     * @return JCY_NAME 检查员中文名
     */
    public String getJcyName() {
        return jcyName;
    }

    /**
     * 检查员中文名
     * @param jcyName 检查员中文名
     */
    public void setJcyName(String jcyName) {
        this.jcyName = jcyName;
    }

    /**
     * 地理分区
     * @return DLFQ 地理分区
     */
    public String getDlfq() {
        return dlfq;
    }

    /**
     * 地理分区
     * @param dlfq 地理分区
     */
    public void setDlfq(String dlfq) {
        this.dlfq = dlfq;
    }

    /**
     * 采集
     * @return CJ 采集
     */
    public String getCj() {
        return cj;
    }

    /**
     * 采集
     * @param cj 采集
     */
    public void setCj(String cj) {
        this.cj = cj;
    }

    /**
     * 核查
     * @return HC 核查
     */
    public String getHc() {
        return hc;
    }

    /**
     * 核查
     * @param hc 核查
     */
    public void setHc(String hc) {
        this.hc = hc;
    }

    /**
     * 编辑
     * @return BJ 编辑
     */
    public String getBj() {
        return bj;
    }

    /**
     * 编辑
     * @param bj 编辑
     */
    public void setBj(String bj) {
        this.bj = bj;
    }

    /**
     * 质检
     * @return ZJ 质检
     */
    public String getZj() {
        return zj;
    }

    /**
     * 质检
     * @param zj 质检
     */
    public void setZj(String zj) {
        this.zj = zj;
    }

    /**
     * 二查
     * @return EC 二查
     */
    public String getEc() {
        return ec;
    }

    /**
     * 二查
     * @param ec 二查
     */
    public void setEc(String ec) {
        this.ec = ec;
    }

    /**
     * 合库
     * @return HK 合库
     */
    public String getHk() {
        return hk;
    }

    /**
     * 合库
     * @param hk 合库
     */
    public void setHk(String hk) {
        this.hk = hk;
    }

    /**
     * 上交
     * @return SJ 上交
     */
    public String getSj() {
        return sj;
    }

    /**
     * 上交
     * @param sj 上交
     */
    public void setSj(String sj) {
        this.sj = sj;
    }

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     * @return WCBJ 任务完成标记（0：未完成；1：已经完成）
     */
    public String getWcbj() {
        return wcbj;
    }

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     * @param wcbj 任务完成标记（0：未完成；1：已经完成）
     */
    public void setWcbj(String wcbj) {
        this.wcbj = wcbj;
    }
}