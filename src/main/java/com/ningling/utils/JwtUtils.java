package com.ningling.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ningling.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Map;


public class JwtUtils {


//用注解+配置文件指定签名和过期时间，防止硬编码，便于在发布环境进行更改
//    private static final String SECRET_KEY = "yuanning";
//    private static final long EXPIRATION_MS = 86400000;//过期时间24小时

    public static String getToken(String secretKey,long Ttl,Map<String,String> nameAndPwd) {
        //设置过期时间
        long expMillis = System.currentTimeMillis() + Ttl;
        Date exp = new Date(expMillis);
        //创建JWT
        JWTCreator.Builder jwt = JWT.create();

        nameAndPwd.forEach((k, v) -> {
            jwt.withClaim(k, v);
        });
        String token = jwt.withExpiresAt(exp)
                .sign(Algorithm.HMAC256(secretKey));//签名
        return token;
    }

    public static DecodedJWT parseJWT(String Jwt,String secretKey){
       return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(Jwt);
    }
}
