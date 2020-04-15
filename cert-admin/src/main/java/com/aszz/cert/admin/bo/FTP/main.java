package com.aszz.cert.admin.bo.FTP;

/**
 * Created by User on 2020/3/6.
 */
public class main {
    public static void main(String[] args) throws Exception {
        ftpUtil ftpUtil = new ftpUtil();
        String hostname = "47.114.72.214";
        // ftp服务器端口号默认为21
        Integer port = 22;
        // ftp登录账号
        String username = "root";
        // ftp登录密码
        String password = "Aszz2020";

        /**
         * 上传文件
         */
        /** //ftp文件上传路径
         String path = "mp3";
         //ftp文件上传名称
         String fileName = "a.txt";
         //文件流io
         InputStream is = new FileInputStream(new File("E:/备份/资料库/工具包/BCompare-zh-3.2.3.13046/readme.txt"));
         // 通过ftp将文件流写出
         ftpUtil.uploadFile(path, fileName, is, hostname, port, username, password);
         **/
        /**
         * 下载文件ftp://192.168.1.185:21/var/ftp/lkqftp/mp3/a.txt
         */
        ftpUtil.initFtpClient(hostname, port, username, password);
        System.out.println(ftpUtil.existFile("mp3/a.txt"));
        boolean downloadFile = ftpUtil.downloadFile("mp3", "a.txt", "E:/", hostname, port, username, password);
    }

}
