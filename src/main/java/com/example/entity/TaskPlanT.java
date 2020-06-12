package com.example.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author liuly 
 */
@TableName("task_plan_t")
@Data
public class TaskPlanT {
    /**
     * 任务计划表id
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
    private Date cj;

    /**
     * 核查
     */
    @TableField("HC")
    private Date hc;

    /**
     * 编辑
     */
    @TableField("BJ")
    private Date bj;

    /**
     * 质检
     */
    @TableField("ZJ")
    private Date zj;

    /**
     * 二查
     */
    @TableField("EC")
    private Date ec;

    /**
     * 合库
     */
    @TableField("HK")
    private Date hk;

    /**
     * 上交
     */
    @TableField("SJ")
    private Date sj;

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     */
    @TableField("WCBJ")
    private String wcbj;

    public String getWcbj() {
        return wcbj;
    }

    public void setWcbj(String wcbj) {
        this.wcbj = wcbj;
    }

    /**
     * 任务计划表id
     * @return TASK_PLAN_ID 任务计划表id
     */
    public String getTaskPlanId() {
        return taskPlanId;
    }

    /**
     * 任务计划表id
     * @param taskPlanId 任务计划表id
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
    public Date getCj() {
        return cj;
    }

    /**
     * 采集
     * @param cj 采集
     */
    public void setCj(Date cj) {
        this.cj = cj;
    }

    /**
     * 核查
     * @return HC 核查
     */
    public Date getHc() {
        return hc;
    }

    /**
     * 核查
     * @param hc 核查
     */
    public void setHc(Date hc) {
        this.hc = hc;
    }

    /**
     * 编辑
     * @return BJ 编辑
     */
    public Date getBj() {
        return bj;
    }

    /**
     * 编辑
     * @param bj 编辑
     */
    public void setBj(Date bj) {
        this.bj = bj;
    }

    /**
     * 质检
     * @return ZJ 质检
     */
    public Date getZj() {
        return zj;
    }

    /**
     * 质检
     * @param zj 质检
     */
    public void setZj(Date zj) {
        this.zj = zj;
    }

    /**
     * 二查
     * @return EC 二查
     */
    public Date getEc() {
        return ec;
    }

    /**
     * 二查
     * @param ec 二查
     */
    public void setEc(Date ec) {
        this.ec = ec;
    }

    /**
     * 合库
     * @return HK 合库
     */
    public Date getHk() {
        return hk;
    }

    /**
     * 合库
     * @param hk 合库
     */
    public void setHk(Date hk) {
        this.hk = hk;
    }

    /**
     * 上交
     * @return SJ 上交
     */
    public Date getSj() {
        return sj;
    }

    /**
     * 上交
     * @param sj 上交
     */
    public void setSj(Date sj) {
        this.sj = sj;
    }


}