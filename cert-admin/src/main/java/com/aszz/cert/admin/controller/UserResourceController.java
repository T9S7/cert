package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.dto.UserResourceNode;
import com.aszz.cert.admin.dto.UserResourceParam;
import com.aszz.cert.admin.service.UserResourceService;
import com.aszz.cert.common.api.CommonPage;
import com.aszz.cert.common.api.CommonResult;
import com.aszz.cert.model.CertResource;
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
 * 用户资源管理.
 */
@Validated
@Controller
@Api(tags = "UserResourceController", description = "用户资源管理")
@RequestMapping("/resource")
public class UserResourceController {

    @Autowired
    private UserResourceService userResourceService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Valid @RequestBody UserResourceParam userResourceParam) {
        int count = userResourceService.create(userResourceParam);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Valid @RequestBody UserResourceParam userResourceParam) {
        int count = userResourceService.update(userResourceParam);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改资源状态")
    @RequestMapping(value = "/updateStatus/{rsId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long rsId, @RequestParam(value = "status") Integer status) {
        int count = userResourceService.updateStatus(rsId, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据ID获取资源详情")
    @RequestMapping(value = "/{rsId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CertResource> getItem(@PathVariable Long rsId) {
        CertResource certResource = userResourceService.getItem(rsId);
        return CommonResult.success(certResource);
    }

    @ApiOperation("根据ID删除后台资源")
    @RequestMapping(value = "/delete/{rsId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long rsId) {
        int count = userResourceService.delete(rsId);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertResource>> list(@RequestParam(required = false) Integer resourceType,
                                                       @RequestParam(required = false) String resourceName,
                                                       @RequestParam(required = false) String resourcePath,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CertResource> resourceList = userResourceService.list(resourceType, resourceName, resourcePath, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CertResource>> listAll() {
        List<CertResource> resourceList = userResourceService.listAll();
        return CommonResult.success(resourceList);
    }


    //    @ApiOperation("查询用户后台菜单资源")
//    @RequestMapping(value = "/{id}/menu", method = RequestMethod.GET)
//    @ResponseBody
//    public  CommonResult<List<CertResource>> getMenuItem(@PathVariable Long id) {
//        List<CertResource> resourceList = userResourceService.getMenuResourceList(id);
//        return CommonResult.success(resourceList);
//    }
//
    @ApiOperation("以平铺结构返回后台按钮资源")
    @RequestMapping(value = "/{userId}/btn", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CertResource>> getBtnItem(@PathVariable Long userId) {
        List<CertResource> resourceList = userResourceService.getBtnResourceList(userId);
        return CommonResult.success(resourceList);
    }

    @ApiOperation("以层级结构返回后台菜单资源")
    @RequestMapping(value = "/{userId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UserResourceNode>> menuTreeList(@PathVariable Long userId) {
        List<UserResourceNode> resourceNodeList = userResourceService.menuTreeList(userId);
        return CommonResult.success(resourceNodeList);
    }


//    @ApiOperation("以层级结构返回后台按钮资源")
//    @RequestMapping(value = "/{userId}/{menuId}/btn", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<UserResourceNode>> btnTreeList(@PathVariable Long userId,@PathVariable Long menuId) {
//        List<UserResourceNode> resourceNodeList = userResourceService.btnTreeList(userId,menuId);
//        return CommonResult.success(resourceNodeList);
//    }

    @ApiOperation("以层级结构返回后台所有资源")
    @RequestMapping(value = "/{userId}/resource", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UserResourceNode>> rsTreeList(@PathVariable Long userId) {
        List<UserResourceNode> resourceNodeList = userResourceService.rsTreeList(userId);
        return CommonResult.success(resourceNodeList);
    }
}
