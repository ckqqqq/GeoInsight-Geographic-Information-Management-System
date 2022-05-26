package com.xiaoyuan.usermanager.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName:    R
 * Author:      小袁教程
 * Date:        2022/5/12 23:46
 * Description:
 */
@Data
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    // 把构造方法私有化
    private R() {}

    // 成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    // 失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(201);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
}
