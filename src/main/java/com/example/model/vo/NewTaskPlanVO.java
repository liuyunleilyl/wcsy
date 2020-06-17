package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author liuly 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("管理员-新增任务计划VO")
public class NewTaskPlanVO {
    /**
     * 任务计划表id
     */
    /*@ApiModelProperty("任务计划表id")
    private String taskPlanId;*/

    /**
     * 任务ID
     */
    /*@ApiModelProperty("任务ID")
    private String taskId;*/

    /**
     * 作业员用户账号
     */
    @NotEmpty(message = "作业员用户账号必填")
    @ApiModelProperty(value = "作业员用户账号",example = "liuyl")
    private String userCode;

    /**
     * 检查员中文名
     */
    @ApiModelProperty(value = "检查员中文名",example = "码云")
    private String jcyName;

    /**
     * 地理分区
     */
    @ApiModelProperty(value = "地理分区",example = "山东济宁")
    private String dlfq;

    /**
     * 采集
     */
    @ApiModelProperty("采集")
    private Date cj;

    /**
     * 核查
     */
    @ApiModelProperty("核查")
    private Date hc;

    /**
     * 编辑
     */
    @ApiModelProperty("编辑")
    private Date bj;

    /**
     * 质检
     */
    @ApiModelProperty("质检")
    private Date zj;

    /**
     * 二查
     */
    @ApiModelProperty("二查")
    private Date ec;

    /**
     * 合库
     */
    @ApiModelProperty("合库")
    private Date hk;

    /**
     * 上交
     */
    @ApiModelProperty("上交")
    private Date sj;

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     */
    /*@ApiModelProperty("任务完成标记（0：未完成；1：已经完成）")
    private String wcbj;*/

    /*@ApiModelProperty("作业员真实姓名")
    private String userName;*/
    @NotEmpty(message = "任务年份必填！")
    @ApiModelProperty(value = "工作内容年份",example = "2020")
    private String taskYear;
    @NotEmpty(message = "任务内容必填")
    @ApiModelProperty(value = "工作内容名称",example = "马鞍山市地理国情监测")
    private String taskName;
}