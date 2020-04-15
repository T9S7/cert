package com.aszz.cert.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.admin.dao.UserRoleDao;
import com.aszz.cert.admin.dao.UserRoleRelationDao;
import com.aszz.cert.admin.dto.UserResourceNode;
import com.aszz.cert.admin.dto.UserResourceParam;
import com.aszz.cert.admin.service.UserResourceService;
import com.aszz.cert.mapper.CertResourceMapper;
import com.aszz.cert.model.CertResource;
import com.aszz.cert.model.CertResourceExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * UserResourceService 实现类
 */
@Service
public class UserResourceServiceImpl implements UserResourceService {

    @Autowired
    private CertResourceMapper resourceMapper;

    @Autowired
    private UserRoleRelationDao userRoleRelationDao;


    @Override
    public int create(UserResourceParam userResourceParam) {
        CertResource certResource = new CertResource();
        BeanUtils.copyProperties(userResourceParam, certResource);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        certResource.setResourceStatus(0);
        certResource.setCreateUserId(userDetails.getUserId());
        certResource.setUpdateUserId(userDetails.getUserId());
        certResource.setCreateTime(new Date());
        certResource.setUpdateTime(new Date());
        return resourceMapper.insert(certResource);
    }

    @Override
    public int update(UserResourceParam userResourceParam) {
        CertResource certResource = new CertResource();
        BeanUtils.copyProperties(userResourceParam, certResource);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        certResource.setUpdateUserId(userDetails.getUserId());
        certResource.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(certResource);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        CertResource certResource = resourceMapper.selectByPrimaryKey(id);
        if (certResource == null) {
            return 0;
        }
        certResource.setResourceStatus(status);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        certResource.setUpdateUserId(userDetails.getUserId());
        certResource.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(certResource);

    }

    @Override
    public CertResource getItem(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CertResource> list(Integer resourceType, String resourceName, String resourcePath, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CertResourceExample example = new CertResourceExample();
        CertResourceExample.Criteria criteria = example.createCriteria();
//        if(resourceType!=null){
        criteria.andResourceTypeEqualTo(resourceType);
//        }
        if (resourcePath != null) {
            criteria.andResourcePathEqualTo(resourcePath);
        }
        if (StrUtil.isNotEmpty(resourceName)) {
            criteria.andResourceNameLike('%' + resourceName + '%');
        }
        criteria.andResourceStatusEqualTo(0);//启动中的资源
        return resourceMapper.selectByExample(example);
    }


    @Override
    public List<CertResource> listAll() {
        CertResourceExample example = new CertResourceExample();
        CertResourceExample.Criteria criteria = example.createCriteria();
        criteria.andResourceStatusEqualTo(0);//启动中的资源
        return resourceMapper.selectByExample(example);
    }


    //    public List<CertResource> getMenuResourceList(Long adminId) {
//        return userRoleRelationDao.getMenuResourceList(adminId);
//    }
//
    @Override
    public List<CertResource> getBtnResourceList(Long adminId) {
        return userRoleRelationDao.getBtnResourceList(adminId);
    }

    @Override
    public List<UserResourceNode> menuTreeList(Long userId) {
//        List<CertResource> resourcesList = resourceMapper.selectByExample(new CertResourceExample());
        List<CertResource> resourcesList = userRoleRelationDao.getMenuResourceList(userId);
        List<UserResourceNode> result = resourcesList.stream()
                .filter(resource -> resource.getParentId().equals(0L))
                .map(resource -> covert(resource, resourcesList)).collect(Collectors.toList());
        return result;
    }


//    @Override
//    public  List<UserResourceNode> btnTreeList(Long userId,Long menuId) {
////        List<CertResource> resourcesList = resourceMapper.selectByExample(new CertResourceExample());
//        List<CertResource> resourcesList = userRoleRelationDao.getBtnResourceList(userId);
//        List<UserResourceNode> result = resourcesList.stream()
//                .filter(resource -> resource.getParentId().equals(menuId))
//                .map(resource -> covert(resource,resourcesList)).collect(Collectors.toList());
//        return result;
//    }

    /**
     * 以层级结构返回用户资源
     */
    public List<UserResourceNode> rsTreeList(Long userId) {
        List<CertResource> rsMenuList = userRoleRelationDao.getMenuResourceList(userId);
        List<CertResource> rsBtnList = userRoleRelationDao.getBtnResourceList(userId);

        List<UserResourceNode> rsMenuNodeList = rsMenuList.stream()
                .filter(resource -> resource.getParentId().equals(0L))
                .map(resource -> covert(resource, rsMenuList)).collect(Collectors.toList());

        for (UserResourceNode userResourceNode : rsMenuNodeList) {
            List<UserResourceNode> rsBtnNodeList = rsMenuList.stream()
                    .filter(resource -> resource.getParentId().equals(userResourceNode.getId()))
                    .map(resource -> covert(resource, rsBtnList)).collect(Collectors.toList());
            userResourceNode.setChildren(rsBtnNodeList);
        }

        return rsMenuNodeList;

    }

    /**
     * 将权限转换为带有子级的权限对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private UserResourceNode covert(CertResource resource, List<CertResource> resourcesList) {
        UserResourceNode node = new UserResourceNode();
        BeanUtils.copyProperties(resource, node);
        List<UserResourceNode> children = resourcesList.stream()
                .filter(subResource -> subResource.getParentId().equals(resource.getId()))
                .map(subResource -> covert(subResource, resourcesList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }


}
