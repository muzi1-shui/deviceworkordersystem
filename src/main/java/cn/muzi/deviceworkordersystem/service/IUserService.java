package cn.muzi.deviceworkordersystem.service;

import cn.muzi.deviceworkordersystem.dto.LoginDTO;
import cn.muzi.deviceworkordersystem.dto.RegisterDTO;
import cn.muzi.deviceworkordersystem.entity.Result;
import cn.muzi.deviceworkordersystem.entity.User;
import com.baomidou.mybatisplus.spring.service.IService;

public interface IUserService extends IService<User> {
    Result login(LoginDTO loginDTO);

    Result register(RegisterDTO registerDTO);
}
