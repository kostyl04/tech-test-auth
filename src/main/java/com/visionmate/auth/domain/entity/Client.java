package com.visionmate.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "auth")
@Getter
@Setter
public class Client {

    @Id
    private String id;
    @Column
    private String secret;
}
