package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.Permission;
import com.visionmate.auth.domain.entity.Permission.PermissionName;
import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.rest.model.ApiPermission;
import com.visionmate.auth.rest.model.ApiRole;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class ApiPermissionToPermissionConverter extends BaseConverter<ApiPermission, Permission> {

    @Override
    public Permission convert(ApiPermission from, Permission to) {
        to.setId(from.getId());
        to.setName(mapper.map(from.getName(), PermissionName.class));
        return to;
    }
}
