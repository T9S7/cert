package com.aszz.cert.admin.service.impl;

import com.aszz.cert.admin.dao.CertInfoDao;
import com.aszz.cert.admin.dto.CertInfoDto;
import com.aszz.cert.admin.service.CertInfoService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 证书统计信息 实现类
 */
@Service
@Slf4j
public class CertInfoServiceImpl implements CertInfoService {

    @Autowired
    private CertInfoDao certInfoDao;


    @Override
    public List<CertInfoDto> list(Long userID, Integer projectID, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return certInfoDao.getCertInfoList(userID, projectID);
    }

    @Override
    public CertInfoDto getCertInfo(Long certID) {
        return certInfoDao.getCertInfo(certID);
    }

}
