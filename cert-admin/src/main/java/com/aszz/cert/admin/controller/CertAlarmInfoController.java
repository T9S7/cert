package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.dto.CertAlarmDto;
import com.aszz.cert.admin.service.CertAlarmService;
import com.aszz.cert.common.api.CommonPage;
import com.aszz.cert.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 证书告警信息管理.
 */
@Validated
@Controller
@Api(tags = "CertAlarmInfoController", description = "证书告警信息管理")
@RequestMapping("/alarm")
public class CertAlarmInfoController {

    @Autowired
    private CertAlarmService certAlarmService;

    @ApiOperation("根据查询条件分页查询告警信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertAlarmDto>> getAlarmList(@RequestParam Long userID,
                                                               @RequestParam(required = false) String projectName,
                                                               @RequestParam(required = false) String devID,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<CertAlarmDto> certAlarmDtoList = certAlarmService.getAlarmList(userID, projectName, devID, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(certAlarmDtoList));
    }
}
