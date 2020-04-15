package com.aszz.cert.admin.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


/**
 * 资源参数
 */
@Getter
@Setter
public class UserResourceParam {

    @ApiModelProperty(value = "资源ID")
    private Long id;

    @ApiModelProperty(value = "父级资源ID")
    @NotNull
    private Long parentId;

    @ApiModelProperty(value = "资源名称")
    @NotEmpty(message = "资源名称不能为空")
    private String resourceName;

    @ApiModelProperty(value = "资源展示名称")
    @NotEmpty(message = "资源展示名称不能为空")
    private String resourceDisplayName;

    @ApiModelProperty(value = "资源路径")
    @NotEmpty(message = "资源路径不能为空")
    private String resourcePath;

    @ApiModelProperty(value = "0 菜单 1按钮-其他 2 按钮-查看 ")
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "2")
    private Integer resourceType;

    @ApiModelProperty(value = "资源描述")
    private String resourceDesc;

}
