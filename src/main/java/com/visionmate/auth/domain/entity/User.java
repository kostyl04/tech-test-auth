package com.visionmate.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static javax.persistence.GenerationType.IDENTITY;
import static org.springframework.util.ObjectUtils.isEmpty;

@Entity
@Table(schema = "auth")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public List<Permission.PermissionName> getPermissions() {
        if (isNull(role)) {
            return new ArrayList<>();
        }
        Set<Permission> permissions = role.getPermissions();
        if (isEmpty(permissions)) {
            return new ArrayList<>();
        }
        return permissions.stream().map(Permission::getName).collect(toList());
    }
}
