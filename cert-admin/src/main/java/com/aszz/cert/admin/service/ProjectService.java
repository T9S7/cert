package com.aszz.cert.admin.service;


import com.aszz.cert.admin.dto.ProjectParam;
import com.aszz.cert.model.CertProject;
import com.aszz.cert.model.Certificate;
import com.aszz.cert.model.FileUpload;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2020/3/4.
 */
public interface ProjectService {
    /*
    * 新建项目
    * */
    int create(ProjectParam project);

    /**
     * 根据ID删除项目
     */
    int delete(List<Long> projectIds);

    /**
     * 根据ID修改项目
     */
    int update(ProjectParam project);

    /**
     * 获取资源详情
     */
    CertProject getProject(Long id);

    /**
     * 查询所有项目信息
     */
    List<CertProject> listAll();

    /**
     * 分页查询项目信息
     */
    List<CertProject> list(Long userid, String projectName, String projectType, Integer pageSize, Integer pageNum);

    /*
    *csr 单证书请求文件上传
    */
    @Mapper
    int uploadFile(MultipartFile csrname, Long projectID);



    /*
     *crt 单证书下载
     */
  void fileDownload(HttpServletResponse response, String fileUrl)throws IOException;

    /*
    *已签发证书展示信息
    * */

    List<Certificate> certList(Integer projectId,Integer pageSize,Integer pageNum);


//    /*
//    *
//    * */
//    List<Certificate> ListAll();

    /*
    *上传文件校验（判断是否csr文件）
    **/
    boolean fileCheck(MultipartFile file);

    /*
     *已上传文件分页展示列表
     * */
    List<FileUpload> fileList(Long projectId, Integer pageSize, Integer pageNum);

    HttpServletResponse downLoadFiles(List<String> filesPath,HttpServletRequest request,HttpServletResponse response)throws Exception;
//    void downloadZip (HttpServletResponse response,
//                      String zipFilename,
//                      List<byte[]> contentList,
//                      List<String> filenameList) throws IOException;
    void batchDownloadFiles(HttpServletRequest request,HttpServletResponse response);

    /*
    * 新建证书
    * **/
    int add(String devId,Long projectId);

    /*
    * 证书删除
    * */
    int certDel(Long certId);

    /*
    * 证书吊销
    * */
    int certRevoke(Long certId) throws Exception;
}
