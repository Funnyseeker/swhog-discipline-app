create table player_discipline_history (
  id bigint not null,
   date timestamptz,
   player_id bigint,
   rule_id bigint,
   discipline_points bigint,
   constraint pk_player_discipline_history primary key (id)
);

alter table player_discipline_history add constraint fk_player_discipline_history_on_player foreign key (player_id) references players (id);

alter table player_discipline_history add constraint fk_player_discipline_history_on_rule foreign key (rule_id) references discipline_rules (id);