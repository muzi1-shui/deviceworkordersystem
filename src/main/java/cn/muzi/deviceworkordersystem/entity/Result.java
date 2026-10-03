package cn.muzi.deviceworkordersystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private boolean success;
    private String message;
    private Object data;
    public static Result success(Object data) {return new Result(true,null,data);}
    public static Result fail(String msg)
    {
        return new Result(false,msg,null);
    }
    public static Result ok(List<Object> data)
    {
        return new Result(true,null,data);
    }
}
