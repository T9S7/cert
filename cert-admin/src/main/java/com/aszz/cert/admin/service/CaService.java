package com.aszz.cert.admin.service;

import com.aszz.cert.model.CertSign;

/**
 * Created by User on 2020/3/3.
 */
public interface CaService {
    /*
     * 插入证书表
     * */
    Long certinfo1(String argument, Integer projectId);

    Long certinfo2(String argument, Integer projectId);

    /*CA 自签*/
    CertSign selfsign();

    /*CA 签发*/
    CertSign casign(Long devId, String csrname, String sn);
}
