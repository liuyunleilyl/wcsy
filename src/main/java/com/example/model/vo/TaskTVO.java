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

/**
 * @author liuly 
 */
@ApiModel("任务VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTVO {
    /**
     * 任务ID
     */
    @ApiModelProperty("任务ID")
    private String taskId;

    /**
     * 年份
     */
    @ApiModelProperty("年份")
    private String taskYear;

    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String taskName;

    /**
     * 任务完成标记（0：未完成；1：已经完成）
     */
    @ApiModelProperty("任务完成标记（0：未完成；1：已经完成）")
    private String wcbj;


}