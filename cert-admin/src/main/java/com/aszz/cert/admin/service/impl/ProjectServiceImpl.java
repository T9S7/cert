package com.aszz.cert.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.admin.bo.FTP.ZipUtils;
import com.aszz.cert.admin.dto.ProjectParam;
import com.aszz.cert.admin.service.ProjectService;
import com.aszz.cert.mapper.*;
import com.aszz.cert.model.*;
import com.github.pagehelper.PageHelper;
import com.google.api.client.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
//import com.aszz.cert.model.CertSignExample;

/**
 * Created by User on 2020/3/4.
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private CertProjectMapper certProjectMapper;

    @Autowired
    private CertificateMapper certificateMapper;

//    @Autowired
//    private CertSignExample certSignExample;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Autowired
    private CertSignMapper certSignMapper;

    //证书不同文件存放路径
    @Value("${certpath.csr}")
    private String csrpath;

    @Value("${certpath.crt}")
    private String crtpath;

    @Value("${certpath.pem}")
    private String pemPath;

    @Override
    public int create(ProjectParam projectinfo) {
        CertProject project = new CertProject();
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        BeanUtils.copyProperties(projectinfo,project);
        project.setCreateUserId(userId);
        project.setUserId(userId);
        project.setCreateTime(new Date());
        return certProjectMapper.insert(project);
    }

    @Override
    public int delete(List<Long> ids) {
        CertProjectExample example = new CertProjectExample();
        example.createCriteria().andIdIn(ids);
        return certProjectMapper.deleteByExample(example);
    }

    @Override
    public int update(ProjectParam projectinfo) {
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        Long id = projectinfo.getId();
        CertProject project = certProjectMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(projectinfo,project);
        project.setUpdateTime(new Date());
        project.setUpdateUserId(userId);
        return certProjectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public CertProject getProject(Long id) {
        return certProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CertProject> listAll() {
        return certProjectMapper.selectByExample(new CertProjectExample());
    }

    @Override
    public List<CertProject> list(Long userid, String projectName, String projectType, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CertProjectExample example = new CertProjectExample();
        CertProjectExample.Criteria criteria = example.createCriteria();
//        if(resourceType!=null){
//        criteria.andResourceTypeEqualTo(resourceType);
        criteria.andUserIdEqualTo(userid);
//        }
        if (projectName != null) {
            criteria.andProjectNameLike('%' + projectName + '%');
        }
        if (StrUtil.isNotEmpty(projectType)) {
            criteria.andProjectTypeEqualTo(projectType);
        }
        return certProjectMapper.selectByExample(example);
    }

    @Override
    public int uploadFile(MultipartFile file, Long projectId) {
        //查询登录用户

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        String username = userDetails.getUsername();
        int fanhui = 0;
        try {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Integer length = fileName.length();
                String devId = fileName.substring(0, length - 4);

//                String name = file.getOriginalFilename();
                InputStream inpustream = file.getInputStream();
                //本地环境存放
//                FileOutputStream fos = new FileOutputStream("E:\\test\\" + File.separator + name);
                //服务环境
                FileOutputStream fos = new FileOutputStream(csrpath+ File.separator+fileName);
                Long filemax = Streams.copy(inpustream, fos, true);
//                Certificate certificate = projectService.uploadFile(csrname,projectID); //插入证书表信息
                if (filemax > 0) { //插入文件成功执行数据更新
                    FileUpload fileUpload = new FileUpload();
                    fileUpload.setCreateTime(new Date());
                    fileUpload.setCreateUserId(userId);
                    fileUpload.setCreateUserName(username);
                    fileUpload.setDeviceId(devId);
                    fileUpload.setFilePath(csrpath + fileName); // 待修改
                    fileUpload.setProjectId(projectId);
                    fileUpload.setFileType(1); //1 csr请求文件
                    fileUpload.setFileStatus(1);// 1 待签发
                    fileUpload.setSignType(1);
                   //添加日志处理
//                    OperateLog loginLog = new OperateLog();
//                    loginLog.setOperatorId(userId);
//                    loginLog.setCreateTime(new Date());
//                    loginLog.setUpdateTime(new Date());
//                    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//                    HttpServletRequest request = attributes.getRequest();
//                    operateLogMapper.insert(loginLog);
                    fanhui = fileUploadMapper.insert(fileUpload);
                }
//                return CommonResult.failed();
            }
        } catch (IOException e) {
//            return false;
            fanhui = 0;
        }
        return fanhui;
    }

    @Override
    /*
    * @param fileUrl 下载文件路径
    * @response
    * */
    public void fileDownload(HttpServletResponse response, String fileUrl) throws IOException {
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        String username = userDetails.getUsername();
        try {
            File file = new File(fileUrl);
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(fileUrl));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            // 清空response
            response.reset();
            response.setContentType("application/octet-stream;charset=UTF-8");
            String fileName = new String(filename.getBytes("gb2312"), "iso8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            OutputStream ouputStream = response.getOutputStream();
            ouputStream.write(buffer);
            ouputStream.flush();
            ouputStream.close();
            fis.close();
            //添加日志处理
//            OperateLog loginLog = new OperateLog();
//            loginLog.setOperatorId(userId);
//            loginLog.setCreateTime(new Date());
//            loginLog.setUpdateTime(new Date());
//            loginLog.setOperateLog("用户"+ username +"下载证书"+ filename);
//            operateLogMapper.insert(loginLog);
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("文件下载出现异常", e);
        }
    }

    /*
    * 已签发证书分页展示列表
    * */
    @Override
    public List<Certificate> certList(Integer projectId,Integer pageSize,Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        CertificateExample example = new CertificateExample();
        int certStatus = 1;//1 激活状态
        int certType = 2;//2  类型为设备证书
        if (!StringUtils.isEmpty(projectId)) {
            example.createCriteria().andProjectIdEqualTo(projectId).andCertTypeEqualTo(certType).andCertStatusEqualTo(certStatus);
        }else {
            example.createCriteria().andCertTypeEqualTo(certType).andCertStatusEqualTo(certStatus);
        }
        return certificateMapper.selectByExample(example);
    }

    /*
//    * 已签发全证书展示列表
//    * */
//    @Override
//    public List<Certificate> ListAll(){
//        PageHelper.startPage(15, 1);
//        CertificateExample example = new CertificateExample();
//        int certStatus = 1;//1 激活状态
//        int certType = 2;//2  类型为设备证书
//        example.createCriteria().andCertTypeEqualTo(certType).andCertStatusEqualTo(certStatus);
//        return certificateMapper.selectByExample(example);
//    }

    @Override
    public boolean fileCheck(MultipartFile file){
        try {
            if(!file.isEmpty()){
                InputStream inpustream = file.getInputStream();
//                FileInputStream fs= new FileInputStream("someFile.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(inpustream));
//                for(int i = 0; i < 1; ++i) {
//                    br.readLine();
//                }
                String lineIWant = br.readLine();
                if (lineIWant.contains("BEGIN CERTIFICATE REQUEST")){ //判断字符里面是否含有 BEGIN CERTIFICATE REQUEST
                    return true;
                }
                br.close();
            }
        }
        catch (IOException e) {
//            return false;
            e.getLocalizedMessage();
        }

        return false;
    }


    /*
    *上传文件分页展示列表
    * */
    @Override
    public List<FileUpload> fileList(Long projectId,Integer pageSize,Integer pageNum){
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        String username = userDetails.getUsername();
        PageHelper.startPage(pageNum, pageSize);
        FileUploadExample example = new FileUploadExample();
        if (!StringUtils.isEmpty(projectId)) {
            example.createCriteria().andProjectIdEqualTo(projectId).andCreateUserIdEqualTo(userId); //返回当前用户下，项目列表下上传文件所有信息
        }
        return fileUploadMapper.selectByExample(example);
    }

    /*
    * 批量下载
    * */

    /**
     * 批量下载
     * @param request 请求
     * @param response 返回
     */
    @Override
    public void batchDownloadFiles(HttpServletRequest request,HttpServletResponse response) {

        //读取前端传来json字段
        String jsonString = request.getParameter("paperInfo");

        //获取web项目根目录
        String fileSaveRootPath = request.getSession().getServletContext().getRealPath("/");

        //创建zip文件并返回zip文件路径
        String zipPath = new ZipUtils().createZipAndReturnPath(jsonString, fileSaveRootPath);

        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/zip;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=Papers.zip");
            System.out.println(response.getHeader("Content-Disposition"));

            //开始下载
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File(zipPath)));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff, 0, buff.length)) != -1) {
                out.write(buff, 0, len);
            }
            out.close();
            out.flush();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
