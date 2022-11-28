-- Shops --

insert into shops (id, name, created_at, in_vacations)
values (1, 'Boutique 1', '2021-11-28', false);

insert into opening_hours (id, day, open_at, close_at)
values (2, 1, '09:00:00', '18:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (1, 2);

insert into opening_hours (id, day, open_at, close_at)
values (3, 2, '09:00:00', '18:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (1, 3);

insert into opening_hours (id, day, open_at, close_at)
values (4, 5, '08:00:00', '17:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (1, 4);


insert into shops (id, name, created_at, in_vacations)
values (5, 'Boutique 2', '2012-06-25', true);

insert into opening_hours (id, day, open_at, close_at)
values (6, 1, '08:45:00', '17:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (5, 6);

insert into opening_hours (id, day, open_at, close_at)
values (7, 2, '08:45:00', '17:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (5, 7);

insert into opening_hours (id, day, open_at, close_at)
values (8, 3, '08:45:00', '18:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (5, 8);

insert into opening_hours (id, day, open_at, close_at)
values (9, 4, '08:45:00', '12:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (5, 9);


insert into shops (id, name, created_at, in_vacations)
values (10, 'Boutique 3', '2022-01-09', false);

insert into opening_hours (id, day, open_at, close_at)
values (11, 6, '08:45:00', '20:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (10, 11);

insert into opening_hours (id, day, open_at, close_at)
values (12, 7, '08:15:00', '20:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (10, 12);


insert into shops (id, name, created_at, in_vacations)
values (13, 'Boutique 4', '2020-04-05', false);

insert into opening_hours (id, day, open_at, close_at)
values (14, 4, '10:00:00', '20:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (13, 14);

insert into opening_hours (id, day, open_at, close_at)
values (15, 6, '10:00:00', '20:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (13, 15);

insert into opening_hours (id, day, open_at, close_at)
values (16, 7, '10:00:00', '20:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (13, 16);


insert into shops (id, name, created_at, in_vacations)
values (17, 'Boutique 5', '2017-12-15', true);

insert into opening_hours (id, day, open_at, close_at)
values (18, 1, '08:00:00', '21:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (17, 18);

insert into opening_hours (id, day, open_at, close_at)
values (19, 3, '08:00:00', '21:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (17, 19);

insert into opening_hours (id, day, open_at, close_at)
values (20, 6, '08:00:00', '21:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (17, 20);


insert into shops (id, name, created_at, in_vacations)
values (21, 'Boutique 6', '2010-01-03', true);

insert into opening_hours (id, day, open_at, close_at)
values (22, 1, '09:00:00', '17:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (21, 22);

insert into opening_hours (id, day, open_at, close_at)
values (23, 2, '09:00:00', '17:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (21, 23);

insert into opening_hours (id, day, open_at, close_at)
values (24, 3, '09:00:00', '17:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (21, 24);

insert into opening_hours (id, day, open_at, close_at)
values (25, 4, '09:00:00', '17:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (21, 25);

insert into opening_hours (id, day, open_at, close_at)
values (26, 5, '09:00:00', '17:30:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (21, 26);


insert into shops (id, name, created_at, in_vacations)
values (27, 'Boutique 7', '2015-08-20', false);

insert into opening_hours (id, day, open_at, close_at)
values (28, 1, '09:15:00', '17:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (27, 28);

insert into opening_hours (id, day, open_at, close_at)
values (29, 5, '08:15:00', '21:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (27, 29);


insert into shops (id, name, created_at, in_vacations)
values (30, 'Boutique 8', '2019-06-28', false);

insert into opening_hours (id, day, open_at, close_at)
values (31, 2, '08:45:00', '17:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (30, 31);

insert into opening_hours (id, day, open_at, close_at)
values (32, 3, '08:45:00', '17:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (30, 32);

insert into opening_hours (id, day, open_at, close_at)
values (33, 6, '08:45:00', '17:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (30, 33);


insert into shops (id, name, created_at, in_vacations)
values (34, 'Boutique 9', '2016-05-16', false);

insert into opening_hours (id, day, open_at, close_at)
values (35, 2, '10:30:00', '16:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (34, 35);

insert into opening_hours (id, day, open_at, close_at)
values (36, 3, '10:30:00', '16:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (34, 36);

insert into opening_hours (id, day, open_at, close_at)
values (37, 5, '10:30:00', '16:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (34, 37);

insert into opening_hours (id, day, open_at, close_at)
values (38, 6, '10:30:00', '16:45:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (34, 38);


insert into shops (id, name, created_at, in_vacations)
values (39, 'Boutique 10', '2015-02-17', false);

insert into opening_hours (id, day, open_at, close_at)
values (40, 7, '08:00:00', '22:00:00');
insert into shops_opening_hours (shop_id, opening_hours_id) values (39, 40);


-- Categories --

insert into categories (id, name)
values (41, 'Nourriture');

insert into categories (id, name)
values (42, 'Multimédia');

insert into categories (id, name)
values (43, 'Vêtement');

insert into categories (id, name)
values (44, 'Chaussure');

insert into categories (id, name)
values (45, 'Electroménager');

insert into categories (id, name)
values (46, 'Boisson');

insert into categories (id, name)
values (47, 'Bio');

insert into categories (id, name)
values (48, 'Cuisine');

insert into categories (id, name)
values (49, 'Salle de bain');

insert into categories (id, name)
values (50, 'Meuble');

insert into categories (id, name)
values (51, 'Maquillage');

insert into categories (id, name)
values (52, 'Parfum');


-- Produits --



-- Hibernate Sequence --

create sequence hibernate_sequence start 53 increment 1;