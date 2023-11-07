create database users;

\c users

create schema users_scheme;

create sequence users_scheme.users_id_seq as integer
    increment by 1
    start with 1
;

create table users_scheme.users
(
    id integer not null default nextval('users_scheme.users_id_seq')
,   first_name character varying
,   last_name character varying
,   middle_name character varying
,   sex character not null default 'M'
,   town character varying
,   email character varying
,   birthday date
,   constraint users_pk primary key (id)
);


create table users_scheme.followers
(
    follower_id integer
,   publisher_id integer
,   constraint followers_pk primary key (follower_id, publisher_id)
);


alter table users_scheme.users
  add constraint ck_users_sex check (sex in ('M', 'F'));

create index idx_users_sex on users_scheme.users using hash (sex);
create index idx_users_town on users_scheme.users (town);
create index idx_users_sex_town on users_scheme.users (sex, town);

