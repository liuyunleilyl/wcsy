package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author liuly 
 */
@ApiModel("管理员~新增任务请求VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskTVO {
    /**
     * 年份
     */
    @NotEmpty(message = "年份必填！")
    @ApiModelProperty(value = "年份",example = "2020")
    private String taskYear;

    /**
     * 任务名称
     */
    @NotEmpty(message = "任务名称必填！")
    @ApiModelProperty(value = "任务名称",example = "山东省济宁市地理国情监测")
    private String taskName;



}