package com.aszz.cert.admin.dto;


import lombok.Getter;
import lombok.Setter;


/**
 * 证书bi信息Dto
 */
@Getter
@Setter
public class CertInfoDto {
    private Long startTime;
    private Long endTime;

    private Long id;
    private Long certId;
    private String projectName;
    private Long certConnectNum;
    private Long certOnlineTime;
    private String createTime;
    private String updateTime;
    private String certExpTime;
    private String certLog;

}
