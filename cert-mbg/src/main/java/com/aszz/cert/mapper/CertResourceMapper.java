package com.aszz.cert.mapper;

import com.aszz.cert.model.CertResource;
import com.aszz.cert.model.CertResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertResourceMapper {
    long countByExample(CertResourceExample example);

    int deleteByExample(CertResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertResource record);

    int insertSelective(CertResource record);

    List<CertResource> selectByExample(CertResourceExample example);

    CertResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertResource record, @Param("example") CertResourceExample example);

    int updateByExample(@Param("record") CertResource record, @Param("example") CertResourceExample example);

    int updateByPrimaryKeySelective(CertResource record);

    int updateByPrimaryKey(CertResource record);
}