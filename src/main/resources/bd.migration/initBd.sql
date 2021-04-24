
 drop table if exists comments CASCADE;
 drop table if exists user CASCADE;
 drop table if exists project CASCADE;
 drop table if exists project_users CASCADE;
 drop table if exists task CASCADE;

 drop table if exists users_task CASCADE;
 create table comments (comments_id bigint generated by default as identity, primary key (comments_id));
 create table user (user_id bigint generated by default as identity, email varchar(255), login varchar(255), name varchar(255), password varchar(255), phone varchar(255), role varchar(255), surname varchar(255), primary key (user_id));

create table project (project_id bigint generated by default as identity, create_time varchar(255), description varchar(255), end_time varchar(255), project_status varchar(255), short_name varchar(255), title varchar(255), owner_user_id bigint, primary key (project_id));
 create table project_users (user_id bigint not null, project_id bigint not null);
 create table task (task_id bigint generated by default as identity, date_end varchar(255), date_start varchar(255), description varchar(255), priority varchar(255), task_status varchar(255), title varchar(255), project_id bigint, primary key (task_id));
 create table users_task (user_id bigint not null, task_id bigint not null, primary key (task_id, user_id));





-- drop table if exists comments CASCADE;
-- drop table if exists project CASCADE;
-- drop table if exists project_users CASCADE;
-- drop table if exists task CASCADE;
-- drop table if exists task_users_task CASCADE;
-- drop table if exists user CASCADE;
-- drop table if exists user_tasks CASCADE;

-- create table comments
-- (
--     comments_id bigint generated by default as identity,
--     primary key (comments_id)
-- );
-- create table project
-- (
--     project_id     bigint generated by default as identity,
--     create_time    varchar(255),
--     description    varchar(255),
--     end_time       varchar(255),
--     project_status varchar(255),
--     short_name     varchar(255),
--     title          varchar(255),
--     owner_user_id  bigint,
--     primary key (project_id)
-- );

 INSERT INTO user (user_id, email, login, name, password, phone, role, surname)
 values (null, null, 'admin', null, '$2y$12$Mnq.GK.kwlPkPt9HZW9KI.HKQWOnSyVgweioWQR45nrMGIaq8YObm', null, 'ADMIN', null),
        (null, 'onliner@gmail.com', 'nintendo233', 'Oleg',
         '$2y$12$rKBORZNjgrMRqwEvf2LBB.Bs2hooPbYUA3NJB6ef6LQZEXyEwmsfq', '+375293542346', 'USER', 'Tereshkov'),
        (null, 'zevs@gmail.com', 'anna', 'Anna', '$2y$12$42SzxackWGwf6gtuhHm3a.L2C7xxaESroyDdj7MI8udDY.Jc1jXbW',
         '+375292543246', 'USER', 'Tereshkova');

INSERT INTO project (project_id, create_time, description, end_time, project_status, short_name, title, owner_user_id)
values (null, '2021-04-16 13:45:43', 'Project 1', null, 'NOT_STARTED', 'ProjDip', 'Project diplom', 2),
       (null, '2020-04-11 13:30:43', 'Project 2', null, 'COMPLETED', 'Magaz', 'Magazine', 2),
       (null, '2021-01-03 03:45:43', 'Project 3', null, 'NOT_STARTED', 'Shop1', 'Online Shop', 2),
       (null, '2021-01-03 06:45:43', 'Project 4', null, 'NOT_STARTED', 'Shop2', 'Dog Shop', 3),
       (null, '2021-06-03 11:45:43', 'Project 5', null, 'POSTPONED', 'Shop3', 'Toy shop', 2);


-- create table project_users
-- (
--     user_id    bigint not null,
--     project_id bigint not null
-- );
INSERT INTO project_users(user_id, project_id)
values (2, 4),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 5);

