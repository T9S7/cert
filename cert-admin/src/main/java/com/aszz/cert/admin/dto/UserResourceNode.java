package com.aszz.cert.admin.dto;

import com.aszz.cert.model.CertResource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by rongzhang on 2020/3/4.
 */
public class UserResourceNode extends CertResource {
    @Getter
    @Setter
    private List<UserResourceNode> children;
}
