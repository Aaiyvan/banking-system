create schema if not exists customer;

create table if not exists customer.t_customers
(
    id           varchar(36) primary key,
    c_firstname  varchar(255) not null,
    c_lastname   varchar(255) not null,
    c_username   varchar(255) not null unique,
    c_email      varchar(255) not null unique,
    c_gender     varchar(6)   not null,
    c_account_id varchar(36)  null
);