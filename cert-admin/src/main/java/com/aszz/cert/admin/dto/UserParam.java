package com.aszz.cert.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 用户登录参数
 */
@Getter
@Setter
public class UserParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

//    @ApiModelProperty(value = "密码", required = true)
//    @NotEmpty(message = "密码不能为空")
//    private String password;

    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @ApiModelProperty(value = "用户状态")
    private Integer status;

    @ApiModelProperty(value = "用户描述")
    private String note;

    @ApiModelProperty(value = "用户角色")
    @NotEmpty(message = "用户角色不能为空")
    private List<Long> roleIds;
}
