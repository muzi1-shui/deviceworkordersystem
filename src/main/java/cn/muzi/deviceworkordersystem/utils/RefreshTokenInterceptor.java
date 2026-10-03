package cn.muzi.deviceworkordersystem.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.muzi.deviceworkordersystem.config.JwtUtil;
import cn.muzi.deviceworkordersystem.dto.UserDTO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

public class RefreshTokenInterceptor implements HandlerInterceptor {
    private JwtUtil jwtUtil;
    private StringRedisTemplate stringRedisTemplate;
    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate, JwtUtil jwtUtil) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token= request.getHeader("authorization");
        if (StrUtil.isBlank(token)){
            return true;
        }
        String jwt = token.substring(RedisConstants.TOKEN_KEY.length());
        String s = stringRedisTemplate.opsForValue().get(token);
        if (StrUtil.isBlank(s)){
            return true;
        }
        UserDTO userDTO= JSONUtil.toBean(jwtUtil.parseToken(jwt),UserDTO.class);
        UserHolder.saveUser(userDTO);
        stringRedisTemplate.expire(token,86400, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
