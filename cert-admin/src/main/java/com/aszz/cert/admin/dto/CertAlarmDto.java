package com.aszz.cert.admin.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * 证书告警信息Dto
 */
@Getter
@Setter
public class CertAlarmDto {
    private Long certId;
    private String certAlarmInfo;
    private Integer certAlarmType;
    private String devId;
    private String projectName;
    private String createUser;
    private String createTime;
    private String updateTime;

}
