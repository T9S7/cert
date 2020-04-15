package com.aszz.cert.admin.bo;

/**
 * Created by User on 2020/3/3.
 */

import java.io.*;
import java.nio.charset.Charset;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.aszz.cert.admin.dto.CmdShellParam;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;


public class RemoteShellExecutor {

    private Connection conn;
    /**
     * 远程机器IP
     */
    private String ip;
    /**
     * 用户名
     */
    private String osUsername;
    /**
     * 密码
     */
    private String password;
    private String charset = Charset.defaultCharset().toString();

    private static final int TIME_OUT = 1000 * 5 * 60;

    /**
     * 构造函数
     *
     * @param ip
     * @param usr
     * @param pasword
     */
    public RemoteShellExecutor(String ip, String usr, String pasword) {
        this.ip = ip;
        this.osUsername = usr;
        this.password = pasword;
    }


    /**
     * 登录
     *
     * @return
     * @throws IOException
     */
    private boolean login() throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(osUsername, password);
    }

    /**
     * 执行脚本
     *
     * @param cmds
     * @return
     * @throws Exception
     */
    @Bean
    public CmdShellParam exec(String cmds) throws Exception {
        CmdShellParam cmdShellParam = new CmdShellParam();
        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
        int ret = -1;
        try {
            if (login()) {
//                System.out.println("登陆成功");
                // Open a new {@link Session} on this connection
                Session session = conn.openSession();
                // Execute a command on the remote machine.
                session.execCommand(cmds);

                stdOut = new StreamGobbler(session.getStdout());
                outStr = processStream(stdOut, charset);

                stdErr = new StreamGobbler(session.getStderr());
                outErr = processStream(stdErr, charset);

                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

                System.out.println("outStr=" + outStr);
                System.out.println("outErr=" + outErr);

                ret = session.getExitStatus();
                cmdShellParam.setRet(ret);
                cmdShellParam.setOutStr(outStr);
            } else {
                throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            IOUtils.closeQuietly(stdOut);
            IOUtils.closeQuietly(stdErr);
        }
        return cmdShellParam;
    }

    /**
     * @param in
     * @param charset
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    private String processStream(InputStream in, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }

    public String read(String filePath) {
        String content = null;
        try {
            if (login()) {
                System.out.println("登陆成功");
                File file = new File(filePath);
                Session session = conn.openSession();
                // Execute a command on the remote machine.
                session.execCommand(filePath);
                FileInputStream fis = new FileInputStream(file); //创建文件输入流
                byte[] buffer = new byte[1024]; //创建文件输入缓冲区
                ByteArrayOutputStream bos = new ByteArrayOutputStream(); //创建内存输出流
                int len;
                while ((len = fis.read(buffer)) != -1) { //当整个循环结束后，文件中的内容就全部写入了缓冲区
                    bos.write(buffer, 0, len);
                }
                byte[] result = bos.toByteArray(); //通过内存输出流把读到的内容放进字节数组
                content = new String(result); //通过字符型的数据存放结果，也就把文件中的内容赋值给了content变
//            sftp.get(new String(fileUid.getBytes("UTF-8"), "ISO-8859-1"), content);
//            return content;
            } else {
                throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
//                content ="异常";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            content ="异常";
        } finally {
            if (conn != null) {
                conn.close();
            }
//            IOUtils.closeQuietly();
        }
        return content;
    }
//    public static void main(String args[]) throws Exception {
//        RemoteShellExecutor executor = new RemoteShellExecutor("47.114.72.214", "root", "Aszz2020");
//        // 执行CA.sh 无参数
//        System.out.println(executor.exec("/root/ssh/CA-20200228.sh"));
//    }

}