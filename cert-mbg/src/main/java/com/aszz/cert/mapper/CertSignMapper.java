package com.aszz.cert.mapper;

import com.aszz.cert.model.CertSign;
import com.aszz.cert.model.CertSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertSignMapper {
    long countByExample(CertSignExample example);

    int deleteByExample(CertSignExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertSign record);

    int insertSelective(CertSign record);

    List<CertSign> selectByExample(CertSignExample example);

    CertSign selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertSign record, @Param("example") CertSignExample example);

    int updateByExample(@Param("record") CertSign record, @Param("example") CertSignExample example);

    int updateByPrimaryKeySelective(CertSign record);

    int updateByPrimaryKey(CertSign record);
}