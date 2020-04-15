package com.aszz.cert.admin.controller;

import com.aszz.cert.admin.service.CertService;
import com.aszz.cert.admin.service.ProjectService;
import com.aszz.cert.common.api.CommonPage;
import com.aszz.cert.common.api.CommonResult;
import com.aszz.cert.model.Certificate;
import com.aszz.cert.model.FileUpload;
import com.aszz.cert.security.component.DynamicSecurityMetadataSource;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.ResponseUtil;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@Api(tags = "CertController", description = "证书管理")
@RequestMapping("/cert")
public class CertController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private CertService certService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    private List<String> downLoadPaths = new ArrayList<String>();//存储选中文件的下载地址
    private OutputStream res;
    private ZipOutputStream zos;
    private String outPath;
    private String lessionIdStr;// 选中文件ID拼接的字符串
    private String fileName; //浏览器下载弹出框中显示的文件名


    @ApiOperation("新建证书")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestParam String devId,@RequestParam Long projectId){
        int count = projectService.add(devId,projectId);
        if(count > 0){
            return CommonResult.success(count);
        }
        else
             return CommonResult.failed();
    }


    @ApiOperation("证书删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam Long certId ){
        int count = projectService.certDel(certId);
        if(count > 0){
            return CommonResult.success(count);
        }
        else
            return CommonResult.failed();
    }


    @ApiOperation("单文件上传")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadFile(@RequestParam MultipartFile file,
//                                   @RequestParam String dev_id,
                                   @RequestParam Long projectId){
        int count=0;
        boolean t = fileCheck(file); //检验文件是否csr文件
        if(t) {
            count = projectService.uploadFile(file, projectId); //文件上传
        }
        if (count > 0) {

            return CommonResult.success(count);
        } else
            return CommonResult.failed();
    }

    // 批量上传
    @ApiOperation("批量上传")
    @RequestMapping(value = "/batchUploadFile", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult batchUploadFile(@RequestParam MultipartFile[] file,
                                        @RequestParam Long projectId){ //throws MyException
        StringBuffer buffer = new StringBuffer();
        for (MultipartFile multipartFile : file) {
            String filename = multipartFile.getOriginalFilename();//获得文件名
            int fileLength = filename.length();//获取文件名长度
            String dev_id = filename.substring(0,fileLength-4);//去除后缀名，或者设备id
            CommonResult str = uploadFile(multipartFile,projectId);
            buffer.append(str);
            buffer.append(",");
        }
        String all = buffer.substring(0, buffer.length() - 1);
        return CommonResult.success(all);
    }

    //开发阶段
    /**
     * @param filePath 下载证书绝对路径
     * @param  response
     * */
    @ApiOperation("单证书下载")
    @RequestMapping(value = "/fileDownload", method = RequestMethod.POST)
    @ResponseBody
    public void fileDownload(@RequestParam String filePath,                      //(value ="fileName") // @RequestParam String filePath,  //(value = "filePath")
                             HttpServletResponse response) throws Exception {
        projectService.fileDownload(response, filePath);
//        return ApiResponse.ofMessage(HttpStatus.SC_OK, "文件下载成功");
    }

//    @ApiOperation("批量证书下载")
//    @RequestMapping(value = "/batchDownloadFiles", method = RequestMethod.POST)
//    @ResponseBody
////    public void downLoadFiles(@RequestParam List<File> files,
//    public void batchDownloadFiles(HttpServletRequest request,
//                              HttpServletResponse response ) throws Exception {
//        projectService.batchDownloadFiles(request,response);
////        return ApiResponse.ofMessage(HttpStatus.SC_OK, "文件下载成功");
//    }
//    @ApiOperation("批量证书下载2")
//    @RequestMapping(value = "/downLoadFiles", method = RequestMethod.POST)
//    @ResponseBody
////    public void downLoadFiles(@RequestParam List<File> files,
//    public void downLoadFiles(
//            @RequestParam List<String> files,
////            @RequestParam List<String> filesPath,
//            HttpServletRequest request,
//            HttpServletResponse response ) throws Exception {
////        Integer[] idsInteger = CompressDownloadUtil.toIntegerArray(filesPath);
//
//        projectService.downLoadFiles(files,request,response);
////        return ApiResponse.ofMessage(HttpStatus.SC_OK, "文件下载成功");
//    }

    @ApiOperation("批量证书下载")
    @RequestMapping(value = "/batchDownloadFile", method = RequestMethod.POST)
    @ResponseBody
//    public void downLoadFiles(@RequestParam List<File> files,
    public void batchDownloadFiles(@RequestParam List<String> filePaths,
                                  HttpServletRequest request,
                                  HttpServletResponse res
                              ) throws IOException {
        certService.downloadFilesTest(filePaths,request,res);
//        return ApiResponse.ofMessage(HttpStatus.SC_OK, "文件下载成功");
    }


    /*
    /查询已签发证书信息
     */
    @ApiOperation("已签发证书列表")
    @RequestMapping(value = "/certList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Certificate>> certList(@RequestParam(required = false) Integer projectId,
                                                          @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Certificate> certificates = projectService.certList(projectId,pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(certificates));
    }

//    /*
///查询已签发证书信息
// */
//    @ApiOperation("已签发全证书列表")
//    @RequestMapping(value = "/ListAll", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<CommonPage<Certificate>> ListAll( ) {
//        List<Certificate> certificates = projectService.ListAll( );
//        return CommonResult.success(CommonPage.restPage(certificates));
//    }

    /*
     * 上传证书请求文件校验
     * */
    @ApiOperation("上传文件校验")
    @RequestMapping(value = "/fileCheck", method = RequestMethod.POST)
    @ResponseBody
    public boolean fileCheck(@RequestParam MultipartFile file ){
        boolean check =  projectService.fileCheck(file);
        if (check){
            return true;
        }else
            return false;
    }

    /*
     * 证书吊销
     * */
    @ApiOperation("证书吊销")
    @RequestMapping(value = "/certRevoke", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult certRevoke(@RequestParam Long id) throws Exception{
        int count =  projectService.certRevoke(id);
        if ( count > 0 ){
            return CommonResult.success(count);
        } else
            return CommonResult.failed();
    }


}