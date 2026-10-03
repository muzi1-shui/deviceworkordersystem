package cn.muzi.deviceworkordersystem;

import cn.muzi.deviceworkordersystem.config.JwtUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class DeviceWorkOrderSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceWorkOrderSystemApplication.class, args);
    }

}
