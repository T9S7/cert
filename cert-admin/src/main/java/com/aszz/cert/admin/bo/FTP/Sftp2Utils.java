package com.aszz.cert.admin.bo.FTP;

import com.google.api.client.util.Value;
import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * Created by User on 2020/3/10.
 */
public class Sftp2Utils {

//    private static final Logger log = LoggerFactory.getLogger(Sftp2Utils.class);

    private static final Logger log = org.apache.log4j.Logger.getLogger(Sftp2Utils.class);
    @Value("${sftpIpConfig:}")
    private String sftpIpConfig;
    @Value("${sftpUsernameConfig:}")
    private String sftpUsernameConfig;
    @Value("${sftpPasswordConfig:}")
    private String sftpPasswordConfig;
    @Value("${localPathConfig:}")
    private String localPathConfig;
    @Value("${remotePathConfig:}")
    private String remotePathConfig;

    private String host;// 服务器连接ip
    private String username;// 用户名
    private String password;// 密码
    private int port = 22;// 端口号
    private ChannelSftp sftp = null;
    private String sftpfilepath = null;
    private Session sshSession = null;
    @Value("${sftpTimeOut:0}")
    private int timeOut;

    public Sftp2Utils() {
//        this.host = sftpIpConfig;               //ip
//        this.username = sftpUsernameConfig;    //username
//        this.password = sftpPasswordConfig;    //password
//        this.sftpfilepath = remotePathConfig;  //服务器文件路径
        this.host = "47.114.72.214";               //ip
        this.username = "root";    //username
        this.password = "Aszz2020";    //password
        this.sftpfilepath = "/root/Client/csr";  //服务器文件路径
    }
//
//    public Sftp2Utils(String host, int port, String username, String password) {
//        this.host = host;
//        this.username = username;
//        this.password = password;
//        this.port = port;
//    }

//    public Sftp2Utils(String host, String username, String password) {
//        this.host = host;
//        this.username = username;
//        this.password = password;
//    }

