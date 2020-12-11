package com.visionmate.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

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
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
