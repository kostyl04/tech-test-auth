package com.visionmate.auth.util.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final String code;
    private final List<Object> params;

    public BusinessException(String code) {
        this(code, new Object[0]);
    }

    public BusinessException(String code, Object... params) {
        this.code = code;
        if (isEmpty(params)) {
            this.params = new ArrayList<>();
        } else {
            this.params = Arrays.asList(params);
        }
    }

    public BusinessException(String code, List<Object> params) {
        this.code = code;
        this.params = params;
    }
}
