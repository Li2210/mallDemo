package com.kill.malldemo.Exception;

/**
 * @Description TODO
 * @Author lishen
 * @Date 26/7/21 4:06 pm
 **/
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
