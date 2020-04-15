package com.aszz.cert.admin.controller;

/**
 * Created by User on 2020/3/2.
 */

import com.aszz.cert.admin.bo.RemoteShellExecutor;
import com.aszz.cert.admin.service.CaService;
import com.aszz.cert.common.api.CommonResult;
import com.aszz.cert.model.CertSign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * 后台证书管理.
 */
@Controller
@Api(tags = "CaController", description = "证书管理")
@RequestMapping("/CA")
public class CaController {
    @Autowired
    private CaService caService;

    @ApiOperation(value = "CA自签")
    @RequestMapping(value = "/selfsign", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CertSign> selfsign() throws Exception {
        /**
         * CA 自签，直接执行服务器文件
         * 并插入签发表信息
         * */System.out.println("######    执行服务器shell脚本    ######");
        RemoteShellExecutor executor = new RemoteShellExecutor("47.114.72.214", "root", "Aszz2020");
        // 执行CA-20200228.sh 无参数
        System.out.println(executor.exec("/root/ssh/ca.sh"));
        CertSign signAdmin = caService.selfsign();
        if (signAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(signAdmin);
    }

    /*
     * CA 签发1
     *
     * */

    @ApiOperation(value = "CA签发")
    @RequestMapping(value = "/csrsign", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult csrsign(@RequestParam Integer csrtype, @RequestParam String argument, @RequestParam Integer projectId
//                                            ,@RequestBody Certificate certificate
    ) throws Exception {
        /**
         * CA 自签，直接执行服务器文件
         * csrtype = 1 表示用户自己生成请求文件，上传证书文件由CA签发
         * csrtype = 2 表示用户填入证书信息，系统生成证书并签发
         * 并插入签发表信息
         * */
        String sn = null;
//        try {
        RemoteShellExecutor executor = new RemoteShellExecutor("47.114.72.214", "root", "Aszz2020");
//        String sb2 = executor.read("E:\\test.txt");
//        System.out.print(sb2);
//       System.out.println(executor.exec("cat /root/CA/serial"));
        sn = executor.exec("cat /root/CA/serial").getOutStr().trim();

//            System.out.println(executor.exec("sh /root/ssh/sh1.sh "));
//        String sn =executor.read("\\root\\CA\\serial"); //serial 内容16进制
//        System.out.println(executor.read("/root/CA","index.txt"));
        // 执行CA-20200228.sh 无参数
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        if (csrtype == 1) {
            System.out.println("######    根据用户上传CSR请求文件签发证书   ######");
//            RemoteShellExecutor executor = new RemoteShellExecutor("47.114.72.214", "root", "Aszz2020");
//             执行client1.sh 无参数 argument 表示上传路径
            System.out.println(executor.exec("sh /root/ssh/sh1.sh " + argument));
            //路径文件名：

            String fName = argument;

            File tempFile = new File(fName.trim());
            String fileName = tempFile.getName();
            Integer length = fileName.length();
            String csrname = fileName.substring(0, length - 4);
            System.out.println("fileName = " + csrname);

            //获取证书id
            Long devId = caService.certinfo1(argument, projectId);
            CertSign CaSign = caService.casign(devId, csrname, sn);
            if (CaSign == null) {
                CommonResult.failed();
            }
            return CommonResult.success(CaSign);

            //插入证书表信息

        } else

            System.out.println("######    依据用户所填信息生成csr申请文件并签发    ######");
//        RemoteShellExecutor executor = new RemoteShellExecutor("47.114.72.214", "root", "Aszz2020");
        // 执行client2.sh 无参数 ，argument 填写证书关键信息
        System.out.println(executor.exec("/root/ssh/sh2.sh " + argument));
        //获取证书id
//        argument = "/root/Client/"+argument +".csr";
        Long devId = caService.certinfo2(argument, projectId);
        CertSign CaSign = caService.casign(devId, argument, sn);
        if (CaSign == null) {
            CommonResult.failed();
        }
        return CommonResult.success(CaSign);
    }
    /*CA 签发*/

}
