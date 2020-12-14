create table recovery_ticket
(
    id      int auto_increment not null,
    user_id int                not null,
    secret  varchar(40),
    constraint Ticket_pk
        primary key (id),
    constraint secret_uindex
        unique (secret),
    constraint Ticket_User__fk
        foreign key (user_id) references user (id)
);