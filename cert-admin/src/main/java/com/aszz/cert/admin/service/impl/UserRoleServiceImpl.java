package com.aszz.cert.admin.service.impl;

import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.admin.dao.UserRoleDao;
import com.aszz.cert.admin.dto.UserRoleParam;
import com.aszz.cert.admin.service.UserRoleService;
import com.aszz.cert.mapper.CertPrivilegeMapper;
import com.aszz.cert.mapper.CertResourceMapper;
import com.aszz.cert.mapper.CertRoleMapper;
import com.aszz.cert.model.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * UserRoleService 实现类
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private CertRoleMapper certRoleMapper;

    @Autowired
    private CertPrivilegeMapper certPrivilegeMapper;

    @Autowired
    private CertResourceMapper certResourceMapper;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public int create(UserRoleParam userRoleParam) {
        CertRole role = new CertRole();
        BeanUtils.copyProperties(userRoleParam, role);
        role.setRoleStatus(0);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        role.setCreateUserId(userDetails.getUserId());
        role.setUpdateUserId(userDetails.getUserId());
        int count = certRoleMapper.insert(role);
        if (count > 0) {
            return role.getId().intValue();
        }
        return 0;
    }

    @Override
    public int update(UserRoleParam userRoleParam) {
        Long id = userRoleParam.getId();
        CertRole role = certRoleMapper.selectByPrimaryKey(id);
        if (role == null) {
            return 0;
        }
        BeanUtils.copyProperties(userRoleParam, role);
        role.setUpdateTime(new Date());
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        role.setUpdateUserId(userDetails.getUserId());
        return certRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        CertRole role = certRoleMapper.selectByPrimaryKey(id);
        if (role == null) {
            return 0;
        }
        role.setRoleStatus(status);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        role.setUpdateUserId(userDetails.getUserId());
        role.setUpdateTime(new Date());
        return certRoleMapper.updateByPrimaryKeySelective(role);

    }

    @Override
    public int delete(List<Long> ids) {
        CertRoleExample example = new CertRoleExample();
        example.createCriteria().andIdIn(ids);
        return certRoleMapper.deleteByExample(example);
    }

    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        return 0;
    }

    @Override
    public List<CertRole> list() {
        CertRoleExample example = new CertRoleExample();
        example.createCriteria().andRoleStatusEqualTo(0);//启动中的角色
        return certRoleMapper.selectByExample(example);
    }

    @Override
    public List<CertRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CertRoleExample example = new CertRoleExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andRoleNameLike("%" + keyword + "%");
            example.createCriteria().andRoleStatusEqualTo(0);//启动中的角色
        }
        return certRoleMapper.selectByExample(example);
    }

    @Override
    public List<CertResource> listResource(Long roleId) {
        return userRoleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
//        CertPrivilegeExample example=new CertPrivilegeExample();
//        example.createCriteria().andPrivilegeMasterValueEqualTo(roleId);
//        certPrivilegeMapper.deleteByExample(example);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        Long userId = userDetails.getUserId();
        //批量插入新关系
        for (Long resourceId : resourceIds) {
            if (userRoleDao.getResourceByRoleIdAndRsId(roleId, resourceId) != null) {
                //已经把该资源授权给了该角色
                continue;
            } else {
                //获取资源相关信息
                CertResource certResource = certResourceMapper.selectByPrimaryKey(resourceId);
                if (certResource == null) {
                    //无相关资源
                    continue;
                }
                CertPrivilege certPrivilege = new CertPrivilege();
                certPrivilege.setPrivilegeMaster(1);
                certPrivilege.setPrivilegeMasterValue(roleId);
                certPrivilege.setPrivilegeResourceType(certResource.getResourceType());
                certPrivilege.setPrivilegeResourceId(resourceId);
                certPrivilege.setPrivilegeOperation(1);
                certPrivilege.setCreateUserId(userId);
                certPrivilege.setUpdateUserId(userId);
                certPrivilege.setCreateTime(new Date());
                certPrivilege.setUpdateTime(new Date());
                certPrivilegeMapper.insert(certPrivilege);
            }
        }
        return resourceIds.size();

    }

    @Override
    public int reAllocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
        CertPrivilegeExample example = new CertPrivilegeExample();
        example.createCriteria().andPrivilegeMasterValueEqualTo(roleId);
        certPrivilegeMapper.deleteByExample(example);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        Long userId = userDetails.getUserId();
        //批量插入新关系
        for (Long resourceId : resourceIds) {
            if (userRoleDao.getResourceByRoleIdAndRsId(roleId, resourceId) != null) {
                //已经把该资源授权给了该角色
                continue;
            } else {
                //获取资源相关信息
                CertResource certResource = certResourceMapper.selectByPrimaryKey(resourceId);
                if (certResource == null) {
                    //无相关资源
                    continue;
                }
                CertPrivilege certPrivilege = new CertPrivilege();
                certPrivilege.setPrivilegeMaster(1);
                certPrivilege.setPrivilegeMasterValue(roleId);
                certPrivilege.setPrivilegeResourceType(certResource.getResourceType());
                certPrivilege.setPrivilegeResourceId(resourceId);
                certPrivilege.setPrivilegeOperation(1);
                certPrivilege.setCreateUserId(userId);
                certPrivilege.setUpdateUserId(userId);
                certPrivilege.setCreateTime(new Date());
                certPrivilege.setUpdateTime(new Date());
                certPrivilegeMapper.insert(certPrivilege);
            }
        }
        return resourceIds.size();

    }
}
