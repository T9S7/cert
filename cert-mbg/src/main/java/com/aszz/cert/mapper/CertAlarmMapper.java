package com.aszz.cert.mapper;

import com.aszz.cert.model.CertAlarm;
import com.aszz.cert.model.CertAlarmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertAlarmMapper {
    long countByExample(CertAlarmExample example);

    int deleteByExample(CertAlarmExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertAlarm record);

    int insertSelective(CertAlarm record);

    List<CertAlarm> selectByExampleWithBLOBs(CertAlarmExample example);

    List<CertAlarm> selectByExample(CertAlarmExample example);

    CertAlarm selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertAlarm record, @Param("example") CertAlarmExample example);

    int updateByExampleWithBLOBs(@Param("record") CertAlarm record, @Param("example") CertAlarmExample example);

    int updateByExample(@Param("record") CertAlarm record, @Param("example") CertAlarmExample example);

    int updateByPrimaryKeySelective(CertAlarm record);

    int updateByPrimaryKeyWithBLOBs(CertAlarm record);

    int updateByPrimaryKey(CertAlarm record);
}