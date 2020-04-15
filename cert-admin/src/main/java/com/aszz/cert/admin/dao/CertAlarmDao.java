package com.aszz.cert.admin.dao;

import com.aszz.cert.admin.dto.CertAlarmDto;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CertAlarmDao {


    /**
     * 根据查询条件查询告警信息
     */
    List<CertAlarmDto> getAlarmList(@Param("userId") Long userId, @Param("projectName") String projectName, @Param("devId") String devId);

}
