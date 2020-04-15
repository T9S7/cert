package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.dto.CertInfoDto;
import com.aszz.cert.admin.service.CertInfoService;
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
 * 证书BI分析管理.
 */
@Validated
@Controller
@Api(tags = "CertBIController", description = "证书BI分析管理")
@RequestMapping("/bi")
public class CertBIController {

    @Autowired
    private CertInfoService certInfoService;

    @ApiOperation("证书统计分析信息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertInfoDto>> list(@RequestParam Long userID,
                                                      @RequestParam(required = false) Integer projectID,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CertInfoDto> certInfoList = certInfoService.list(userID, projectID, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(certInfoList));
    }


    @ApiOperation("查询证书统计详细信息")
    @RequestMapping(value = "/{certID}/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CertInfoDto> getItem(@PathVariable Long certID) {
        CertInfoDto certInfoDto = certInfoService.getCertInfo(certID);
        return CommonResult.success(certInfoDto);
    }
}
