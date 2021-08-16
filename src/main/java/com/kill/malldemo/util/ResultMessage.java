package com.kill.malldemo.util;

import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author lishen
 * @Date 26/7/21 10:03 pm
 **/

@Component
public class ResultMessage {

    private String code;
    private String message;
    private Object data;

    public void success(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void success(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public void success(String code, Object data) {
        this.code = code;
        this.message = null;
        this.data = data;
    }

    public void fail(String code, String msg) {
        this.code = code;
        this.message = msg;
        this.data = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
