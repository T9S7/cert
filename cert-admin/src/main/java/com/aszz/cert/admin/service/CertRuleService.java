package com.aszz.cert.admin.service;


import com.aszz.cert.admin.dto.CertRuleParam;
import com.aszz.cert.model.CertRule;

import java.util.List;

/**
 * 证书规则信息Service
 */
public interface CertRuleService {
    /**
     * 添加规则
     */
    int addRule(CertRuleParam certRuleParam);

    /**
     * 删除指定规则
     */
    int delete(List<Long> ruleIds);

    /**
     * 修改指定规则信息
     */
    int update(Long id, CertRuleParam certRuleParam);

    /**
     * 修改规则状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 分页查询告警规则
     */
    List<CertRule> list(Long userId, Integer pageSize, Integer pageNum);
}
