package com.aszz.cert.security.component;

import com.aszz.cert.common.api.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedisTokenRefreshFilter extends OncePerRequestFilter {

    @Value("${redis.key.prefix.jwtToken}")
    private String REDIS_KEY_PREFIX_JWTOKEN;
    @Value("${redis.key.expire.jwtToken}")
    private Long REDIS_KEY_EXPIRE_JWTOKEN_SECONDS;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired(required = false)
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        String url = request.getRequestURL().toString();
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            //是接口
            if (!url.contains("/admin/login")) {
                //不是登录接口,其他接口
                if (redisService.get(REDIS_KEY_PREFIX_JWTOKEN) == null) {
                    //token过期
//                    throw new TokenExpireException(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage());
                    throw new IOException("test");
//                    return;
                } else {
                    //刷新token有效时间
//                redisService.remove(REDIS_KEY_PREFIX_JWTOKEN);
                    redisService.expire(REDIS_KEY_PREFIX_JWTOKEN, REDIS_KEY_EXPIRE_JWTOKEN_SECONDS);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
