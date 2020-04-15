package com.aszz.cert.admin.dao;


import com.aszz.cert.admin.dto.CertInfoDto;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 证书统计信息自定义Dao
 */
public interface CertInfoDao {

    /**
     * 根据用户id和项目id查询统计信息列表
     */
    List<CertInfoDto> getCertInfoList(@Param("userID") Long userID, @Param("projectID") Integer projectID);

    /*
     * 根据证书ID证书统计信息
     */
    CertInfoDto getCertInfo(@Param("certID") Long certID);
}
