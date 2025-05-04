insert into countries(country_id, name, continent) values
(1, 'Italy', 'Europe'),
(2, 'Spain', 'Europe'),
(3, 'England', 'Europe'),
(4, 'Macedonia', 'Europe'),
(5, 'United States', 'North America');

insert into hosts(host_id, name, surname, country_country_id) values
(1, 'Lorenzo', 'Zurzolo', 1),
(2, 'Penelope', 'Cruz', 2),
(3, 'Cillian', 'Murphy', 3),
(4, 'Marija', 'Kostadinovikj', 4),
(5, 'Julia', 'Roberts', 5);

insert into guests(guest_id, name, surname, country_country_id) values
(1, 'Ana', 'Todorova', 2),
(2, 'Marko', 'Markovski', 5),
(3, 'Petar', 'Petrovski', 4),
(4, 'Kristijan', 'Kristijanoski', 3),
(5, 'Stefan', 'Stefanovski', 1);

insert into accommodations(id, name, category, host_host_id, num_rooms, is_rented) values
(1, 'Smestuvanje 1', 'ROOM', 1, 1, false),
(2, 'Smestuvanje 2', 'APARTMENT', 2, 1, false),
(3, 'Smestuvanje 3', 'FLAT', 3, 2, false),
(4, 'Smestuvanje 4', 'HOTEL', 4, 3, false),
(5, 'Smestuvanje 5', 'MOTEL', 5, 2, false),
(6, 'Smestuvanje 6', 'HOUSE', 2, 1, false),
(7, 'Smestuvanje 7', 'HOTEL', 1, 4, false),
(8, 'Smestuvanje 8', 'APARTMENT', 3, 3, false),
(9, 'Smestuvanje 9', 'MOTEL', 5, 1, false),
(10,'Smestuvanje 10','APARTMENT',4 ,4 ,false);
