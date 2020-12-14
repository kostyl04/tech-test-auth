package com.visionmate.auth.util;

import com.visionmate.auth.rest.util.CommonData;
import com.visionmate.auth.util.exception.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private static final Map<Class<? extends BusinessException>, HttpStatus> STATUS_MAP;

    static {
        STATUS_MAP = new HashMap<>();
        STATUS_MAP.put(EntityNotFoundException.class, NOT_FOUND);
        STATUS_MAP.put(BadParameterException.class, BAD_REQUEST);
        STATUS_MAP.put(UnauthenticatedException.class, FORBIDDEN);
        STATUS_MAP.put(UnexpectedException.class, NOT_FOUND);
    }

    private CommonData commonData;

    //TODO template processor, message source
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handle(BusinessException exception) {
        String lang = commonData.getLang();
        ErrorResponse errorResponse = new ErrorResponse(exception.getCode(), "error");
        return new ResponseEntity<>(errorResponse, STATUS_MAP.get(exception.getClass()));
    }
}
