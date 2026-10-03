package cn.muzi.deviceworkordersystem.controller;

import cn.muzi.deviceworkordersystem.dto.LoginDTO;
import cn.muzi.deviceworkordersystem.dto.RegisterDTO;
import cn.muzi.deviceworkordersystem.entity.Result;
import cn.muzi.deviceworkordersystem.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;
    @PostMapping("/login")//用户登录接口
    public Result login(@RequestBody LoginDTO loginDTO)
    {
        return userService.login(loginDTO);
    }
}
