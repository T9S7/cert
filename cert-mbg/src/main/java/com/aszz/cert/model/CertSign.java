package com.aszz.cert.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CertSign implements Serializable {
    @ApiModelProperty(value = "序列号")
    private Long id;

    @ApiModelProperty(value = "证书id")
    private Long certId;

    @ApiModelProperty(value = "是否自签（1.自签；2.CA签发）")
    private Integer signCode;

    @ApiModelProperty(value = "证书状态(V 正常，R 吊销)")
    private String certStar;

    @ApiModelProperty(value = "Serial Number 签发序列号")
    private String sn;

    @ApiModelProperty(value = "证书信息")
    private String subject;

    @ApiModelProperty(value = "请求文件提交人")
    private String csruser;

    @ApiModelProperty(value = "签发时间")
    private Date signTime;

    @ApiModelProperty(value = "修改人")
    private String updateUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "备用字段1")
    private String reserve1;

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

    public Integer getSignCode() {
        return signCode;
    }

    public void setSignCode(Integer signCode) {
        this.signCode = signCode;
    }

    public String getCertStar() {
        return certStar;
    }

    public void setCertStar(String certStar) {
        this.certStar = certStar;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCsruser() {
        return csruser;
    }

    public void setCsruser(String csruser) {
        this.csruser = csruser;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", certId=").append(certId);
        sb.append(", signCode=").append(signCode);
        sb.append(", certStar=").append(certStar);
        sb.append(", sn=").append(sn);
        sb.append(", subject=").append(subject);
        sb.append(", csruser=").append(csruser);
        sb.append(", signTime=").append(signTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", reserve1=").append(reserve1);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}