    /**
     * 通过SFTP连接服务器
     */
    public void connect() {
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            if (log.isInfoEnabled()) {
                log.info("Session created.");
            }
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            if (0 != timeOut) {
                sshSession.setTimeout(timeOut);
            }
            sshSession.connect();
            if (log.isInfoEnabled()) {
                log.info("Session connected.");
            }
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            if (log.isInfoEnabled()) {
                log.info("Opening Channel.");
            }
            sftp = (ChannelSftp) channel;
            if (log.isInfoEnabled()) {
                log.info("Connected to " + host + ".");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                if (log.isInfoEnabled()) {
                    log.info("sftp is closed already");
                }
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                if (log.isInfoEnabled()) {
                    log.info("sshSession is closed already");
                }
            }
        }
    }

    /**
     * 批量下载文件
     * //     * @param remotPath：远程下载目录(以路径符号结束,可以为相对路径eg:/assess/sftp/jiesuan_2/2014/)
     *
     * @param localPath：本地保存目录(以路径符号结束,D:\\Duansha\\sftp\\)
     * @return
     */
    public List<String> batchDownLoadFile(String remotePath, String localPath) {
        return batchDownLoadFile(remotePath, localPath, null, null, false);
    }

    /**
     * 批量下载文件
     * <p>
     * //     * @param remotPath：远程下载目录(以路径符号结束,可以为相对路径eg:/assess/sftp/jiesuan_2/2014/)
     *
     * @param localPath：本地保存目录(以路径符号结束,D:\\Duansha\\sftp\\)
     * @param fileFormat：下载文件格式(以特定字符开头,为空不做检验)
     * @param fileEndFormat：下载文件格式(文件格式,为空不做检验)
     * @param del：下载后是否删除sftp文件
     * @return
     */
    public List<String> batchDownLoadFile(String remotePath, String localPath, String fileFormat, String fileEndFormat,
                                          boolean del) {
        List<String> filenames = new ArrayList<String>();
        try {
            // connect();
            Vector<?> v = listFiles(remotePath);
            // sftp.cd(remotePath);
            if (v.size() > 0) {
                //v.size()中包含(. 和 ..)所以实际文件数目=vize-2
                log.info("本次处理文件个数不为零,开始下载...fileSize={}");//, v.size()-2
                Iterator<?> it = v.iterator();
                while (it.hasNext()) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) it.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    log.info("filename: {} ");//, filename
                    if (".".equals(filename) || "..".equals(filename)) {
                        continue;
                    }

                    if (!attrs.isDir()) {
                        boolean flag = false;
                        String localFileName = localPath + filename;
                        fileFormat = fileFormat == null ? "" : fileFormat.trim();
                        fileEndFormat = fileEndFormat == null ? "" : fileEndFormat.trim();
                        // 四种情况
                        if (fileFormat.length() > 0 && fileEndFormat.length() > 0) {
                            if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileFormat.length() > 0 && "".equals(fileEndFormat)) {
                            if (filename.startsWith(fileFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileEndFormat.length() > 0 && "".equals(fileFormat)) {
                            if (filename.endsWith(fileEndFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else {
                            flag = downloadFile(remotePath, filename, localPath, filename);
                            if (flag) {
                                filenames.add(localFileName);
                                if (flag && del) {
                                    deleteSFTP(remotePath, filename);
                                }
                            }
                        }
                    } else {
                        String newRemotePath = remotePath + filename + "/";
                        String newLocalPath = localPath + filename + "\\";
                        File fi = new File(newLocalPath);
                        if (!fi.exists()) {
                            fi.mkdirs();
                        }
                        log.info("newRemotePath:{},  newLocalPath:{}");
                        log.info(newRemotePath);
                        log.info(newLocalPath);
                        batchDownLoadFile(newRemotePath, newLocalPath);
                    }
                }
            }
            if (log.isInfoEnabled()) {
                log.info("download file is success:remotePath=" + remotePath + "and localPath=" + localPath
                        + ",file size is" + (v.size() - 2));
            }
        } catch (SftpException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            // this.disconnect();
        }
        return filenames;
    }

    /**
     * 批量下载文件
     * <p>
     * //     * @param remotPath：远程下载目录(以路径符号结束,可以为相对路径eg:/assess/sftp/jiesuan_2/2014/)
     *
     * @param localPath：本地保存目录(以路径符号结束,D:\\Duansha\\sftp\\)
     * @param fileFormat：下载文件格式(以特定字符开头,为空不做检验)
     * @param fileEndFormat：下载文件格式(文件格式,为空不做检验)
     * @param del：下载后是否删除sftp文件
     * @return
     */
    public List<String> batchDownLoadFileNotDir(String remotePath, String localPath, String fileFormat, String fileEndFormat,
                                                boolean del) {
        List<String> filenames = new ArrayList<String>();
        try {
            // connect();
            Vector<?> v = listFiles(remotePath);
            // sftp.cd(remotePath);
            if (v.size() > 0) {
                //v.size()中包含(. 和 ..)所以实际文件数目=vize-2
                log.info("本次处理文件个数不为零,开始下载...fileSize={}");//, v.size()-2
                Iterator<?> it = v.iterator();
                while (it.hasNext()) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) it.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    log.info(filename);
                    if (".".equals(filename) || "..".equals(filename)) {
                        continue;
                    }

                    if (!attrs.isDir()) {
                        boolean flag = false;
                        String localFileName = localPath + filename;
                        fileFormat = fileFormat == null ? "" : fileFormat.trim();
                        fileEndFormat = fileEndFormat == null ? "" : fileEndFormat.trim();
                        // 四种情况
                        if (fileFormat.length() > 0 && fileEndFormat.length() > 0) {
                            if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileFormat.length() > 0 && "".equals(fileEndFormat)) {
                            if (filename.startsWith(fileFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileEndFormat.length() > 0 && "".equals(fileFormat)) {
                            if (filename.endsWith(fileEndFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else {
                            flag = downloadFile(remotePath, filename, localPath, filename);
                            if (flag) {
                                filenames.add(localFileName);
                                if (flag && del) {
                                    deleteSFTP(remotePath, filename);
                                }
                            }
                        }
                    } else {

                    }
                }
            }
            if (log.isInfoEnabled()) {
                log.info("download file is success:remotePath=" + remotePath + "and localPath=" + localPath
                        + ",file size is" + (v.size() - 2));
            }
        } catch (SftpException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
        return filenames;
    }

    /**
     * 下载单个文件
     * <p>
     * //     * @param remotPath：远程下载目录(以路径符号结束)
     *
     * @param remoteFileName：下载文件名
     * @param localPath：本地保存目录(以路径符号结束)
     * @param localFileName：保存文件名
     * @return
     */
    public boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FileOutputStream fieloutput = null;
        try {
            // sftp.cd(remotePath);
            File file = new File(localPath + localFileName);
            File f = new File(localPath);
            if (!f.exists()) f.mkdirs();
//            if(!file.exists()) {
//                file.createNewFile();
//            }else {
//                log.info("文件已经下载，不需要重复下载.");
//                return true;
//            }
            fieloutput = new FileOutputStream(file);
            sftp.get(remotePath + remoteFileName, fieloutput);
            if (log.isInfoEnabled()) {
                log.info("- - - - - DownloadFile: " + remoteFileName + " success from sftp.");
            }
            return true;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (SftpException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (null != fieloutput) {
                try {
                    fieloutput.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 上传单个文件
     * <p>
     * //     * @param remotePath：远程保存目录 remotePathConfig 替代原来的 remotePath
     * //     * @param remoteFileName：保存文件名 localFileName 替代原来的  remoteFileName
     *
     * @param localPath：本地上传目录(以路径符号结束)
     * @param localFileName：上传的文件名
     * @return
     */
//    public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
    public boolean uploadFile(String localPath, String localFileName) { //String remotePath, String remoteFileName,
        FileInputStream in = null;
        try {
            connect();
//            createDir(remotePath);//remotePathConfig
            createDir(sftpfilepath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
//            sftp.put(in, remoteFileName);
            sftp.put(in, localFileName);
            return true;
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (SftpException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 批量上传文件
     * <p>
     * //     * @param remotePath：远程保存目录  remotePath 改成默认路径地址
     *
     * @param localPath：本地上传目录(以路径符号结束)
     * @param del：上传后是否删除本地文件
     * @return
     */
//    public boolean bacthUploadFile(String remotePath, String localPath, boolean del) { // String remotePath 改成默认路径地址
    public boolean bacthUploadFile(String localPath, boolean del) {
        try {
            connect();
            File file = new File(localPath);
            log.info("file: {} ");//, file
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile() && files[i].getName().indexOf("bak") == -1) {
//                    if (this.uploadFile(remotePath, files[i].getName(), localPath, files[i].getName()) && del) {//
                    if (this.uploadFile(localPath, files[i].getName()) && del) {
                        deleteFile(localPath + files[i].getName());
                    }
                }
            }
            if (log.isInfoEnabled()) {
//                log.info("upload file is success:remotePath=" + remotePath + "and localPath=" + localPath //remotePathConfig
                log.info("upload file is success:remotePath=" + remotePathConfig + "and localPath=" + localPath
                        + ",file size is " + files.length);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            this.disconnect();
        }
        return false;

    }

    /**
     * 删除本地文件
     *
     * @param filePath
     * @return
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        if (!file.isFile()) {
            return false;
        }
        boolean rs = file.delete();
        if (rs && log.isInfoEnabled()) {
            log.info("delete file success from local.");
        }
        return rs;
    }

    /**
     * 创建目录
     *
     * @param createpath
     * @return
     */
    public boolean createDir(String createpath) {
        try {
            if (isDirExist(createpath)) {
                this.sftp.cd(createpath);
                return true;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString())) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }

            }
            this.sftp.cd(createpath);
            return true;
        } catch (SftpException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断目录是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 删除stfp文件
     *
     * @param directory：要删除文件所在目录
     * @param deleteFile：要删除的文件   //     * @param sftp
     */
    public void deleteSFTP(String directory, String deleteFile) {
        try {
            // sftp.cd(directory);
            sftp.rm(directory + deleteFile);
            if (log.isInfoEnabled()) {
                log.info("delete file success from sftp.");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 如果目录不存在就创建目录
     *
     * @param path
     */
    public void mkdirs(String path) {
        File f = new File(path);

        String fs = f.getParent();

        f = new File(fs);

        if (!f.exists()) {
            f.mkdirs();
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory：要列出的目录 //     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector<?> listFiles(String directory) throws SftpException {
        log.info(directory);
        return sftp.ls(directory);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ChannelSftp getSftp() {
        return sftp;
    }

    public void setSftp(ChannelSftp sftp) {
        this.sftp = sftp;
    }

    /** 测试 */
//    public static void main(String[] args) {
//        Sftp2Utils sftp = null;
//        // 本地存放地址
//        String localPath = "M:\\";
//        // Sftp下载路径
//        String sftpPath = "/usr/etc/";
//        try {
//            sftp = new Sftp2Utils("172.168.65.171", "root", "root1234");
//            sftp.connect();
//            // 下载
//            sftp.batchDownLoadFile(sftpPath, localPath, "error", null, false);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//        } finally {
//            sftp.disconnect();
//        }
//    }

    /**
     错误处理：
     1. java.security.NoSuchAlgorithmException: DH KeyPairGenerator not available
     复制sunjce_provider.jar(jre/lib/ext/)到lib下。
     */

}