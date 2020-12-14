package com.visionmate.auth.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    
    private String code;
    private String errorMessage;

    public ErrorResponse(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
}
