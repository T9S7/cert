package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.dto.CertRuleParam;
import com.aszz.cert.admin.service.CertRuleService;
import com.aszz.cert.common.api.CommonPage;
import com.aszz.cert.common.api.CommonResult;
import com.aszz.cert.model.CertRule;
import com.aszz.cert.security.component.DynamicSecurityMetadataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 证书规则信息管理.
 */
@Validated
@Controller
@Api(tags = "CertRuleController", description = "证书规则信息管理")
@RequestMapping("/rule")
public class CertRuleController {

    @Autowired
    private CertRuleService certRuleService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("根据用户id分页查询规则")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertRule>> list(@RequestParam Long userID,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CertRule> certRuleList = certRuleService.list(userID, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(certRuleList));
    }

    @ApiOperation(value = "添加规则")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@Valid @RequestBody CertRuleParam certRuleParam) {
        int count = certRuleService.addRule(certRuleParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定规则信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody CertRuleParam certRuleParam) {
        int count = certRuleService.update(id, certRuleParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.registerFailed();
    }

    @ApiOperation("修改规则状态")
    @RequestMapping(value = "/updateStatus/{ruleId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long ruleId, @RequestParam(value = "status") Integer status) {
        int count = certRuleService.updateStatus(ruleId, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除规则")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam List<Long> ruleIds) {
        int count = certRuleService.delete(ruleIds);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
