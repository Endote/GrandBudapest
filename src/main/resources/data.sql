insert into person (id, name, phone, since, surname) values
(9999, 'Jan', '123123123', '2020-01-01', 'Kowalski');

insert into hotel (id, name) values
    (1, 'Grand Budapest'),
    (2, 'Intercontinental');

insert into room (id, room_number, owner_id) values
    (1, 101, 1),
    (2, 102, 1),
    (3, 103, 1),
    (4, 101, 2),
    (5, 102, 2),
    (6, 103, 2);

insert into employee (id, name, surname) values
    (1, 'Adam', 'Dam');

insert into reservation (id, start_date, end_date,  ordered_by, total_price, room_id, taken_by) values
(1, '2020-10-10', '2020-12-12', 9999, 100, 1, 1),
(2, '2022-10-10', '2022-12-12', 9999, 100, 2, 1),
(3, '2020-10-10', '2020-12-12', 9999, 100, 4, 1),
(4, '2021-10-10', '2021-12-12', 9999, 100, 5, 1),
(5, '2022-10-10', '2022-12-12', 9999, 100, 5, 1);


