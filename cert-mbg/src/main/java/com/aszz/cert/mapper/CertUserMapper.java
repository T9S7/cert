package com.aszz.cert.mapper;

import com.aszz.cert.model.CertUser;
import com.aszz.cert.model.CertUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertUserMapper {
    long countByExample(CertUserExample example);

    int deleteByExample(CertUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertUser record);

    int insertSelective(CertUser record);

    List<CertUser> selectByExample(CertUserExample example);

    CertUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertUser record, @Param("example") CertUserExample example);

    int updateByExample(@Param("record") CertUser record, @Param("example") CertUserExample example);

    int updateByPrimaryKeySelective(CertUser record);

    int updateByPrimaryKey(CertUser record);
}