package com.aszz.cert.admin.service;

import com.aszz.cert.admin.dto.UserRoleParam;
import com.aszz.cert.model.CertResource;
import com.aszz.cert.model.CertRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理Service
 * Created by macro on 2018/9/30.
 */
public interface UserRoleService {
    /**
     * 添加角色
     */
    int create(UserRoleParam userRoleParam);

    /**
     * 修改角色信息
     */
    int update(UserRoleParam userRoleParam);

    /**
     * 修改角色状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     */
//    List<UmsPermission> getPermissionList(Long roleId);

    /**
     * 修改指定角色的权限
     */
    @Transactional
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取所有角色列表
     */
    List<CertRole> list();

    /**
     * 分页获取角色列表
     */
    List<CertRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
//    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
//    List<UmsMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<CertResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     */
//    @Transactional
//    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);

    /**
     * 给角色重新分配资源
     */
    @Transactional
    int reAllocResource(Long roleId, List<Long> resourceIds);
}
