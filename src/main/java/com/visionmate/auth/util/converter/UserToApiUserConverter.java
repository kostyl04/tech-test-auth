package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.User;
import com.visionmate.auth.rest.model.ApiRole;
import com.visionmate.auth.rest.model.ApiUser;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class UserToApiUserConverter extends BaseConverter<User, ApiUser> {

    @Override
    public ApiUser convert(User from, ApiUser to) {
        to.setId(from.getId());
        to.setUsername(from.getName());
        to.setRole(mapper.map(from.getRole(), ApiRole.class));
        return to;
    }
}
