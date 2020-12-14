package com.visionmate.auth.util.exception;

import java.util.List;

public class UnauthenticatedException extends BusinessException{


    public UnauthenticatedException(String code) {
        super(code);
    }

    public UnauthenticatedException(String code, Object... params) {
        super(code, params);
    }

    public UnauthenticatedException(String code, List<Object> params) {
        super(code, params);
    }
}
