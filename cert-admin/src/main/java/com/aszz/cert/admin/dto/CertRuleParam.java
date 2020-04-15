package com.aszz.cert.admin.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


/**
 * 证书规则参数
 */
@Getter
@Setter
public class CertRuleParam {
    @ApiModelProperty(value = "规则名称", required = true)
    @NotEmpty(message = "规则名称")
    private String certRuleName;

    @ApiModelProperty(value = "规则", required = true)
    @NotBlank(message = "规则不能为空")
    private String certRuleContent;

    @ApiModelProperty(value = "规则状态 0.启用 1.停用", required = true)
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "1")
    private Integer certRuleStatus;

    @ApiModelProperty(value = "规则描述")
    @NotEmpty(message = "规则描述")
    private String certRuleDesc;
}
