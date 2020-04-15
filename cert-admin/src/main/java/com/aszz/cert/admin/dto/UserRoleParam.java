package com.aszz.cert.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 角色参数
 */
@Getter
@Setter
public class UserRoleParam {

    @ApiModelProperty(value = "角色ID")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    @ApiModelProperty(value = "授权给角色的资源")
    @NotEmpty(message = "授权给角色的资源不能为空")
    List<Long> resourceIds;
}
