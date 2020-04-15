package com.aszz.cert.admin.config;

import com.aszz.cert.admin.service.UserResourceService;
import com.aszz.cert.admin.service.UserService;
import com.aszz.cert.model.CertResource;
import com.aszz.cert.security.component.DynamicSecurityService;
import com.aszz.cert.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cert-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CertSecurityConfig extends SecurityConfig {
    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceService userResourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<CertResource> resourceList = userResourceService.listAll();
                if (resourceList == null) {
                    return map;
                }
                for (CertResource resource : resourceList) {
                    map.put(resource.getResourcePath(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getResourceName()));
                }
                return map;
            }
        };
    }

}
