package com.aszz.cert.mapper;

import com.aszz.cert.model.CertProject;
import com.aszz.cert.model.CertProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertProjectMapper {
    long countByExample(CertProjectExample example);

    int deleteByExample(CertProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertProject record);

    int insertSelective(CertProject record);

    List<CertProject> selectByExample(CertProjectExample example);

    CertProject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertProject record, @Param("example") CertProjectExample example);

    int updateByExample(@Param("record") CertProject record, @Param("example") CertProjectExample example);

    int updateByPrimaryKeySelective(CertProject record);

    int updateByPrimaryKey(CertProject record);
}