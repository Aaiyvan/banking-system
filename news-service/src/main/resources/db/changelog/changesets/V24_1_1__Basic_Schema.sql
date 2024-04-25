create table if not exists news
(
    id             varchar(36) primary key,
    title          varchar(255) not null,
    content        text,
    published_date timestamp null
);

create table if not exists news_tags
(
    news_id varchar(36) not null,
    tag     varchar(255) not null,
    primary key (news_id, tag),
    constraint fk_news_tags_news foreign key (news_id) references news (id) on delete cascade on update no action
);

create table if not exists file_attachment
(
    id        varchar(36) primary key,
    file_name varchar,
    source    varchar,
    url       varchar,
    news_id   varchar(36),
    foreign key (news_id) references news (id)
);
