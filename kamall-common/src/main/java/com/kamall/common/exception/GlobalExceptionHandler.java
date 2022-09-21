package com.kamall.common.exception;

import com.kamall.common.api.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public CommonResult globalHandler(RuntimeException exception) {
        return CommonResult.failed("{ 全局程序异常 }" + exception.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult handle(ApiException exception) {
        if (exception.getErrorCode() != null) {
            return CommonResult.failed(exception.getErrorCode());
        }
        return CommonResult.failed(exception.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }
}
