package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author liuly 
 */
@ApiModel("任务进度表VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskScheduleTVO {
    /**
     * 任务进度表id
     */
    @ApiModelProperty(value = "任务进度表id",example = "efecdbb0c0974665a4eaf5e1a5ce4a41")
    private String taskScheduleId;

    /**
     * 任务ID
     */
    @NotEmpty(message = "任务ID不能为空!!")
    @ApiModelProperty(value = "任务ID",example = "1")
    private String taskId;

    /**
     * 作业员用户账号
     */
    @NotEmpty(message = "作业员用户账号不能为空!!")
    @ApiModelProperty(value = "作业员用户账号",example = "liuyl")
    private String userCode;

    /**
     * 检查员中文名
     */
    @ApiModelProperty(value = "检查员中文名",example = "马云")
    private String jcyName;

    /**
     * 地理分区
     */
    @ApiModelProperty(value = "地理分区",example = "山东")
    private String dlfq;

    /**
     * 采集
     */
    @Min(0)
    @Max(100)
    @ApiModelProperty(value = "采集",example = "100")
    private String cj = "0";

    /**
     * 核查
     */
    @Min(0)
    @Max(100)
    @ApiModelProperty(value = "核查",example = "100")
    private String hc = "0";

    /**
     * 编辑
     */
    @Min(0)
    @Max(100)
    @ApiModelProperty(value = "编辑",example = "100")
    private String bj = "0";

    /**
     * 质检
     */
    @Min(0)
    @Max(100)
    @ApiModelProperty(value = "质检",example = "100")
    private String zj = "0";

    /**
     * 二查
     */
    @Min(0)
    @Max(100)
    @ApiModelProperty(value = "二查",example = "100")
    private String ec = "0";

    /**
     * 合库
     */
    @Min(0)
    @Max(100)
    @ApiModelProperty(value = "合库",example = "100")
    private String hk = "0";

    /**
     * 上交
     */
    @Min(0)
    @Max(100)
    @ApiModelProperty(value = "上交",example = "100")
    private String sj = "0";

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     */
    @ApiModelProperty(value = "任务完成标记",example = "0")
    private String wcbj = "0";

}