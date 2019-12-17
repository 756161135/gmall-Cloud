package com.atguigu.gmall.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @return
 */
public class JwtUtil {

    public static void main(String[] args){

        // 浏览器ip
        String ip = "12312321312321";
        String browserName = "firefox";
        String salt = ip + browserName;
        // 用户信息
        String userId = "1";
        Map<String,String> map = new HashMap<>();
        map.put("userId",userId);
        // 服务器密钥
        String atguiguKey = "atguigugmall0722";

        String encode = encode(atguiguKey, map, salt);

        System.out.println(encode);

    }


    /***
     * jwt加密
     * @param key
     * @param map
     * @param salt
     * @return
     */
    public static String encode(String key,Map map,String salt){

        if(salt!=null){
            key+=salt;
        }
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);
        jwtBuilder.addClaims(map);

        String token = jwtBuilder.compact();
        return token;
    }

    /***
     * jwt解密
     * @param key
     * @param token
     * @param salt
     * @return
     * @throws SignatureException
     */
    public static  Map decode(String key,String token,String salt)throws SignatureException {
        if(salt!=null){
            key+=salt;
        }
        Claims map = null;

        map = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        return map;

    }

}
