create table permission
(
    id int auto_increment not null ,
    name ENUM('CREATE_USER', 'UPDATE_USER', 'DELETE_USER', 'LIST_USERS', 'ALL') not null,
    constraint Permission_pk
        primary key (id),
    constraint Permission_name_uindex
        unique (name)
);

insert into permission(name)
VALUES
('CREATE_USER'),
('UPDATE_USER'),
('LIST_USERS'),
('DELETE_USER'),
('ALL')