create table user
(
    id       int auto_increment not null,
    name     varchar(20)        not null,
    email    varchar(60)        not null,
    password varchar(60)        not null,
    role_id  int,
    constraint User_pk
        primary key (id),
    constraint User_name_uindex
        unique (name),
    constraint User_email_uindex
        unique (email),
    constraint User_Role__fk
        foreign key (role_id) references role (id)
);

insert into user (name, email, password, role_id)
values ('admin', 'admin@mailinator.com', '$2y$10$GSaZQVaOR1A1sn82TgNx1e9BgQDl4QDlstjTuprw.uCTkUQQd5XZG', 1);