package com.visionmate.auth.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@Setter
public class ApiRole {

    private Integer id;
    private String name;
    private List<ApiPermission> permissions;

    public List<ApiPermission> getPermissions() {
        if (isNull(permissions)) {
            permissions = new ArrayList<>();
        }
        return permissions;
    }
}
