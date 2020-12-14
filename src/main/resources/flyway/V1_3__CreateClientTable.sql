create table client
(
    id     varchar(36)        not null,
    secret varchar(60)        not null,
    constraint Client_pk
        primary key (id)
);

insert into client (id, secret)
values ('59489bdb-b586-403e-8789-d014cd5f5906', '$2y$10$GSaZQVaOR1A1sn82TgNx1e9BgQDl4QDlstjTuprw.uCTkUQQd5XZG');