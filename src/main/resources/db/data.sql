INSERT INTO user_type VALUES(1, 1, 'Basico');
INSERT INTO user_type VALUES(2, 1, 'Premium');

INSERT INTO sport_type VALUES(1, 1, 'Equipo');
INSERT INTO sport_type VALUES(2, 1, 'Individual');

INSERT INTO sports VALUES(1, 1, 'Tenis', 1);
INSERT INTO sports VALUES(2, 1, 'Futbol 11', 1);
INSERT INTO sports VALUES(3, 1, 'Futbol sala', 1);
INSERT INTO sports VALUES(4, 1, 'Futbol 7', 1);
INSERT INTO sports VALUES(5, 1, 'Padel', 1);
INSERT INTO sports VALUES(6, 1, 'Baloncesto', 1);
INSERT INTO sports VALUES(7, 1, 'Running', 2);
INSERT INTO sports VALUES(8, 1, 'Voleibol', 1);
INSERT INTO sports VALUES(9, 1, 'Hockey', 1);
INSERT INTO sports VALUES(10, 1, 'Gimnasio', 2);
INSERT INTO sports VALUES(11, 1, 'Yoga', 2);
INSERT INTO sports VALUES(12, 1, 'Senderismo', 2);
INSERT INTO sports VALUES(13, 1, 'Balonmano', 1);
INSERT INTO sports VALUES(14, 1, 'Ciclismo', 2);
INSERT INTO sports VALUES(15, 1, 'Golf', 1);
INSERT INTO sports VALUES(16, 1, 'Natación', 2);
INSERT INTO sports VALUES(17, 1, 'Karting', 1);
INSERT INTO sports VALUES(18, 1, 'Pilates', 2);

INSERT INTO meetings VALUES(1, 1, 'Quedada1', 'Polideportivo Ciudad Jardin', 'Sevilla', '2021/06/15 11:00:00', 'Breve descripcion', 1);
INSERT INTO meetings VALUES(2, 1, 'Quedada2', 'Pistas Sevilla 1', 'Sevilla', '2021/04/15 11:20:00', 'Breve descripcion', 1);
INSERT INTO meetings VALUES(3, 1, 'Quedada3', 'Pistas Sevilla 2', 'Sevilla', '2021/05/23 13:30:00', 'Breve descripcion', 1);
INSERT INTO meetings VALUES(4, 1, 'Quedada4', 'Pistas Sevilla 3', 'Sevilla', '2021/04/30 11:20:00', 'Breve descripcion', 1);
INSERT INTO meetings VALUES(5, 1, 'Quedada5', 'Pistas Sevilla 4', 'Sevilla', '2021/05/11 17:20:00', 'Breve descripcion', 1);
INSERT INTO meetings VALUES(6, 1, 'Quedada6', 'Pistas Sevilla 5', 'Sevilla', '2021/04/20 11:45:00', 'Breve descripcion', 2);
INSERT INTO meetings VALUES(7, 1, 'Quedada7', 'Pistas Sevilla 6', 'Sevilla', '2021/04/15 09:20:00', 'Breve descripcion', 1);

INSERT INTO championships VALUES(1, 1, 'Torneo1', 'Sevilla', 'Descripcion del torneo', '2021/06/25 12:00:00', '2021/06/15 11:00:00', 1);
INSERT INTO championships VALUES(2, 1, 'Torneo2', 'Sevilla', 'Nuevo torneo', '2021/04/12 16:45:00', '2021/04/06 18:45:00', 1);
INSERT INTO championships VALUES(3, 1, 'Torneo3', 'Sevilla', 'Descripcion del torneo', '2021/05/23 12:00:00', '2021/05/15 14:00:00', 1);
INSERT INTO championships VALUES(4, 1, 'Torneo4', 'Sevilla', 'Descripcion del torneo', '2021/05/15 12:00:00', '2021/04/15 11:00:00', 1);
INSERT INTO championships VALUES(5, 1, 'Torneo5', 'Sevilla', 'Descripcion del torneo', '2021/06/15 12:00:00', '2021/05/15 11:00:00', 1);
INSERT INTO championships VALUES(6, 1, 'Torneo6', 'Sevilla', 'Descripcion del torneo', '2021/04/17 12:00:00', '2021/04/16 11:00:00', 1);
INSERT INTO championships VALUES(7, 1, 'Torneo7', 'Sevilla', 'Descripcion del torneo', '2021/05/15 12:00:00', '2021/04/15 11:00:00', 2);

INSERT INTO teams VALUES(1, 1, 'Equipo1', 1);
INSERT INTO teams VALUES(2, 1, 'Equipo2', 1);
INSERT INTO teams VALUES(3, 1, 'Equipo3', 2);
INSERT INTO teams VALUES(4, 1, 'Equipo4', 2);
INSERT INTO teams VALUES(5, 1, 'Equipo5', 2);
INSERT INTO teams VALUES(6, 1, 'Equipo6', 3);
INSERT INTO teams VALUES(7, 1, 'Equipo7', 3);

INSERT INTO matches VALUES(1, 1, '2021/06/15 11:00:00', 1, 1, 2);

INSERT INTO payment_type VALUES(1, 1, 'Basico');
INSERT INTO payment_type VALUES(2, 1, 'Premium');

INSERT INTO users VALUES(1, 1, 'Antonio', '1999-02-20', 'antonio@gmail.com', 'password123', '659659654', 'antonio12', 1);
INSERT INTO users VALUES(2, 1, 'Maria', '1989-05-21', 'maria1@gmail.com', 'password133', '666555222', 'maria23', 2);

INSERT INTO statistics VALUES(1, 1, 1, 1);
INSERT INTO statistics VALUES(2, 1, 2, 2);

INSERT INTO payments VALUES(1, 1, 2.5, '2021/02/15 11:00:00', 1, 1);
INSERT INTO payments VALUES(2, 1, 5.0, '2021/01/16 11:45:00', 1, 2);

INSERT INTO teams_participants VALUES(1, 1);

