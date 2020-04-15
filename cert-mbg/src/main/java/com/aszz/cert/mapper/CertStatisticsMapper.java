package com.aszz.cert.mapper;

import com.aszz.cert.model.CertStatistics;
import com.aszz.cert.model.CertStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertStatisticsMapper {
    long countByExample(CertStatisticsExample example);

    int deleteByExample(CertStatisticsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertStatistics record);

    int insertSelective(CertStatistics record);

    List<CertStatistics> selectByExample(CertStatisticsExample example);

    CertStatistics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertStatistics record, @Param("example") CertStatisticsExample example);

    int updateByExample(@Param("record") CertStatistics record, @Param("example") CertStatisticsExample example);

    int updateByPrimaryKeySelective(CertStatistics record);

    int updateByPrimaryKey(CertStatistics record);
}