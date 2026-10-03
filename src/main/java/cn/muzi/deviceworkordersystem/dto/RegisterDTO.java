package cn.muzi.deviceworkordersystem.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String phone;
    private Integer role;
}
