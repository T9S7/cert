package com.aszz.cert.admin.service;


import com.aszz.cert.admin.dto.CertInfoDto;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 证书统计信息Service
 */
public interface CertInfoService {

    /*
     * 分页查询证书统计信息
     */
    List<CertInfoDto> list(Long userID, Integer projectID, Integer pageSize, Integer pageNum);

    /*
     * 根据证书ID证书统计信息
     */
    CertInfoDto getCertInfo(@Param("certID") Long certID);
}
