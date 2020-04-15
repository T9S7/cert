package com.aszz.cert.admin.service.impl;

import com.aszz.cert.admin.dao.CertAlarmDao;
import com.aszz.cert.admin.dto.CertAlarmDto;
import com.aszz.cert.admin.service.CertAlarmService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 证书告警信息实现类
 */
@Service
@Slf4j
public class CertAlarmServiceImpl implements CertAlarmService {
    @Autowired
    private CertAlarmDao certAlarmDao;


    @Override
    public List<CertAlarmDto> getAlarmList(Long userID, String projectName, String devId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return certAlarmDao.getAlarmList(userID, projectName, devId);
    }
}
