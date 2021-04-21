INSERT INTO user (user_id, email, login, name, password, phone, role, surname)
values (null, null, 'admin', null, '$2y$12$Mnq.GK.kwlPkPt9HZW9KI.HKQWOnSyVgweioWQR45nrMGIaq8YObm', null, 'ADMIN', null),
       (null, 'onliner@gmail.com', 'nintendo233', 'Oleg',
        '$2y$12$rKBORZNjgrMRqwEvf2LBB.Bs2hooPbYUA3NJB6ef6LQZEXyEwmsfq', '+375293542346', 'USER', 'Tereshkov'),
       (null, 'zevs@gmail.com', 'anna', 'Anna', '$2y$12$42SzxackWGwf6gtuhHm3a.L2C7xxaESroyDdj7MI8udDY.Jc1jXbW',
        '+375292543246', 'USER', 'Tereshkova');



INSERT INTO project (project_id, create_time, description, end_time, owner_user_id, project_status, short_name, title)
values (null, '2021-04-16 13:45:43', 'Project 1', null, 2, 'NOT_STARTED', 'ProjDip', 'Project diplom'),
       (null, '2020-04-11 13:30:43', 'Project 2', '2021-04-13 12:30:43', 2, 'COMPLETED', 'Magaz', 'Magazine'),
       (null, '2021-01-03 03:45:43', 'Project 3', null, 2, 'NOT_STARTED', 'Shop1', 'Online Shop'),
       (null, '2021-01-03 06:45:43', 'Project 4', null, 3, 'NOT_STARTED', 'Shop2', 'Dog Shop'),
       (null, '2021-06-03 11:45:43', 'Project 5', null, 2, 'POSTPONED', 'Shop3', 'Toy shop');



INSERT INTO project_users(user_id, project_id)
values (2, 4),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 5);



