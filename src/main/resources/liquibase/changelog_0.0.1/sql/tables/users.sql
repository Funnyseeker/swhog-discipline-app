create table users (
    id bigint not null,
    username varchar(255),
    pwd varchar(255),
    salt bytea,
    role varchar(255),
    discord_nick varchar(255),
    swgoh_ally_code varchar(255),
    comment varchar(255),
    guild_id bigint,
    constraint pk_users primary key (id)
);

alter table users add constraint fk_users_on_guild foreign key (guild_id) references guilds (id);