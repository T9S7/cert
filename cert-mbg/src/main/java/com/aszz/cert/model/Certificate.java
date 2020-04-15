package com.aszz.cert.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Certificate implements Serializable {
    @ApiModelProperty(value = "证书ID")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Integer projectId;

    @ApiModelProperty(value = "证书路径")
    private String certPath;

    @ApiModelProperty(value = "0 未激活 1激活 2过期 3异常")
    private Integer certStatus;

    @ApiModelProperty(value = "0.CA证书 1.服务端证书 2设备证书")
    private Integer certType;

    @ApiModelProperty(value = "发送设备数据对应的mqtt客户端ID")
    private String certMqttClientId;

    @ApiModelProperty(value = "证书关联ID")
    private String certDevId;

    @ApiModelProperty(value = "创建用户ID")
    private Long createUserId;

    @ApiModelProperty(value = "修改用户ID")
    private Long updateUserId;

    @ApiModelProperty(value = "证书失效时间")
    private Date certExpTime;

    @ApiModelProperty(value = "证书创建时间")
    private Date createTime;

    @ApiModelProperty(value = "证书修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "保留字段1")
    private String reserve1;

    @ApiModelProperty(value = "保留字段2")
    private String reserve2;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public Integer getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(Integer certStatus) {
        this.certStatus = certStatus;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertMqttClientId() {
        return certMqttClientId;
    }

    public void setCertMqttClientId(String certMqttClientId) {
        this.certMqttClientId = certMqttClientId;
    }

    public String getCertDevId() {
        return certDevId;
    }

    public void setCertDevId(String certDevId) {
        this.certDevId = certDevId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCertExpTime() {
        return certExpTime;
    }

    public void setCertExpTime(Date certExpTime) {
        this.certExpTime = certExpTime;
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

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectId=").append(projectId);
        sb.append(", certPath=").append(certPath);
        sb.append(", certStatus=").append(certStatus);
        sb.append(", certType=").append(certType);
        sb.append(", certMqttClientId=").append(certMqttClientId);
        sb.append(", certDevId=").append(certDevId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", certExpTime=").append(certExpTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", reserve1=").append(reserve1);
        sb.append(", reserve2=").append(reserve2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}