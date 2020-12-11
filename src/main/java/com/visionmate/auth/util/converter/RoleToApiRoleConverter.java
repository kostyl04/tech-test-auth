package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.rest.model.ApiPermission;
import com.visionmate.auth.rest.model.ApiRole;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class RoleToApiRoleConverter extends BaseConverter<Role, ApiRole> {

    @Override
    public ApiRole convert(Role from, ApiRole to) {
        to.setId(from.getId());
        to.setPermissions(mapper.mapToList(from.getPermissions(), ApiPermission.class));
        to.setName(from.getName());
        return to;
    }
}
