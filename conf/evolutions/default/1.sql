# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table personn (
  id                        bigint not null,
  name                      varchar(100),
  last_name                 varchar(100),
  date_of_birth             timestamp,
  email                     varchar(255),
  favorite_db               integer,
  notes                     varchar(5000),
  constraint pk_personn primary key (id))
;

create sequence personn_seq;




# --- !Downs

drop table if exists personn cascade;

drop sequence if exists personn_seq;

