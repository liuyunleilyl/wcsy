package com.example.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("下拉框模板VO")
public class ValueAndLabelTemplate {
    /**
     * value值
     */
    @ApiModelProperty("value值")
    private String value;
    /**
     * name值
     */
    @ApiModelProperty("name值")
    private String label;
}
