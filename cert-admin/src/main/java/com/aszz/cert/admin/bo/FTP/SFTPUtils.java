package com.aszz.cert.admin.bo.FTP;

/**
 * Created by User on 2020/3/6.
 */

import java.io.*;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
//import org.slf4j.Logger;
//import org.apache.logging.log4j.Logger;

/**
 * @author Lenovo
 * <p>
 * 连接Linux服务器，对 相应的目录、文件进行操作。
 */
public class SFTPUtils {

    private static final Logger logger = org.apache.log4j.Logger.getLogger(SFTPUtils.class);

    private static final String host = "47.114.72.214";
    private static final int port = 22;
    private static final String username = "root";
    private static final String password = "Aszz2020";
    public static final String directory = "/root/CA";

    private static ChannelSftp sftp;

    private static SFTPUtils instance = null;

    private SFTPUtils() {

    }

    public static SFTPUtils getInstance() {
        if (instance == null) {
            if (instance == null) {
                instance = new SFTPUtils();
                sftp = instance.connect(host, port, username, password);   //获取连接
            }
        }
        return instance;
    }

    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public ChannelSftp connect(String host, int port, String username, String password) {
        ChannelSftp sftp = null;
        try {
            // 创建JSch对象
            JSch jsch = new JSch();

            // 根据用户名、主机ip、端口号获取一个Session对象
            Session sshSession = jsch.getSession(username, host, port);

            // 设置密码
            sshSession.setPassword(password);

            // 为Session对象设置properties
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);

            // 设置超时
            sshSession.setTimeout(1000 * 30);

            // 通过Session建立连接
            sshSession.connect();

            logger.info("SFTP Session connected.");
            // 打开SFTP通道
            Channel channel = sshSession.openChannel("sftp");

            // 建立SFTP通道的连接
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("Connected successfully to ftpHost = " + host);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory  上传的目录
     * @param uploadFile 要上传的文件
     */
    public boolean upload(String directory, String uploadFile) {
        try {
            // 进入指定目录
            sftp.cd(directory);
            // 创建文件
            File file = new File(uploadFile);
            // 创建文件输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            // 上传文件
            sftp.put(fileInputStream, file.getName());

            // 关闭连接
            fileInputStream.close();

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     */
    public File download(String directory, String downloadFile, String saveFile) {
        try {
            sftp.cd(directory);
            File file = new File(saveFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            sftp.get(downloadFile, fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 下载文件
     *
     * @param downloadFilePath 下载的文件完整目录
     * @param saveFile         存在本地的路径
     */
    public File download(String downloadFilePath, String saveFile) {
        try {
            int i = downloadFilePath.lastIndexOf('/');
            if (i == -1)
                return null;
            sftp.cd(downloadFilePath.substring(0, i));
            File file = new File(saveFile);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            sftp.get(downloadFilePath.substring(i + 1), fileOutputStream);
            fileOutputStream.close();
            logger.info("下载文件：" + saveFile + "成功");
            return file;
        } catch (Exception e) {
            logger.error("下载文件：" + saveFile + "失败" + e.getMessage());
            return null;
        }
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 阅读文件
     *
     * @param directory 要查询文件所在目录
     * @param readFile  要查询的文件
     */

    public void read(String directory, String readFile) {

        try {
            sftp.cd(directory);
//            sftp.rm(deleteFile);
            File file = new File(directory + "/" + readFile);
            FileInputStream fis = new FileInputStream(file); //创建文件输入流
            byte[] buffer = new byte[1024]; //创建文件输入缓冲区
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); //创建内存输出流
            int len;
            while ((len = fis.read(buffer)) != -1) { //当整个循环结束后，文件中的内容就全部写入了缓冲区
                bos.write(buffer, 0, len);
            }
            byte[] result = bos.toByteArray(); //通过内存输出流把读到的内容放进字节数组
            String content;
            content = new String(result); //通过字符型的数据存放结果，也就把文件中的内容赋值给了content变
//            sftp.get(new String(fileUid.getBytes("UTF-8"), "ISO-8859-1"), content);
//            return content;
        } catch (Exception e) {
            logger.error(e.getMessage());
//            content ="异常";
        }
//        return content;
    }

    /**
     * 断开服务器连接
     */
    public void disconnect() {
        try {
            sftp.getSession().disconnect();
        } catch (JSchException e) {
            logger.error(e.getMessage());
        }
        sftp.quit();
        sftp.disconnect();
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @throws SftpException
     */
    @SuppressWarnings("unchecked")
    public Vector<LsEntry> listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
        SFTPUtils sf = SFTPUtils.getInstance();
        sf.upload(directory, "E:\\file\\用户信息20190823.xls");    //上传文件

        sf.download(directory, "2.png", "C:\\Users\\hp\\Desktop\\1212.png");

//		File download = sf.download("/home/1.png", "C:\\Users\\hp\\Desktop\\122221.png");

//        sf.delete(directory, deleteFile); //删除文件

        Vector<LsEntry> files = null;        //查看文件列表
        try {
            files = sf.listFiles(directory);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        for (LsEntry file : files) {
            System.out.println("###\t" + file.getFilename());
        }
        sf.disconnect();
    }
}