package cn.muzi.deviceworkordersystem.utils;

import cn.muzi.deviceworkordersystem.dto.UserDTO;
import cn.muzi.deviceworkordersystem.entity.User;

public class UserHolder{
    private static final ThreadLocal<UserDTO> userHolder = new ThreadLocal<UserDTO>();
    public static void saveUser(UserDTO user){userHolder.set(user);}
    public static UserDTO getUser(){return userHolder.get();}
    public static void removeUser(){userHolder.remove();}
}
