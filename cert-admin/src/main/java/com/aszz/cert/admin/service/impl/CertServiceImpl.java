package com.aszz.cert.admin.service.impl;

import com.aszz.cert.admin.service.CertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class CertServiceImpl implements CertService {

    @Autowired
    private ProjectServiceImpl projectService;

    //证书不同文件存放路径
    @Value("${certpath.zipTemp}")
    private String zipTemp;


    /**
     * -普通java文件下载方法，适用于所有框架
     * -注意：
     * 1.  response.setContentType设置下载内容类型，常用下载类型：
     * application/octet-stream（二进制流，未知文件类型）；
     * application/vnd.ms-excel（excel）；
     * text/plain（纯文本）； text/xml（xml）；text/html（html）；image/gif（GIF）；image/jpeg（JPG）等
     * 如果不写，则匹配所有；
     * 2.  response.setHeader("Content-Disposition","attachment; filename="+fileName +".zip"); 设置下载文件名；
     * 文件名可能会出现乱码，解决名称乱码：fileName  = new String(fileName.getBytes(), "iso8859-1");
     */
    @Override
    public String downloadFilesTest(List<String> filePaths, HttpServletRequest request, HttpServletResponse res) throws IOException {
        try {

            //方法1：IO流实现下载的功能
            String fileName = "MyCerts.zip";//new String("MyCerts.zip".getBytes("GBK"));
            res.setCharacterEncoding("UTF-8"); //设置编码字符
            res.setContentType("application/octet-stream;charset=UTF-8"); //设置下载内容类型
            res.setHeader("Content-disposition", "attachment;filename=" + fileName);//设置下载的文件名称
            OutputStream out = res.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框
            //创建压缩文件需要的空的zip包  测试路径
            String zipBasePath = "E:\\test\\zip\\";
            String zipName = "temp.zip";
            String zipFilePath = "E:\\test\\zip\\" + zipName;
//        // 正式环境路径
//            String zipBasePath= zipTemp;
//            String zipName = "temp.zip";
//            String zipFilePath =zipTemp + zipName;

            //压缩文件
            File zip = new File(zipFilePath);
            if (!zip.exists()) {
                zip.createNewFile();
            }
            //创建zip文件输出流
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
            this.zipFile(zipBasePath, zipName, zipFilePath, filePaths, zos);
            zos.close();
//            projectService.fileDownload( res,zipFilePath);
            res.setHeader("Content-disposition", "attachment;filename=" + zipName);//设置下载的压缩文件名称
            //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));
            byte[] buff = new byte[bis.available()];
            ;
            bis.read(buff);
            bis.close();
            // 清空response
//            res.reset();
            out.write(buff);//输出数据文件
            out.flush();//释放缓存
            out.close();//关闭输出流
        } catch (Exception e) {
            e.printStackTrace();
            res.reset();
//            --GBK
            res.setCharacterEncoding("UTF-8");
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().print("<div align=\"center\" style=\"font-size: 30px;font-family: serif;color: red;\">系统内部错误，下载未成功，请联系管理员！</div>"
                    + "<div>错误信息：" + e.getMessage() + "</div>");
            res.getWriter().flush();
            res.getWriter().close();
        }
        return null;
    }

    /**
     * 压缩文件
     *
     * @param zipBasePath 临时压缩文件基础路径
     * @param zipName     临时压缩文件名称
     * @param zipFilePath 临时压缩文件完整路径
     * @param filePaths   需要压缩的文件路径集合
     * @throws IOException
     */
    private String zipFile(String zipBasePath, String zipName, String zipFilePath, List<String> filePaths, ZipOutputStream zos) throws IOException {

        //循环读取文件路径集合，获取每一个文件的路径
        for (String filePath : filePaths) {
            File inputFile = new File(filePath);  //根据文件路径创建文件
            if (inputFile.exists()) { //判断文件是否存在
                if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹
                    //创建输入流读取文件
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                    //将文件写入zip内，即将文件进行打包
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));
                    //写入文件的方法，同上
                    int size = 0;
                    byte[] buffer = new byte[1024];  //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {
                        zos.write(buffer, 0, size);
                    }
                    //关闭输入输出流
                    zos.closeEntry();
                    bis.close();
                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    try {
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<String>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        return zipFile(zipBasePath, zipName, zipFilePath, filePathsTem, zos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}
