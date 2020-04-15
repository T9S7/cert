package com.aszz.cert.admin.service.impl;

/**
 * Created by User on 2020/3/3.
 */

import cn.hutool.core.util.RandomUtil;
import com.aszz.cert.admin.bo.AdminUserDetails;
import com.aszz.cert.mapper.CertSignMapper;
import com.aszz.cert.mapper.CertificateMapper;
import com.aszz.cert.model.CertSign;
import com.aszz.cert.model.Certificate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.aszz.cert.admin.service.CaService;

/**
 * CaService 实现类
 */
@Service
@Slf4j
public class CaServiceImpl implements CaService {

    @Autowired
    private CertSignMapper signMapper;

    @Autowired
    private CertificateMapper certificateMapper;


    @Override
    public Long certinfo1(String argument, Integer projectId) {
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        Certificate cert = new Certificate();
        cert.setProjectId(projectId);
//        cert.setCertCsrPath(argument);
        cert.setCreateTime(new Date());
        cert.setUpdateTime(new Date());
        cert.setCreateUserId(userId);
        cert.setUpdateUserId(userId);
        cert.setCertPath("/root/Client/crt/" + userId + "/");
        cert.setCertType(2);
        cert.setCertStatus(1);
        certificateMapper.insert(cert);
        return cert.getId();
    }

    @Override
    public Long certinfo2(String argument, Integer projectId) {
        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        Certificate cert = new Certificate();
        cert.setProjectId(projectId);
//        cert.setCertCsrPath("/root/Client/csr/" + userId + "/" + argument + ".csr");
        cert.setCertPath("/root/Client/crt/" + userId + "/" + argument + ".crt");
        cert.setCertMqttClientId(RandomUtil.randomString(12));
        cert.setCertStatus(1);
        cert.setCertType(2);
        cert.setCreateTime(new Date());
        cert.setUpdateTime(new Date());
        cert.setCreateUserId(userId);
        cert.setUpdateUserId(userId);
        certificateMapper.insert(cert);
        return cert.getId();
    }

    @Override
    public CertSign selfsign() {
        CertSign sign = new CertSign();
        sign.setSignCode(1);// 1 自签  2 CA签发
        sign.setCertStar("V");
        sign.setSn("0");
        sign.setSubject("admin");
        sign.setCsruser("admin"); //csr证书请求文件申请人
        sign.setSignTime(new Date());
        sign.setUpdateUser("admin");
        sign.setUpdateTime(new Date());
        sign.setReserve1("");
        signMapper.insert(sign);
        return sign;
    }

    /*CA 签发*/
    @Override
    public CertSign casign(Long devId, String csrname, String sn) {

//        //登陆远程服务器
//        RemoteShellExecutor executor = new RemoteShellExecutor("47.114.72.214", "root", "Aszz2020");
//
//        String sn ="";
//            try {
//                BufferedReader bufferedReader = new BufferedReader(new FileReader("/root/CA/index.txt")); //相对文件路径，如果是放在项目文件夹下，则为new FileReader("test.txt");
//
//                while ((sn=bufferedReader.readLine()) != null){
//                    System.out.println(sn);
//                }
//                System.out.println(sn);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        //查询登录用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        CertSign sign = new CertSign();
        sign.setUpdateTime(new Date());
        sign.setSignTime(new Date());
        sign.setCertId(devId);
        sign.setSignCode(2);
        sign.setCertStar("V");
        sign.setCsruser(userName);
        sign.setUpdateUser(userName);
        sign.setSn(sn);//sn 16进制，string类型，为保持一致，未转换
        sign.setSubject(csrname);
        signMapper.insert(sign);
        return sign;
    }
}
