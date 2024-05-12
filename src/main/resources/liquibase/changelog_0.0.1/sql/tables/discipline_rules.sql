create table discipline_rules (
  id bigint not null,
   guild_id bigint,
   reason text,
   discipline_points bigint,
   constraint pk_discipline_rules primary key (id)
);

alter table discipline_rules add constraint fk_discipline_rules_on_guild foreign key (guild_id) references guilds (id);