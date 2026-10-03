package cn.muzi.deviceworkordersystem.config;

import cn.hutool.json.JSONUtil;
import cn.muzi.deviceworkordersystem.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
@Component
public class JwtUtil {
    @Value("${config.jwt.secret}")
    private String secret;

    @Value("${config.jwt.expiration}")
    private Long expiration;
    private SecretKey secretKey;
    @PostConstruct
    public void initKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }
   public String generateToken(UserDTO userDTO){
        try {
            return Jwts.builder()
                    .setSubject(JSONUtil.toJsonStr(userDTO))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+expiration))
                    .signWith(secretKey)
                    .compact();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
   }
    public String parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }
        catch (Exception e) {
            return null;
       }
    }
}
