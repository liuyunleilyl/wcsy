package com.example.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuly 
 */
@TableName("task_t")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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