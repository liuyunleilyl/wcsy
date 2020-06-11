package com.example.model.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@ApiModel("任务计划VO")
public class TaskPlanTVO {
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
    @ApiModelProperty("任务完成标记（0：未完成；1：已经完成）")
    private Date wcbj;
}