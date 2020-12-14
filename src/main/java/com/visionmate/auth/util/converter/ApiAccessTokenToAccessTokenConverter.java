package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.AccessToken;
import com.visionmate.auth.rest.model.ApiAccessToken;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class ApiAccessTokenToAccessTokenConverter extends BaseConverter<ApiAccessToken, AccessToken> {

    @Override
    public AccessToken convert(ApiAccessToken from, AccessToken to) {
        to.setPassword(from.getPassword());
        to.setClientId(from.getClientId());
        to.setClientSecret(from.getClientSecret());
        to.setUsername(from.getUsername());
        return to;
    }
}
