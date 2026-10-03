package cn.muzi.deviceworkordersystem.controller;

import cn.muzi.deviceworkordersystem.dto.RegisterDTO;
import cn.muzi.deviceworkordersystem.entity.Result;
import cn.muzi.deviceworkordersystem.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    IUserService userService;
    @PostMapping("/register")//管理员注册账号接口
    public Result register(@RequestBody RegisterDTO registerDTO)
    {
        System.out.println(registerDTO);
        return userService.register(registerDTO);
    }
}
