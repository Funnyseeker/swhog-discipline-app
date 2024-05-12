create table players (
    id bigint not null,
    name text,
    discord_nick_name varchar(255),
    swgoh_ally_code varchar(255),
    constraint pk_players primary key (id)
);