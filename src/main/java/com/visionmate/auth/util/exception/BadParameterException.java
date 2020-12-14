package com.visionmate.auth.util.exception;

import java.util.List;

public class BadParameterException extends BusinessException {

    public BadParameterException(String code) {
        super(code);
    }

    public BadParameterException(String code, Object... params) {
        super(code, params);
    }

    public BadParameterException(String code, List<Object> params) {
        super(code, params);
    }
}
