package com.visionmate.auth.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Table(schema = "auth")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permission {

    @Id
    @Include
    private Integer id;
    @Column(columnDefinition = "ENUM('CREATE_USER', 'UPDATE_USER', 'DELETE_USER', 'LIST_USERS', 'ALL')")
    @Enumerated(value = STRING)
    private PermissionName name;

    public enum PermissionName {
        CREATE_USER, UPDATE_USER, DELETE_USER, LIST_USERS, ALL
    }
}
