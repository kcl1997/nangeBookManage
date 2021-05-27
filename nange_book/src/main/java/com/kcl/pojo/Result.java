package com.kcl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名： nange_book
 * 包名:    com.kcl.pojo
 * 文件名   Result
 * 创建者
 * 创建时间: 2021/5/25 6:21 PM
 * 描述  结果集封装
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code; //200 ok
    private String msg;
    private Object obj;




    public static Result success(String msg, Object obj){
        Result result = new Result(200,msg,obj);
        return  result;
    }

    public static Result success(Object obj){
        return  success("请求成功",obj);
    }


    public static Result error(Integer code,String msg,Object obj){
        Result result = new Result(code,msg,obj);
        return  result;
    }
    public static Result error(Integer code,String msg){
       return error(code,msg,null);
    }
    public static Result error(Integer code){
      return error(code,"请求失败");
    }

}

