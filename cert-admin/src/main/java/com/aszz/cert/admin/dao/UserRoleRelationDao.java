package com.aszz.cert.admin.dao;

import com.aszz.cert.model.CertResource;
import com.aszz.cert.model.CertRole;
import com.aszz.cert.model.CertUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 */
public interface UserRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<CertUserRole> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<CertRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
//    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
//    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

    /**
     * 根据用户id获取用户所有可访问资源
     */
    List<CertResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有菜单可访问资源
     */
    List<CertResource> getMenuResourceList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有按钮可访问资源
     */
    List<CertResource> getBtnResourceList(@Param("adminId") Long adminId);

}
