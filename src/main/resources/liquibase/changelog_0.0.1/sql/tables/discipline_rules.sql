create table discipline_rules (
    id bigint not null,
    reason text,
    discipline_points bigint,
    constraint pk_discipline_rules primary key (id)
);