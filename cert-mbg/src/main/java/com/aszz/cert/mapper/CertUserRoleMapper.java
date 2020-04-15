package com.aszz.cert.mapper;

import com.aszz.cert.model.CertUserRole;
import com.aszz.cert.model.CertUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertUserRoleMapper {
    long countByExample(CertUserRoleExample example);

    int deleteByExample(CertUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertUserRole record);

    int insertSelective(CertUserRole record);

    List<CertUserRole> selectByExample(CertUserRoleExample example);

    CertUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertUserRole record, @Param("example") CertUserRoleExample example);

    int updateByExample(@Param("record") CertUserRole record, @Param("example") CertUserRoleExample example);

    int updateByPrimaryKeySelective(CertUserRole record);

    int updateByPrimaryKey(CertUserRole record);
}