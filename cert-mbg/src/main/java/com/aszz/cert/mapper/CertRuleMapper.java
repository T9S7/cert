package com.aszz.cert.mapper;

import com.aszz.cert.model.CertRule;
import com.aszz.cert.model.CertRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertRuleMapper {
    long countByExample(CertRuleExample example);

    int deleteByExample(CertRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertRule record);

    int insertSelective(CertRule record);

    List<CertRule> selectByExampleWithBLOBs(CertRuleExample example);

    List<CertRule> selectByExample(CertRuleExample example);

    CertRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertRule record, @Param("example") CertRuleExample example);

    int updateByExampleWithBLOBs(@Param("record") CertRule record, @Param("example") CertRuleExample example);

    int updateByExample(@Param("record") CertRule record, @Param("example") CertRuleExample example);

    int updateByPrimaryKeySelective(CertRule record);

    int updateByPrimaryKeyWithBLOBs(CertRule record);

    int updateByPrimaryKey(CertRule record);
}