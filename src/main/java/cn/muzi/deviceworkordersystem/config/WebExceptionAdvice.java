package cn.muzi.deviceworkordersystem.config;

import cn.muzi.deviceworkordersystem.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@RestControllerAdvice
public class WebExceptionAdvice {

    @ExceptionHandler(value = {Exception.class})
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error(String.valueOf(e));
        return Result.fail("服务端异常");
    }
}
