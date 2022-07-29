create table if not exists users(
    id  bigserial not null,
    name varchar(120) not null,
    email varchar(40) not null,
    primary key(id)
)