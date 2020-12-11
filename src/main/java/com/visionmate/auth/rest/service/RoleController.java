package com.visionmate.auth.rest.service;

import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.domain.service.RoleService;
import com.visionmate.auth.rest.model.ApiRole;
import com.visionmate.auth.util.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/roles", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class RoleController {

    private final Mapper mapper;
    private final RoleService roleService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ApiRole create(@RequestBody ApiRole apiRole) {
        Role newRole = mapper.map(apiRole, Role.class);
        newRole.setId(null);
        newRole = roleService.create(newRole);
        return mapper.map(newRole, ApiRole.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public ApiRole update(@PathVariable Integer id, @RequestBody ApiRole apiRole) {
        Role role = mapper.map(apiRole, Role.class);
        role.setId(id);
        Role updatedRole = roleService.update(role);
        return mapper.map(updatedRole, ApiRole.class);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<ApiRole> getRoles() {
        List<Role> roles = roleService.getRoles();
        return mapper.mapToList(roles, ApiRole.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        roleService.deleteById(id);
    }
}