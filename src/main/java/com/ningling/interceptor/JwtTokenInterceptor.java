package com.ningling.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ningling.properties.JwtProperties;
import com.ningling.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是访问controller的方法还是其他
        if(!(handler instanceof HandlerMethod)){
            //请求的是资源文件直接放行
            return true;
        }

        //从请求头获取令牌
        String token = request.getHeader("token");

        //校验令牌

        try {
            DecodedJWT decodedJWT = JwtUtils.parseJWT(token, jwtProperties.getSecret_key());
            log.info("jwt检验：",token);
            return true;
        } catch (Exception e) {
            //不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }

}
