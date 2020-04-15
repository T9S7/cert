package com.aszz.cert.admin.service;

import com.aszz.cert.admin.dto.CertAlarmDto;

import java.util.List;

/**
 * 证书告警信息Service
 */
public interface CertAlarmService {

    /**
     * 根据查询条件查询告警信息
     */
    List<CertAlarmDto> getAlarmList(Long userID, String projectName, String devId, Integer pageSize, Integer pageNum);
}
