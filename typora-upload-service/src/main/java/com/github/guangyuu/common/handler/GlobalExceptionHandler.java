package com.github.guangyuu.common.handler;

import com.github.guangyuu.common.constant.CodeEnum;
import com.github.guangyuu.common.exception.StoreException;
import com.github.guangyuu.common.pojo.vo.R;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author Guangyu
 * @Date 2022/7/8
 * @Description 全局异常处理器
 **/
@RestControllerAdvice
@ConditionalOnWebApplication
public class GlobalExceptionHandler {


    /**
     * 处理自定义业务异常
     *
     * @param exception 异常
     * @return 全局统一响应
     */
    @ExceptionHandler(value = StoreException.class)
    public R serviceExceptionHandler(StoreException exception) {
        return R.fail(exception.getErrorMessage());
    }


    /**
     * 其他异常处理
     *
     * @return 全局统一响应
     */
    @ExceptionHandler(Exception.class)
    public R<String> otherExceptionHandler() {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        return R.fail(codeEnum.code(), codeEnum.msg());
    }
}
