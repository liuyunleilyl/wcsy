package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuly 
 */
@ApiModel("任务进度表封装VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskScheduleTResVO {
    /**
     * 任务进度表id
     */
    @ApiModelProperty("任务进度表id")
    private String taskScheduleId;

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
    @ApiModelProperty("采集")
    private String cj;

    /**
     * 核查
     */
    @ApiModelProperty("核查")
    private String hc;

    /**
     * 编辑
     */
    @ApiModelProperty("编辑")
    private String bj;

    /**
     * 质检
     */
    @ApiModelProperty("质检")
    private String zj;

    /**
     * 二查
     */
    @ApiModelProperty("二查")
    private String ec;

    /**
     * 合库
     */
    @ApiModelProperty("合库")
    private String hk;

    /**
     * 上交
     */
    @ApiModelProperty("上交")
    private String sj;

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     */
    @ApiModelProperty("任务进度表id")
    private String wcbj;

    @ApiModelProperty("作业员真实姓名")
    private String userName;
    @ApiModelProperty("工作内容年份")
    private String taskYear;
    @ApiModelProperty("工作内容名称")
    private String taskName;
}