package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.dto.UserRoleParam;
import com.aszz.cert.admin.service.UserRoleService;
import com.aszz.cert.common.api.CommonPage;
import com.aszz.cert.common.api.CommonResult;
import com.aszz.cert.model.CertResource;
import com.aszz.cert.model.CertRole;
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
 * 后台用户角色管理
 */
@Validated
@Controller
@Api(tags = "UserRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;


    @ApiOperation("添加角色并赋权")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Valid @RequestBody UserRoleParam userRoleParam) {
        int insertId = userRoleService.create(userRoleParam);
        if (insertId > 0) {
            //添加角色成功后赋权
            int count = userRoleService.allocResource(new Long(insertId), userRoleParam.getResourceIds());
            if (count > 0) {
                return CommonResult.success(count);
            }
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改角色并重新赋权")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Valid @RequestBody UserRoleParam userRoleParam) {
        int count = userRoleService.update(userRoleParam);
        if (count > 0) {
            //修改角色成功后赋权
            int acount = userRoleService.reAllocResource(userRoleParam.getId(), userRoleParam.getResourceIds());
            if (acount > 0) {
                return CommonResult.success(count);
            }
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CertRole>> listAll() {
        List<CertRole> roleList = userRoleService.list();
        return CommonResult.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CertRole> roleList = userRoleService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @ApiOperation("修改角色状态")
    @RequestMapping(value = "/updateStatus/{roleId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long roleId, @RequestParam(value = "status") Integer status) {
        int count = userRoleService.updateStatus(roleId, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色相关资源")
    @RequestMapping(value = "/listResource/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CertResource>> listResource(@PathVariable Long roleId) {
        List<CertResource> roleList = userRoleService.listResource(roleId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("给角色分配资源")
    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult allocResource(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = userRoleService.allocResource(roleId, resourceIds);
        return CommonResult.success(count);
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam List<Long> roleIds) {
        int count = userRoleService.delete(roleIds);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
