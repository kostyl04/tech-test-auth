package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleConverter extends BaseConverter<Role, Role> {

    @Override
    public Role convert(Role from, Role to) {
        to.setName(from.getName());
        to.setId(from.getId());
        to.setPermissions(from.getPermissions());
        return to;
    }
}
