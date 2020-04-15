package com.aszz.cert.mapper;

import com.aszz.cert.model.CertRole;
import com.aszz.cert.model.CertRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertRoleMapper {
    long countByExample(CertRoleExample example);

    int deleteByExample(CertRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertRole record);

    int insertSelective(CertRole record);

    List<CertRole> selectByExample(CertRoleExample example);

    CertRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertRole record, @Param("example") CertRoleExample example);

    int updateByExample(@Param("record") CertRole record, @Param("example") CertRoleExample example);

    int updateByPrimaryKeySelective(CertRole record);

    int updateByPrimaryKey(CertRole record);
}