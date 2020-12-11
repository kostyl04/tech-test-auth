package com.visionmate.auth.rest.util;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

import static com.visionmate.auth.util.Constants.Headers.*;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Getter
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = TARGET_CLASS)
public class CommonData {

    private String lang = "en";
    private final String token;
    private final String device;

    public CommonData(HttpServletRequest request) {
        String lang = request.getHeader(X_LANG);
        if (isNotEmpty(lang)) {
            this.lang = lang;
        }
        token = request.getHeader(X_TOKEN);
        device = request.getHeader(X_DEVICE);
    }
}