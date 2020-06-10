package com.example.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author liuly 
 */
@TableName("task_t")
@Data
public class TaskT {
    /**
     * 任务ID
     */
    @TableId(value = "TASK_ID", type = IdType.UUID)
    private String taskId;

    /**
     * 年份
     */
    @TableField("TASK_YEAR")
    private String taskYear;

    /**
     * 任务名称
     */
    @TableField("TASK_NAME")
    private String taskName;

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
     * 年份
     * @return TASK_YEAR 年份
     */
    public String getTaskYear() {
        return taskYear;
    }

    /**
     * 年份
     * @param taskYear 年份
     */
    public void setTaskYear(String taskYear) {
        this.taskYear = taskYear;
    }

    /**
     * 任务名称
     * @return TASK_NAME 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 任务名称
     * @param taskName 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}