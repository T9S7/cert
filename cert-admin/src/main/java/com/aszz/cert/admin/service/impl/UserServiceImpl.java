package com.aszz.cert.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.admin.dao.UserRoleDao;
import com.aszz.cert.admin.dao.UserRoleRelationDao;
import com.aszz.cert.admin.dto.UpdateUserPasswordParam;
import com.aszz.cert.admin.dto.UserParam;
import com.aszz.cert.admin.service.UserService;
import com.aszz.cert.mapper.CertUserMapper;
import com.aszz.cert.mapper.CertUserRoleMapper;
import com.aszz.cert.mapper.OperateLogMapper;
import com.aszz.cert.model.*;
import com.aszz.cert.security.component.RedisService;
import com.aszz.cert.security.util.JwtTokenUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * UserService 实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${redis.key.prefix.jwtToken}")
    private String REDIS_KEY_PREFIX_JWTOKEN;
    @Value("${redis.key.expire.jwtToken}")
    private Long REDIS_KEY_EXPIRE_JWTOKEN_SECONDS;

    @Value("${user.default.password}")
    private String defaultPassword;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CertUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private CertUserRoleMapper certUserRoleMapper;

    @Autowired
    private UserRoleRelationDao userRoleRelationDao;

    @Autowired
    private RedisService redisService;

    @Override
    public CertUser getAdminByUsername(String username) {
        CertUserExample example = new CertUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<CertUser> adminList = userMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }


    @Override
    public CertUser register(UserParam userParam) {
        CertUser user = new CertUser();
        BeanUtils.copyProperties(userParam, user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //设置默认密码
        user.setPassword(defaultPassword);
        //默认启动状态
        user.setStatus(0);
        //查询是否有相同用户名的用户
        CertUserExample example = new CertUserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<CertUser> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return user;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> tokenMap = new HashMap<>();
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtil.generateToken(userDetails);
            //生成token后，将token放入redis
//            if(redisService.get(REDIS_KEY_PREFIX_JWTOKEN)==null){
            redisService.remove(REDIS_KEY_PREFIX_JWTOKEN);
            redisService.set(REDIS_KEY_PREFIX_JWTOKEN, token);
            redisService.expire(REDIS_KEY_PREFIX_JWTOKEN, REDIS_KEY_EXPIRE_JWTOKEN_SECONDS);
//            }
            tokenMap.put("token", token);
            tokenMap.put("userId", adminUserDetails.getUserId());
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return tokenMap;
    }


    /**
     * 添加登录记录
     *
     * @param username 用户名
     */
//    private void insertLoginLog(String username) {
//        CertUser admin = getAdminByUsername(username);
//        OperateLog loginLog = new OperateLog();
//        loginLog.setOperatorId(admin.getId());
//        loginLog.setCreateTime(new Date());
//        loginLog.setUpdateTime(new Date());
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        operateLogMapper.insert(loginLog);
//    }


    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public CertUser getItem(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CertUser> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CertUserExample example = new CertUserExample();
        CertUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
//            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, UserParam userParam) {
        CertUser user = new CertUser();
        BeanUtils.copyProperties(userParam, user);
        user.setId(id);
        user.setUpdateTime(new Date());
        CertUser rawAdmin = userMapper.selectByPrimaryKey(id);
        if (rawAdmin.getPassword().equals(user.getPassword())) {
            //与原加密密码相同的不需要修改
//            user.setPassword(null);
        } else {
            //与原加密密码不同的需要加密修改
            if (StrUtil.isEmpty(user.getPassword())) {
                user.setPassword(null);
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count > 0) {
            List<Long> roleList = userParam.getRoleIds();
            if (roleList != null && roleList.size() > 0) {
                return updateRole(id, roleList);
            }
        }
        return 0;
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        CertUser certUser = userMapper.selectByPrimaryKey(id);
        if (certUser == null) {
            return 0;
        }
        certUser.setStatus(status);
        certUser.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(certUser);

    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long userId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        CertUserRoleExample certUserRoleExample = new CertUserRoleExample();
        certUserRoleExample.createCriteria().andUserIdEqualTo(userId);
        certUserRoleMapper.deleteByExample(certUserRoleExample);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<CertUserRole> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                CertUserRole certUserRole = new CertUserRole();
                certUserRole.setUserId(userId);
                certUserRole.setRoleId(roleId);
                //获取登录用户
                AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
                certUserRole.setCreateUserId(userDetails.getUserId());
                certUserRole.setUpdateUserId(userDetails.getUserId());
                certUserRole.setCreateTime(new Date());
                certUserRole.setUpdateTime(new Date());
                list.add(certUserRole);
            }
            userRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public List<CertRole> getRoleList(Long userId) {
        return userRoleRelationDao.getRoleList(userId);
    }

    @Override
    public List<CertResource> getResourceList(Long userId) {
        return userRoleRelationDao.getResourceList(userId);
    }

//    @Override
//    public int updatePermission(Long adminId, List<Long> permissionIds) {
//        return 0;
//    }

    @Override
    public int updatePassword(UpdateUserPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername())
                || StrUtil.isEmpty(param.getOldPassword())
                || StrUtil.isEmpty(param.getNewPassword())) {
            return -1;
        }
        CertUserExample example = new CertUserExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<CertUser> adminList = userMapper.selectByExample(example);
        if (CollUtil.isEmpty(adminList)) {
            return -2;
        }
        CertUser umsAdmin = adminList.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), umsAdmin.getPassword())) {
            return -3;
        }
        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        userMapper.updateByPrimaryKey(umsAdmin);
        return 1;
    }

    @Override
    public int resetPassword(Long userId) {
        CertUser certUser = userMapper.selectByPrimaryKey(userId);
        if (certUser == null) {
            return -1;
        }
        certUser.setPassword(defaultPassword);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(certUser.getPassword());
        certUser.setPassword(encodePassword);
        return userMapper.updateByPrimaryKey(certUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        CertUser admin = getAdminByUsername(username);
        if (admin != null) {
            List<CertResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

}
