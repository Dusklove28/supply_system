package com.ningling.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY = "ninglingPear";
    private static final long EXPIRATION_MS = 86400000;//过期时间24小时
//    private static final String AlgorithmRule = "HMAC256";

    public static String getToken(Map<String,String> nameAndPwd) {
        //设置过期时间
        long expMillis = System.currentTimeMillis() + EXPIRATION_MS;
        Date exp = new Date(expMillis);
        //
        JWTCreator.Builder jwt = JWT.create();

        nameAndPwd.forEach((k, v) -> {
            jwt.withClaim(k, v);
        });
        String token = jwt.withExpiresAt(exp)
                .sign(Algorithm.HMAC256(SECRET_KEY));
        return token;
    }

    public static String parseJWT(String Jwt){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(Jwt);
        return verify.getPayload();
    }
}