//    public static HttpServletResponse downLoadFiles(
//            List<String> filePath,
    public HttpServletResponse downLoadFiles(
//                                             List<File> files,
                                            List<String> filesPath,
                                             HttpServletRequest request,
                                             HttpServletResponse response)throws Exception {
        try {
            /**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件
             */
            /**创建一个临时压缩文件，
             * 我们会把文件流全部注入到这个文件中
             * 这里的文件你可以自定义是.rar还是.zip
             　　      * 这里的file路径发布到生产环境时可以改为
             */
//            File file = new File(request.getSession().getServletContext().getRealPath("/qrcode.ara"));

            List<File> files =new ArrayList<>();
            for(int i=0;i<filesPath.size();i++) {
                files.add(new File(filesPath.get(i)));
            }

            File file = new File(request.getSession().getServletContext().getRealPath("/downLoadFiles.zip"));
            if (!file.exists()){
                file.createNewFile();
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream
             */
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            return downloadZip(file,response);
        }catch (Exception e) {
            e.printStackTrace();
        }
        /**直到文件的打包已经成功了，
         * 文件的打包过程被我封装在FileUtil.zipFile这个静态方法中，
         * 稍后会呈现出来，接下来的就是往客户端写数据了
         */
        // OutputStream out = response.getOutputStream();


        return response ;
    }

    /**
     * 把接受的全部文件打成压缩包
     */
    public void zipFile (List files,ZipOutputStream outputStream) {
        int size = files.size();
        for(int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }
    public  HttpServletResponse downloadZip(File file,HttpServletResponse response) {
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            try {
                File f = new File(file.getPath());
                f.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 根据输入的文件与输出流对文件进行打包
     */
    public void zipFile(File inputFile,ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 */
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 2048);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber=0;
                    byte[] buffer = new byte[2048];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int add(String devId,Long projectId){
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        String userName = userDetails.getUsername();

        FileUpload fileUpload = new FileUpload();
        fileUpload.setSignType(2);//根据用户填写id签发
        fileUpload.setFilePath("/");
        fileUpload.setFileStatus(1);//待签发
        fileUpload.setFileType(2);//非请求文件
        fileUpload.setDeviceId(devId);
        fileUpload.setProjectId(projectId);
        fileUpload.setCreateTime(new Date());
        fileUpload.setCreateUserId(userId);
        fileUpload.setCreateUserName(userName);
        int count = fileUploadMapper.insert(fileUpload);
        return count;
    }

    @Override
    public int certDel(Long certId){
        Certificate cert = new Certificate();
        String filePath = certificateMapper.selectByPrimaryKey(certId).getCertPath();
        int count = certificateMapper.deleteByPrimaryKey(certId);
        if (count > 0){
            deleteFile(filePath);
            return count;
        }
        return  0;
    }

    /**
     * 删除文件夹（强制删除）
     *
     * @param path
     */
    public static void deleteAllFilesOfDir(File path) {
        if (null != path) {
            if (!path.exists())
                return;
            if (path.isFile()) {
                boolean result = path.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc(); // 回收资源
                    result = path.delete();
                }
            }
            File[] files = path.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir(files[i]);
                }
            }
            path.delete();
        }
    }

    /**
     * 删除文件
     *
     * @param pathname
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(String pathname){
        boolean result = false;
        File file = new File(pathname);
        if (file.exists()) {
            file.delete();
            result = true;
            System.out.println("文件已经被成功删除");
        }
        return result;
    }

    /*
    * 证书吊销
    * */
    @Override
    public int certRevoke(Long id) throws Exception{ //id 证书id
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        String userName = userDetails.getUsername();
        int count = 0;
        CertSignExample example = new CertSignExample();
        example.createCriteria().andCertIdEqualTo(id);
        List<CertSign> certSigns = certSignMapper.selectByExample(example);
        CertSign certS = certSigns.get(0);
        String sn = certS.getSn(); //certSignMapper.selectByPrimaryKey(id).getSn();
        if (sn.length()>0) {
            int vn = exeCmd(sn);
            if (vn == 0) {
                //更新证书表
                Certificate cert = certificateMapper.selectByPrimaryKey(id);
                cert.setCertExpTime(new Date());
                cert.setUpdateTime(new Date());
                cert.setUpdateUserId(userId);
                cert.setCertStatus(2); //过期
                certificateMapper.updateByPrimaryKeySelective(cert);
                //更新签发表
                certS.setCertStar("R");
                certS.setUpdateTime(new Date());
                certS.setUpdateUser(userName);
                return certSignMapper.updateByPrimaryKeySelective(certS);
            }
        }else {
            System.out.println("证书配置信息有误");

        }
        return count;
    }

    /*
    * 执行命令
    * */
    public int exeCmd(String strPath) throws Exception{
        Runtime r = Runtime.getRuntime();
        System.out.println("吊销证书命令" + "openssl ca -revoke " + pemPath + strPath +".pem -config /root/ssh/openssl.conf");
        Process p = r.exec("openssl ca -revoke " + pemPath + strPath +".pem -config /root/ssh/openssl.conf");
        System.out.println("执行完成");
        return p.waitFor();
    }

}