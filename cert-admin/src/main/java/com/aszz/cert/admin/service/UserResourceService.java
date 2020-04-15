package com.aszz.cert.admin.service;

import com.aszz.cert.admin.dto.UserResourceNode;
import com.aszz.cert.admin.dto.UserResourceParam;
import com.aszz.cert.model.CertResource;

import java.util.List;

/**
 * 后台资源管理Service
 */
public interface UserResourceService {
    /**
     * 添加资源
     */
    int create(UserResourceParam userResourceParam);

    /**
     * 修改资源
     */
    int update(UserResourceParam userResourceParam);


    /**
     * 修改资源状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取资源详情
     */
    CertResource getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    List<CertResource> list(Integer resourceType, String resourceName, String resourcePath, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<CertResource> listAll();

    /**
     * 查询用户菜单资源
     */
//    List<CertResource> getMenuResourceList(Long adminId);


    /**
     * 查询用户按钮资源
     */
    List<CertResource> getBtnResourceList(Long adminId);


    /**
     * 以层级结构返回用户菜单资源
     */
    List<UserResourceNode> menuTreeList(Long userId);


    /**
     * 以层级结构返回用户按钮资源
     */
//    List<UserResourceNode> btnTreeList(Long userId,Long menuId);

    /**
     * 以层级结构返回用户资源
     */
    List<UserResourceNode> rsTreeList(Long userId);
}
