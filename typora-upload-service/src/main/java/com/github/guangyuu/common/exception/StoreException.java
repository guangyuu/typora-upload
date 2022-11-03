package com.github.guangyuu.common.exception;

/**
 * @Author Guangyu
 * @Date 2022/7/8
 * @Description 自定义业务异常
 **/
public class StoreException extends RuntimeException {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public StoreException() {

    }

    public StoreException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
