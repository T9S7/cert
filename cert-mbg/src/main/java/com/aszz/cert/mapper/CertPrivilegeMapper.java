package com.aszz.cert.mapper;

import com.aszz.cert.model.CertPrivilege;
import com.aszz.cert.model.CertPrivilegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertPrivilegeMapper {
    long countByExample(CertPrivilegeExample example);

    int deleteByExample(CertPrivilegeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertPrivilege record);

    int insertSelective(CertPrivilege record);

    List<CertPrivilege> selectByExample(CertPrivilegeExample example);

    CertPrivilege selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertPrivilege record, @Param("example") CertPrivilegeExample example);

    int updateByExample(@Param("record") CertPrivilege record, @Param("example") CertPrivilegeExample example);

    int updateByPrimaryKeySelective(CertPrivilege record);

    int updateByPrimaryKey(CertPrivilege record);
}