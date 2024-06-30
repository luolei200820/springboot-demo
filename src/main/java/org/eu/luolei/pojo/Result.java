package org.eu.luolei.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    // 快速返回成功的响应(带响应数据)
    public static <E> Result<E> success(E data){
        return new Result<>(0,"操作成功",data);
    }

    // 快速返回成功响应(不带响应数据)
    public static Result success(){
        return new Result(0,"操作成功",null);
    }

    public static Result error(String message){
        return new Result(1,message,null);
    }
}
