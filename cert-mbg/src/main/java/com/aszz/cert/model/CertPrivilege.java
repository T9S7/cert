package com.aszz.cert.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CertPrivilege implements Serializable {
    @ApiModelProperty(value = "授权ID")
    private Long id;

    @ApiModelProperty(value = "被授权者0 User ,1 Role")
    private Integer privilegeMaster;

    @ApiModelProperty(value = "被授权者ID：UserID或RoleID")
    private Long privilegeMasterValue;

    @ApiModelProperty(value = "被授权资源类型 0.菜单 1.按钮")
    private Integer privilegeResourceType;

    @ApiModelProperty(value = "被授权资源ID")
    private Long privilegeResourceId;

    @ApiModelProperty(value = "0 'disabled',1“enable”")
    private Integer privilegeOperation;

    @ApiModelProperty(value = "创建用户ID")
    private Long createUserId;

    @ApiModelProperty(value = "修改用户ID")
    private Long updateUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrivilegeMaster() {
        return privilegeMaster;
    }

    public void setPrivilegeMaster(Integer privilegeMaster) {
        this.privilegeMaster = privilegeMaster;
    }

    public Long getPrivilegeMasterValue() {
        return privilegeMasterValue;
    }

    public void setPrivilegeMasterValue(Long privilegeMasterValue) {
        this.privilegeMasterValue = privilegeMasterValue;
    }

    public Integer getPrivilegeResourceType() {
        return privilegeResourceType;
    }

    public void setPrivilegeResourceType(Integer privilegeResourceType) {
        this.privilegeResourceType = privilegeResourceType;
    }

    public Long getPrivilegeResourceId() {
        return privilegeResourceId;
    }

    public void setPrivilegeResourceId(Long privilegeResourceId) {
        this.privilegeResourceId = privilegeResourceId;
    }

    public Integer getPrivilegeOperation() {
        return privilegeOperation;
    }

    public void setPrivilegeOperation(Integer privilegeOperation) {
        this.privilegeOperation = privilegeOperation;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", privilegeMaster=").append(privilegeMaster);
        sb.append(", privilegeMasterValue=").append(privilegeMasterValue);
        sb.append(", privilegeResourceType=").append(privilegeResourceType);
        sb.append(", privilegeResourceId=").append(privilegeResourceId);
        sb.append(", privilegeOperation=").append(privilegeOperation);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}