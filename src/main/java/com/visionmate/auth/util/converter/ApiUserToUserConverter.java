package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.domain.entity.User;
import com.visionmate.auth.rest.model.ApiUser;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class ApiUserToUserConverter extends BaseConverter<ApiUser, User> {

    @Override
    public User convert(ApiUser from, User to) {
        to.setId(from.getId());
        to.setPassword(from.getPassword());
        to.setName(from.getUsername());
        to.setRole(mapper.map(from.getRole(), Role.class));
        return to;
    }
}
