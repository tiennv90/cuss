/**
 * CREATE Script for init of DB
 */

insert into flight (id, available_seats, departure_time, flight_code, creation_timestamp, update_timestamp) values (1, 200, now(), 'ENG234', now(), now());
insert into flight (id, available_seats, departure_time, flight_code, creation_timestamp, update_timestamp) values (2, 250, now(), 'GER231', now(), now());
insert into flight (id, available_seats, departure_time, flight_code, creation_timestamp, update_timestamp) values (3, 250, now(), 'FR2312', now(), now());

insert into passenger (id, seat_number, flight_id, status, first_name, last_name, pnr, creation_timestamp, update_timestamp) values (1, 'A122', 1,'WAITING_FOR_CHECK_IN', 'John', 'Doe', 'RRXKCV', now(), now());
insert into passenger (id, seat_number, flight_id, status, first_name, last_name, pnr, creation_timestamp, update_timestamp) values (2, 'A145', 1,'WAITING_FOR_CHECK_IN', 'Thomas', 'Francesco', 'KVGSDF', now(), now());
insert into passenger (id, seat_number, flight_id, status, first_name, last_name, pnr, creation_timestamp, update_timestamp) values (3, 'A112', 1,'WAITING_FOR_CHECK_IN', 'Barry', 'Gerald', 'ZFKGRB', now(), now());
insert into passenger (id, seat_number, flight_id, status, first_name, last_name, pnr, creation_timestamp, update_timestamp) values (4, 'A145', 2,'CHECK_IN_COMPLETED', 'Gordon', 'Edward', 'BVBSF', now(), now());
insert into passenger (id, seat_number, flight_id, status, first_name, last_name, pnr, creation_timestamp, update_timestamp) values (5, 'A223', 2,'CHECK_IN_COMPLETED', 'Linda', 'Edward', 'AAGFS', now(), now());
insert into passenger (id, seat_number, flight_id, status, first_name, last_name, pnr, creation_timestamp, update_timestamp) values (6, 'A244', 2,'WAITING_FOR_CHECK_IN', 'Logan', 'James', 'MRKFS', now(), now());

INSERT INTO baggage (id, passenger_id, weight, tag_code, status, creation_timestamp, update_timestamp)
SELECT
    nextval('baggage_seq'),
    (floor(random()*3) + 1)::int,
    round((random()*30 + 5)::numeric, 2),
    'TAG_' || lpad(gs::text, 5, '0'),
    CASE
        WHEN random() < 0.33 THEN 'CHECKED'
        WHEN random() < 0.66 THEN 'LOST'
        ELSE 'IN_TRANSIT'
    END,
    now(),
    now()
FROM generate_series(1, 10000) as gs;

SELECT setval('baggage_seq', COALESCE((SELECT MAX(id) FROM baggage), 1));