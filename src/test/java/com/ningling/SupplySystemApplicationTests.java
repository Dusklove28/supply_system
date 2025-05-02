//package com.ningling;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.AlgorithmMismatchException;
//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.ningling.properties.JwtProperties;
//import com.ningling.utils.JwtUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@SpringBootTest
//class SupplySystemApplicationTests {
//
//    @Autowired
//    private JwtProperties jwtProperties;
//    @Test
//    void contextLoads() {
//
//    }
//
//    @Test
//    void jwt(){
//
//        Map<String, String> map = new HashMap<>();
//        map.put("Dusklove28","123456");
//        String  token = JwtUtils.getToken(jwtProperties.getSecret_key(), jwtProperties.getTtl(), map);
//        System.out.println(token);
////        String parseJWT = JwtUtils.parseJWT(token);
////        System.out.println(parseJWT);
////        System.out.println(jwtProperties.getSecret_key());
////        System.out.println(jwtProperties.getTtl());
//
//        try {
//            JWT.require(Algorithm.HMAC256("yuanning")).build().verify(token);
//        }catch (SignatureVerificationException e){
//            e.printStackTrace();
//            map.put("msg","无效签名！");
//        }catch (TokenExpiredException e){
//            e.printStackTrace();
//            map.put("msg","token过期");
//        }catch (AlgorithmMismatchException e){
//            e.printStackTrace();
//            map.put("msg","算法不一致");
//        }catch (Exception e){
//            e.printStackTrace();
//            map.put("msg","token无效！");
//        }
//
//    }
//}
