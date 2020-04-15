package com.aszz.cert.admin.service.impl;

import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.admin.dao.UserRoleDao;
import com.aszz.cert.admin.dto.CertRuleParam;
import com.aszz.cert.admin.service.CertRuleService;
import com.aszz.cert.mapper.CertRuleMapper;
import com.aszz.cert.model.CertRule;
import com.aszz.cert.model.CertRuleExample;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * CertRuleService 实现类
 */
@Service
@Slf4j
public class CertRuleServiceImpl implements CertRuleService {
    @Autowired
    private CertRuleMapper certRuleMapper;

    @Override
    public int addRule(CertRuleParam certRuleParam) {
        CertRule certRule = new CertRule();
        BeanUtils.copyProperties(certRuleParam, certRule);
        certRule.setCreateTime(new Date());
        certRule.setUpdateTime(new Date());
        //管理员才能创建规则
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        certRule.setCreateUserId(userDetails.getUserId());
        certRule.setUpdateUserId(userDetails.getUserId());
        //查询是否有相同的规则
        CertRuleExample certRuleExample = new CertRuleExample();
        certRuleExample.createCriteria().andCertRuleNameEqualTo(certRuleParam.getCertRuleName());
        List<CertRule> certRuleList = certRuleMapper.selectByExample(certRuleExample);
        if (certRuleList.size() > 0) {
            return 0;
        }
        return certRuleMapper.insert(certRule);

    }

    @Override
    public int delete(List<Long> ruleIds) {
        CertRuleExample example = new CertRuleExample();
        example.createCriteria().andIdIn(ruleIds);
        return certRuleMapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, CertRuleParam certRuleParam) {
        CertRule certRule = certRuleMapper.selectByPrimaryKey(id);
        if (certRule == null) {
            return 0;
        }
        BeanUtils.copyProperties(certRuleParam, certRule);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        certRule.setUpdateUserId(userDetails.getUserId());
        certRule.setUpdateTime(new Date());
        return certRuleMapper.updateByPrimaryKeySelective(certRule);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        CertRule certRule = certRuleMapper.selectByPrimaryKey(id);
        if (certRule == null) {
            return 0;
        }
        certRule.setCertRuleStatus(status);
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        certRule.setUpdateUserId(userDetails.getUserId());
        certRule.setUpdateTime(new Date());
        return certRuleMapper.updateByPrimaryKeyWithBLOBs(certRule);
    }

    @Override
    public List<CertRule> list(Long userId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CertRuleExample certRuleExample = new CertRuleExample();
        certRuleExample.createCriteria().andCreateUserIdEqualTo(userId);
        return certRuleMapper.selectByExampleWithBLOBs(certRuleExample);
    }
}
