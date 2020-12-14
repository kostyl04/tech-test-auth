package com.visionmate.auth.util.exception;

import java.util.List;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String code) {
        super(code);
    }

    public EntityNotFoundException(String code, Object... params) {
        super(code, params);
    }

    public EntityNotFoundException(String code, List<Object> params) {
        super(code, params);
    }
}
