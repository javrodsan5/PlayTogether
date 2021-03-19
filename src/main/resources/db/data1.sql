INSERT INTO users VALUES(1, 1, 'Antonio', '1999-02-20', 'antonio@gmail.com', 'password123', '659659654', 'antonio12', 1, 1);
INSERT INTO users VALUES(1, 1, 'Maria', '1989-05-21', 'maria1@gmail.com', 'password133', '666555222', 'maria23', 1, 2);

INSERT INTO user_type VALUES(1, 1, Basico);
INSERT INTO user_type VALUES(2, 1, Premium);

INSERT INTO teams VALUES(1, 1, 'Equipo1', 1);
INSERT INTO teams VALUES(2, 1, 'Equipo2', 1);

INSERT INTO teamsteam_users VALUES(1, 1);
INSERT INTO teamsteam_users VALUES(2, 2);

INSERT INTO statistics VALUES(1, 1, 1, 1);

INSERT INTO sports VALUES(1, 1, 'Tenis', 1)
INSERT INTO sports VALUES(2, 1, 'Futbol 11', 1)
INSERT INTO sports VALUES(3, 1, 'Futbol sala', 1)
INSERT INTO sports VALUES(4, 1, 'Futbol 7', 1)
INSERT INTO sports VALUES(5, 1, 'Padel', 1)
INSERT INTO sports VALUES(6, 1, 'Baloncesto', 1)
INSERT INTO sports VALUES(7, 1, 'Running', 2)
INSERT INTO sports VALUES(8, 1, 'Voleibol', 1)
INSERT INTO sports VALUES(9, 1, 'Hockey', 1)
INSERT INTO sports VALUES(10, 1, 'Gimnasio', 2)
INSERT INTO sports VALUES(11, 1, 'Yoga', 2)
INSERT INTO sports VALUES(12, 1, 'Senderismo', 2)
INSERT INTO sports VALUES(13, 1, 'Balonmano', 1)

INSERT INTO sport_type VALUES(1, 1, 'Equipo');
INSERT INTO sport_type VALUES(2, 1, 'Individual');

INSERT INTO payments VALUES(1, 1, 2.5, '2021/02/15 11:00:00', 1, 1);
INSERT INTO payments VALUES(2, 1, 5.0, '2021/01/16 11:45:00', 1, 2);

INSERT INTO payment_type VALUES(1, 1, 'Basico');
INSERT INTO payment_type VALUES(2, 1, 'Premium');


INSERT INTO meetings VALUES(1, 1, 'Quedada1', 'Polideportivo Ciudad Jardin', 'Sevilla', '2021/06/15 11:00:00', 'Breve descripcion', 1);
INSERT INTO meetings VALUES(2, 1, 'Quedada2', 'Pistas Sevilla', 'Sevilla', '2021/04/15 11:20:00', 'Breve descripcion', 3);

INSERT INTO matches VALUES(1, 1, '2021/06/15 11:00:00', 1, 1, 2);
INSERT INTO matches VALUES(2, 1, '2021/05/08 20:32:00', 1, 1, 2);

INSERT INTO championships VALUES(1, 1, 'Torneo1', 'Sevilla', 'Descripcion del torneo', '2021/06/15 12:00:00', '2021/06/15 11:00:00', 1);
INSERT INTO championships VALUES(2, 1, 'Torneo2', 'Sevilla', 'Nuevo torneo', '2021/04/06 16:45:00', '2021/04/06 18:45:00', 4);
