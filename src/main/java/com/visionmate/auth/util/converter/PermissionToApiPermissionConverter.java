package com.visionmate.auth.util.converter;

import com.visionmate.auth.domain.entity.Permission;
import com.visionmate.auth.rest.model.ApiPermission;
import com.visionmate.auth.util.mapper.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class PermissionToApiPermissionConverter extends BaseConverter<Permission, ApiPermission> {

    @Override
    public ApiPermission convert(Permission from, ApiPermission to) {
        to.setId(from.getId());
        to.setName(mapper.map(from.getName()));
        return to;
    }
}
