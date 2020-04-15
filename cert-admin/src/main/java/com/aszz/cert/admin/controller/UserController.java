package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.admin.dao.UserRoleDao;
import com.aszz.cert.admin.dto.UpdateUserPasswordParam;
import com.aszz.cert.admin.dto.UserLoginParam;
import com.aszz.cert.admin.dto.UserParam;
import com.aszz.cert.admin.quartz.TaskEntity;
import com.aszz.cert.admin.service.ITaskService;
import com.aszz.cert.admin.service.UserService;
import com.aszz.cert.common.api.CommonPage;
import com.aszz.cert.common.api.CommonResult;
import com.aszz.cert.model.CertRole;
import com.aszz.cert.model.CertUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理.
 */
@Validated
@Controller
@Api(tags = "UserController", description = "用户管理")
@RequestMapping("/admin")
public class UserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService adminService;
//    @Autowired
//    private UserRoleService userRoleService;

    @Autowired
    private ITaskService iTaskService;

    @ApiOperation(value = "用户创建并分配角色")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CertUser> register(@Valid @RequestBody UserParam userParam) {
        CertUser userAdmin = adminService.register(userParam);
        if (userAdmin == null) {
            return CommonResult.registerFailed();
        }
        //用户创建成功后为其分配角色
        int count = adminService.updateRole(userAdmin.getId(), userParam.getRoleIds());
        if (count >= 0) {
            return CommonResult.success(userAdmin);
        }
        return CommonResult.registerFailed();
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
        Map<String, Object> tokenMap = adminService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        Object token = tokenMap.get("token");
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        tokenMap.put("tokenHead", tokenHead);
        Long userId = (Long) tokenMap.get("userId");
        if (iTaskService.findByTaskName("certExp:"+ userId) == null) {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setJobName("certExp:" + userId);
            taskEntity.setJobGroup("cert");
            taskEntity.setCronExpression("0 */30 * * * ? ");
            taskEntity.setJobDescription("证书过期信息检查定时任务");
            taskEntity.setJobClass("com.aszz.cert.admin.quartz.CertAlarmInfoTask");
            taskEntity.setUserId(userId);
            iTaskService.addTask(taskEntity);
        }
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        //获取登录用户
        AdminUserDetails userDetails = UserRoleDao.getLoginUserFromContext();
        TaskEntity taskEntity = iTaskService.findByTaskName("certExp:"+userDetails.getUserId());
        if (taskEntity != null) {
            iTaskService.deleteTask(taskEntity);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("根据用户名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<CertUser>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<CertUser> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CertUser> getItem(@PathVariable Long id) {
        CertUser admin = adminService.getItem(id);
        return CommonResult.success(admin);
    }

    @ApiOperation("修改指定用户信息并分配角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UserParam userParam) {
        int count = adminService.update(id, userParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestBody UpdateUserPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("重置指定用户密码")
    @RequestMapping(value = "/resetPassword/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult resetPassword(@PathVariable Long id) {
        int status = adminService.resetPassword(id);
        if (status > 0) {
            return CommonResult.success(status);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        int count = adminService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

//    @ApiOperation("给用户分配角色")
//    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
//                                   @RequestParam("roleIds") List<Long> roleIds) {
//        int count = adminService.updateRole(adminId, roleIds);
//        if (count >= 0) {
//            return CommonResult.success(count);
//        }
//        return CommonResult.failed();
//    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CertRole>> getRoleList(@PathVariable Long id) {
        List<CertRole> roleList = adminService.getRoleList(id);
        return CommonResult.success(roleList);
    }

}
