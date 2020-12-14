package com.visionmate.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(schema = "auth", name = "recovery_ticket")
public class RecoveryTicket {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Transient
    private String email;

    @Transient
    private String newPassword;

    @Column
    private String secret;
}
