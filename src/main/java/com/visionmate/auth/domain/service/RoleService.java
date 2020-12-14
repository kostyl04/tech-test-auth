package com.visionmate.auth.domain.service;

import com.visionmate.auth.domain.entity.Permission;
import com.visionmate.auth.domain.entity.Role;
import com.visionmate.auth.domain.repository.PermissionRepository;
import com.visionmate.auth.domain.repository.RoleRepository;
import com.visionmate.auth.util.exception.BadParameterException;
import com.visionmate.auth.util.exception.EntityNotFoundException;
import com.visionmate.auth.util.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.hibernate.Hibernate.unproxy;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final Mapper mapper;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role getRole(Integer id) {
        return roleRepository.getById(id);
    }

    @Transactional
    public Role create(Role role) {
        validateRole(role);
        Set<Permission> permissions = permissionRepository.getByIdIn(role.getPermissions().stream()
                .map(Permission::getId)
                .collect(toList())
        );
        checkPermissionExistence(role, permissions);
        role.setPermissions(permissions);
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        if (!existsById(role.getId())) {
            throw new EntityNotFoundException("role.not.found", role.getId());
        }
        validateRole(role);
        Set<Permission> permissions = permissionRepository.getByIdIn(role.getPermissions().stream()
                .map(Permission::getId)
                .collect(toList())
        );
        checkPermissionExistence(role, permissions);
        role.setPermissions(permissions);
        Role oldRole = roleRepository.getById(role.getId());
        oldRole = mapper.map(role, unproxy(oldRole, Role.class));
        return roleRepository.save(oldRole);
    }

    private void checkPermissionExistence(Role role, Set<Permission> permissions) {
        if (!permissions.equals(role.getPermissions())) {
            role.getPermissions().removeAll(permissions);
            throw new BadParameterException("nonexistent.permissions", role.getPermissions().stream()
                    .map(Permission::getId)
                    .collect(toList())
            );
        }
    }

    private void validateRole(Role role) {
        if (isEmpty(role.getName())) {
            throw new BadParameterException("role.name.could.not.be.null");
        }
        if (nonNull(role.getId())) {
            if (roleRepository.existsByNameAndIdNot(role.getName(), role.getId())) {
                throw new BadParameterException("role.name.already.exists", role.getName());
            }
        } else {
            if (roleRepository.existsByName(role.getName())) {
                throw new BadParameterException("role.name.already.exists", role.getName());
            }
        }
    }

    public void deleteById(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        }
    }

    public boolean existsById(Integer id) {
        return roleRepository.existsById(id);
    }
}