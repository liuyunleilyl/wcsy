package com.example.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liuly 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("任务计划封装VO")
public class TaskPlanTListResVO {
    /**
     * 任务计划表id
     */
    @ApiModelProperty("任务计划表id")
    private String taskPlanId;

    /**
     * 任务ID
     */
    @ApiModelProperty("任务ID")
    private String taskId;

    /**
     * 作业员用户账号
     */
    @ApiModelProperty("作业员用户账号")
    private String userCode;

    /**
     * 检查员中文名
     */
    @ApiModelProperty("检查员中文名")
    private String jcyName;

    /**
     * 地理分区
     */
    @ApiModelProperty("地理分区")
    private String dlfq;

    /**
     * 采集
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty("采集")
    private Date cj;

    /**
     * 核查
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty("核查")
    private Date hc;

    /**
     * 编辑
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty("编辑")
    private Date bj;

    /**
     * 质检
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty("质检")
    private Date zj;

    /**
     * 二查
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty("二查")
    private Date ec;

    /**
     * 合库
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty("合库")
    private Date hk;

    /**
     * 上交
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty("上交")
    private Date sj;

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     */
    @ApiModelProperty("任务完成标记（0：未完成；1：已经完成）")
    private String wcbj;

    @ApiModelProperty("作业员真实姓名")
    private String userName;
    @ApiModelProperty("工作内容年份")
    private String taskYear;
    @ApiModelProperty("工作内容名称")
    private String taskName;
}