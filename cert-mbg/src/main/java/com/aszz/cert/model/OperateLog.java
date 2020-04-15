package com.aszz.cert.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class OperateLog implements Serializable {
    @ApiModelProperty(value = "操作ID")
    private Long id;

    @ApiModelProperty(value = "0 用户平台操作 1对证书的操作")
    private String opDesc;

    private String opUser;

    private Long opStartTime;

    private Long opSpendTime;

    private String opBasePath;

    private String opUri;

    private String opUrl;

    private String opIp;

    private String opMethod;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public Long getOpStartTime() {
        return opStartTime;
    }

    public void setOpStartTime(Long opStartTime) {
        this.opStartTime = opStartTime;
    }

    public Long getOpSpendTime() {
        return opSpendTime;
    }

    public void setOpSpendTime(Long opSpendTime) {
        this.opSpendTime = opSpendTime;
    }

    public String getOpBasePath() {
        return opBasePath;
    }

    public void setOpBasePath(String opBasePath) {
        this.opBasePath = opBasePath;
    }

    public String getOpUri() {
        return opUri;
    }

    public void setOpUri(String opUri) {
        this.opUri = opUri;
    }

    public String getOpUrl() {
        return opUrl;
    }

    public void setOpUrl(String opUrl) {
        this.opUrl = opUrl;
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getOpMethod() {
        return opMethod;
    }

    public void setOpMethod(String opMethod) {
        this.opMethod = opMethod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", opDesc=").append(opDesc);
        sb.append(", opUser=").append(opUser);
        sb.append(", opStartTime=").append(opStartTime);
        sb.append(", opSpendTime=").append(opSpendTime);
        sb.append(", opBasePath=").append(opBasePath);
        sb.append(", opUri=").append(opUri);
        sb.append(", opUrl=").append(opUrl);
        sb.append(", opIp=").append(opIp);
        sb.append(", opMethod=").append(opMethod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}