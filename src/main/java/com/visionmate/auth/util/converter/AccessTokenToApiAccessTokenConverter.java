package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.AccessToken;
import com.visionmate.auth.rest.model.ApiAccessToken;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenToApiAccessTokenConverter extends BaseConverter<AccessToken, ApiAccessToken> {

    @Override
    public ApiAccessToken convert(AccessToken from, ApiAccessToken to) {
        to.setAccessToken(from.getAccessToken());
        return to;
    }
}
