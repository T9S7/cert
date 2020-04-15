package com.aszz.cert.admin.dao;


import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.model.CertResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 后台用户角色自定义Dao
 */
public interface UserRoleDao {

    /*
     根据角色id查询资源列表
     */
    List<CertResource> getResourceListByRoleId(@Param("roleId") Long roleId);


    /*
     根据角色id和资源id查询资源
 */
    Long getResourceByRoleIdAndRsId(@Param("roleId") Long roleId, @Param("rsId") Long rsId);


    /**
     * 从Security上下文中获取登录用户
     */
    public static AdminUserDetails getLoginUserFromContext() {
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        return userDetails;
    }

}
