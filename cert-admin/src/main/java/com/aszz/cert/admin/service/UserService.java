package com.aszz.cert.admin.service;

import com.aszz.cert.admin.dto.UpdateUserPasswordParam;
import com.aszz.cert.admin.dto.UserParam;
import com.aszz.cert.model.CertResource;
import com.aszz.cert.model.CertRole;
import com.aszz.cert.model.CertUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 后台管理员Service
 */
public interface UserService {
    /**
     * 根据用户名获取后台管理员
     */
    CertUser getAdminByUsername(String username);

    /**
     * 注册功能
     */
    CertUser register(UserParam userParam);

    /**
     * 登录功能
     *
     * @param name     用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    Map<String, Object> login(String name, String password);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    CertUser getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<CertUser> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UserParam userParam);

    /**
     * 修改用户状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long userId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     */
    List<CertRole> getRoleList(Long userId);

    /**
     * 获取指定用户的可访问资源
     */
    List<CertResource> getResourceList(Long userId);

    /**
     * 修改用户的+-权限
     */
//    @Transactional
//    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
//    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateUserPasswordParam param);

    /**
     * 重置密码
     */
    int resetPassword(Long userId);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

}
