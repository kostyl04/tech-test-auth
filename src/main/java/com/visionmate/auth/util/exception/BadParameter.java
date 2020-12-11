package com.visionmate.auth.util.exception;

import java.util.List;

public class BadParameter extends BusinessException {

    public BadParameter(String code) {
        super(code);
    }

    public BadParameter(String code, Object... params) {
        super(code, params);
    }

    public BadParameter(String code, List<Object> params) {
        super(code, params);
    }
}