-- create table task
-- (
--     task_id     bigint generated by default as identity,
--     date_end    varchar(255),
--     date_start  varchar(255),
--     description varchar(255),
--     priority    varchar(255),
--     task_status varchar(255),
--     title       varchar(255),
--     project_id  bigint,
--     primary key (task_id)
-- );

INSERT INTO task (task_id, date_end, date_start, description, priority, task_status, title, project_id)
values (null, null, '2020-04-11 13:30:43', 'TASK1DESCRIPTION', 'LOW', 'NOT_STARTED', 'TITLE1TASK1', 1),
       (null, null, '2020-04-11 13:30:43', 'TASK2DESCRIPTION', 'LOW', 'NOT_STARTED', 'TITLE2TASK2', 1),
       (null, null, '2020-04-11 13:30:43', 'TASK3DESCRIPTION', 'LOW', 'NOT_STARTED', 'TITLE3TASK3', 1),
       (null, null, '2020-04-11 13:30:43', 'TASK4DESCRIPTION', 'LOW', 'NOT_STARTED', 'TITLE4TASK4', 4),
       (null, null, '2020-04-11 13:30:43', 'TASK5DESCRIPTION', 'LOW', 'NOT_STARTED', 'TITLE5TASK5', 4);
-- create table task_users_task
-- (
--     task_task_id       bigint not null,
--     users_task_user_id bigint not null,
--     primary key (task_task_id, users_task_user_id)
-- );
-- INSERT INTO task_users_task (task_task_id, users_task_user_id)
-- values (1, 3),
--        (2, 3),
--        (3, 3),
--        (4, 2),
--        (5, 2);

-- create table user
-- (
--     user_id  bigint generated by default as identity,
--     email    varchar(255),
--     login    varchar(255),
--     name     varchar(255),
--     password varchar(255),
--     phone    varchar(255),
--     role     varchar(255),
--     surname  varchar(255),
--     primary key (user_id)
-- );


-- create table user_tasks
-- (
--     user_user_id  bigint not null,
--     tasks_task_id bigint not null
-- );
INSERT INTO users_task(user_id, task_id)
values (3, 1),
       (3, 2),
       (3, 3),
       (2, 4),
       (2, 5);

 alter table project add constraint FKs1xrbvp32s3ue8wb75gogdcqr foreign key (owner_user_id) references user;
 alter table project_users add constraint FK9at0ei37rls7vd2m6sh92668h foreign key (project_id) references project;
 alter table project_users add constraint FKswc4g4h18lbxsl9obvo97lnbl foreign key (user_id) references user;
 alter table task add constraint FKk8qrwowg31kx7hp93sru1pdqa foreign key (project_id) references project;
 alter table users_task add constraint FKt52hbwf1wddrdy2lr9fi14fap foreign key (task_id) references task;
 alter table users_task add constraint FKdgplb0lf513pemo1nld6l4wk6 foreign key (user_id) references user;

-- alter table project
--     add constraint FKs1xrbvp32s3ue8wb75gogdcqr foreign key (owner_user_id) references user;
-- alter table project_users
--     add constraint FK9at0ei37rls7vd2m6sh92668h foreign key (project_id) references project;
-- alter table project_users
--     add constraint FKswc4g4h18lbxsl9obvo97lnbl foreign key (user_id) references user;
-- alter table task
--     add constraint FKk8qrwowg31kx7hp93sru1pdqa foreign key (project_id) references project;
-- alter table task_users_task
--     add constraint FK6yh1pvp8bashsaoi4oq0jbrr foreign key (users_task_user_id) references user;
-- alter table task_users_task
--     add constraint FKcp59epaofy4bx7cf9i00o9tsx foreign key (task_task_id) references task;
-- alter table user_tasks
--     add constraint FK7a9a4c8vtkncd2e0m049gtjet foreign key (tasks_task_id) references task;
-- alter table user_tasks
--     add constraint FK7j5wjxr4r8hmty7eess5wfhbq foreign key (user_user_id) references user;