package com.kamall.common.exception;

import com.kamall.common.api.IErrorCode;

/**
 * 用于处理自定义Api异常
 */

public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
