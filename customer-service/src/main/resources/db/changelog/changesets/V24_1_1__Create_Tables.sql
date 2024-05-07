create table if not exists t_customers
(
    id           uuid primary key,
    c_firstname  varchar(255) not null,
    c_lastname   varchar(255) not null,
    c_username   varchar(255) not null unique,
    c_email      varchar(255) not null unique,
    c_gender     varchar(6)   not null,
    c_avatar     varchar      null
);