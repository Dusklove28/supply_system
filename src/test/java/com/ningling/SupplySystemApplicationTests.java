package com.ningling;

import com.ningling.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SupplySystemApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void jwt(){

        Map<String, String> map = new HashMap<>();
        map.put("Dusklove28","123456");
        String token = JwtUtils.getToken(map);
        System.out.println(token);
        String parseJWT = JwtUtils.parseJWT(token);
        System.out.println(parseJWT);
    }
}
