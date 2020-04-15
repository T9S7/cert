package com.aszz.cert.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CertRule implements Serializable {
    @ApiModelProperty(value = "证书规则ID")
    private Long id;

    @ApiModelProperty(value = "证书规则名称")
    private String certRuleName;

    @ApiModelProperty(value = "证书规则描述")
    private String certRuleDesc;

    @ApiModelProperty(value = "0.启用 1.停止")
    private Integer certRuleStatus;

    @ApiModelProperty(value = "修改用户ID")
    private Long updateUserId;

    @ApiModelProperty(value = "创建用户ID")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "证书规则")
    private String certRuleContent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCertRuleName() {
        return certRuleName;
    }

    public void setCertRuleName(String certRuleName) {
        this.certRuleName = certRuleName;
    }

    public String getCertRuleDesc() {
        return certRuleDesc;
    }

    public void setCertRuleDesc(String certRuleDesc) {
        this.certRuleDesc = certRuleDesc;
    }

    public Integer getCertRuleStatus() {
        return certRuleStatus;
    }

    public void setCertRuleStatus(Integer certRuleStatus) {
        this.certRuleStatus = certRuleStatus;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCertRuleContent() {
        return certRuleContent;
    }

    public void setCertRuleContent(String certRuleContent) {
        this.certRuleContent = certRuleContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", certRuleName=").append(certRuleName);
        sb.append(", certRuleDesc=").append(certRuleDesc);
        sb.append(", certRuleStatus=").append(certRuleStatus);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", certRuleContent=").append(certRuleContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}