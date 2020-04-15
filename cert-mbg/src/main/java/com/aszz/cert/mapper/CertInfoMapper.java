package com.aszz.cert.mapper;

import com.aszz.cert.model.CertInfo;
import com.aszz.cert.model.CertInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertInfoMapper {
    long countByExample(CertInfoExample example);

    int deleteByExample(CertInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertInfo record);

    int insertSelective(CertInfo record);

    List<CertInfo> selectByExampleWithBLOBs(CertInfoExample example);

    List<CertInfo> selectByExample(CertInfoExample example);

    CertInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertInfo record, @Param("example") CertInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CertInfo record, @Param("example") CertInfoExample example);

    int updateByExample(@Param("record") CertInfo record, @Param("example") CertInfoExample example);

    int updateByPrimaryKeySelective(CertInfo record);

    int updateByPrimaryKeyWithBLOBs(CertInfo record);

    int updateByPrimaryKey(CertInfo record);
}