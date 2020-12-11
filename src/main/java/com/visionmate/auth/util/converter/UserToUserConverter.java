package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.User;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserConverter extends BaseConverter<User, User> {

    @Override
    public User convert(User from, User to) {
        to.setRole(from.getRole());
        to.setName(from.getName());
        to.setId(from.getId());
        to.setPassword(from.getPassword());
        return to;
    }
}
