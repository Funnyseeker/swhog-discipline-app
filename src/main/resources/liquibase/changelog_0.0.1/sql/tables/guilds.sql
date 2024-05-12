create table guilds (
    id bigint not null,
    url text,
    name text,
    last_sync_date timestamp without time zone,
    constraint pk_guilds primary key (id)
);