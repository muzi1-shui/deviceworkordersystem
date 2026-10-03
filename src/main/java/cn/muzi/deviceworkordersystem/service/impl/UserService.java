package cn.muzi.deviceworkordersystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.muzi.deviceworkordersystem.config.JwtUtil;
import cn.muzi.deviceworkordersystem.dto.LoginDTO;
import cn.muzi.deviceworkordersystem.dto.RegisterDTO;
import cn.muzi.deviceworkordersystem.dto.UserDTO;
import cn.muzi.deviceworkordersystem.entity.Result;
import cn.muzi.deviceworkordersystem.entity.User;
import cn.muzi.deviceworkordersystem.mapper.UserMapper;
import cn.muzi.deviceworkordersystem.service.IUserService;
import cn.muzi.deviceworkordersystem.utils.RedisConstants;
import cn.muzi.deviceworkordersystem.utils.RegexUtil;
import cn.muzi.deviceworkordersystem.utils.UserHolder;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper,User> implements IUserService {
    @Resource
    JwtUtil jwtUtil;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Override
    public Result login(LoginDTO loginDTO) {
        if (loginDTO.getUsername().isEmpty()|| loginDTO.getPassword().isEmpty()||loginDTO.getPhone().isEmpty()) {
            return Result.fail("数据不全，请重新填写");
        }
        if (!RegexUtil.isPhone(loginDTO.getPhone()))
        {
            return Result.fail("手机号格式不对");
        }
        User user=query().eq("phone", loginDTO.getPhone()).eq("password",loginDTO.getPassword()).one();
        if (user==null)
        {
            return Result.fail("请联系管理员创建用户");
        }
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        String token = RedisConstants.TOKEN_KEY+jwtUtil.generateToken(userDTO);
        UserHolder.saveUser(userDTO);
        stringRedisTemplate.opsForValue().set(token,JSONUtil.toJsonStr(userDTO),86400, TimeUnit.SECONDS);
        return Result.success(token);
    }

    @Override
    public Result register(RegisterDTO registerDTO) {

            UserDTO userDTO = UserHolder.getUser();
            if (UserHolder.getUser()==null)
            {
                return Result.fail("请登录账号");
            }
            Integer role = userDTO.getRole();
            if (role == null || role != 1)
            {
                return Result.fail("当前账号权限不够");
            }
            long count=query().eq("username",registerDTO.getUsername()).count();
            if (count>0)
            {
                return Result.fail("用户名已存在");
            }
            saveUser(registerDTO);
            return Result.success("success");
    }
    public void saveUser(RegisterDTO registerDTO) {
        User user=new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setPhone(registerDTO.getPhone());
        user.setRole(registerDTO.getRole());
        save(user);
    }
}
