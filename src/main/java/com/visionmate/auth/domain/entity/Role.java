package com.visionmate.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(schema = "auth")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(name = "Role_Permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissions = new HashSet<>();
}
