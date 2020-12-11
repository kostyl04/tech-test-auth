package com.visionmate.auth.util.exception;

import java.util.List;

public class EntityNotFound extends BusinessException {
    public EntityNotFound(String code) {
        super(code);
    }

    public EntityNotFound(String code, Object... params) {
        super(code, params);
    }

    public EntityNotFound(String code, List<Object> params) {
        super(code, params);
    }
}
