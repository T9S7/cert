package com.aszz.cert.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CertInfo implements Serializable {
    @ApiModelProperty(value = "证书信息ID")
    private Long id;

    @ApiModelProperty(value = "证书ID")
    private Long certId;

    @ApiModelProperty(value = "证书连接次数")
    private Long certConnectNum;

    @ApiModelProperty(value = "证书在线时长，单位ms")
    private Long certOnlineTime;

    @ApiModelProperty(value = "修改用户ID")
    private Long updateUserId;

    @ApiModelProperty(value = "创建用户ID")
    private Long createUserId;

    @ApiModelProperty(value = "证书信息创建时间")
    private Date createTime;

    @ApiModelProperty(value = "证书信息更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "保留字段5")
    private String reserve1;

    @ApiModelProperty(value = "保留字段6")
    private String reserve2;

    @ApiModelProperty(value = "证书连接日志")
    private String certLog;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCertId() {
        return certId;
    }

    public void setCertId(Long certId) {
        this.certId = certId;
    }

    public Long getCertConnectNum() {
        return certConnectNum;
    }

    public void setCertConnectNum(Long certConnectNum) {
        this.certConnectNum = certConnectNum;
    }

    public Long getCertOnlineTime() {
        return certOnlineTime;
    }

    public void setCertOnlineTime(Long certOnlineTime) {
        this.certOnlineTime = certOnlineTime;
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

    public String getCertLog() {
        return certLog;
    }

    public void setCertLog(String certLog) {
        this.certLog = certLog;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", certId=").append(certId);
        sb.append(", certConnectNum=").append(certConnectNum);
        sb.append(", certOnlineTime=").append(certOnlineTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", reserve1=").append(reserve1);
        sb.append(", reserve2=").append(reserve2);
        sb.append(", certLog=").append(certLog);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}