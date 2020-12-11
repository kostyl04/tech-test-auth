package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.Permission;
import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.rest.model.ApiRole;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class ApiRoleToRoleConverter extends BaseConverter<ApiRole, Role> {

    @Override
    public Role convert(ApiRole from, Role to) {
        to.setId(from.getId());
        to.setName(from.getName());
        to.setPermissions(mapper.mapToSet(from.getPermissions(), Permission.class));
        return to;
    }
}
