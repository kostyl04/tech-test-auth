create table role
(
    id   int auto_increment not null,
    name varchar(20)        not null,
    constraint Role_pk
        primary key (id),
    constraint Role_name_uindex
        unique (name)
);

create table role_permission
(
    role_id       int not null,
    permission_id int not null,
    constraint Role_Permission_pk
        primary key (role_id, permission_id),
    constraint Role_Permission_Permission__fk
        foreign key (role_id) references role (id),
    constraint Role_Permission_Permission__fk2
        foreign key (permission_id) references permission (id)

);

insert role (name)
values ('Admin');

insert role_permission(role_id, permission_id)
values (1, 5);