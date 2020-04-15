package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.dto.ProjectParam;
import com.aszz.cert.admin.service.ProjectService;
import com.aszz.cert.common.api.CommonPage;
import com.aszz.cert.common.api.CommonResult;
import com.aszz.cert.model.CertProject;
import com.aszz.cert.security.component.DynamicSecurityMetadataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 2020/3/4.
 */

/**
 * 后台证书管理.
 */
@Controller
@Api(tags = "ProjectController", description = "项目管理")
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;


    @ApiOperation(value = "新建项目")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Valid @RequestBody ProjectParam project) {
        int count = projectService.create(project);
        if (count > 0) {
            return CommonResult.success(count);
        } else
            return CommonResult.failed();
    }

    @ApiOperation(value = "批量删除项目")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam List<Long> projectIds) {
        int count = projectService.delete(projectIds);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(count);
        } else
            return CommonResult.failed();
    }

    @ApiOperation(value = "根据ID修改项目")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Valid @RequestBody ProjectParam project) {
        int count = projectService.update(project);
        if (count > 0) {
            return CommonResult.success(count);
        } else
            return CommonResult.failed();
    }

    //    @ApiOperation(value = "新建项目")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult create(@RequestBody CertProject project) {
//        int count = projectService.create(project);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else
//            return CommonResult.failed();
//    }
//
//    @ApiOperation(value = "根据ID删除项目")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult delete(@PathVariable Long id) {
//        int count = projectService.delete(id);
//        dynamicSecurityMetadataSource.clearDataSource();
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else
//            return CommonResult.failed();
//    }
//
//    @ApiOperation(value = "根据ID修改项目")
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult update(@PathVariable Long id, @RequestBody CertProject project) {
//        int count = projectService.update(id, project);
//        if (count > 0) {
//            return CommonResult.success(count);
//        } else
//            return CommonResult.failed();
//    }
    @ApiOperation("根据ID获取项目详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CertProject> getItem(@PathVariable Long id) {
        CertProject certproject = projectService.getProject(id);
        return CommonResult.success(certproject);
    }

    @ApiOperation("查询所有项目信息")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CertProject>> listAll() {
        List<CertProject> projectList = projectService.listAll();
        return CommonResult.success(projectList);
    }

    @ApiOperation("分页模糊查询用户名下所有项目")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertProject>> list(@RequestParam Long userId,        //用户id
                                                      @RequestParam(required = false) String projectName,
                                                      @RequestParam(required = false) String projectType,
                                                      @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CertProject> projectList = projectService.list(userId, projectName, projectType, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(projectList));
    }
}
