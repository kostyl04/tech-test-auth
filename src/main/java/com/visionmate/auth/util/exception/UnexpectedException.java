package com.visionmate.auth.util.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class UnexpectedException extends BusinessException {

    private final Exception cause;

    public UnexpectedException(String code, Exception cause) {
        super(code);
        this.cause = cause;
    }

    public UnexpectedException(String code, Exception cause, Object... params) {
        super(code, params);
        this.cause = cause;
    }

    public UnexpectedException(String code, List<Object> params, Exception cause) {
        super(code, params);
        this.cause = cause;
    }
}
