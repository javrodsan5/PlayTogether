INSERT INTO user_type VALUES(1, 'Básico');
INSERT INTO user_type VALUES(2, 'Premium');

INSERT INTO sport_type VALUES(1, 'Equipo');
INSERT INTO sport_type VALUES(2, 'Individual');

INSERT INTO sports VALUES(1, 'Tenis', 1, 1);
INSERT INTO sports VALUES(2, 'Fútbol 11', 11, 1);
INSERT INTO sports VALUES(3, 'Fútbol sala', 5, 1);
INSERT INTO sports VALUES(4, 'Fútbol 7', 7, 1);
INSERT INTO sports VALUES(5, 'Pádel', 2, 1);
INSERT INTO sports VALUES(6, 'Baloncesto', 5, 1);
INSERT INTO sports VALUES(7, 'Running', 1, 2);
INSERT INTO sports VALUES(8, 'Voleibol', 6, 1);
INSERT INTO sports VALUES(9, 'Hockey', 6, 1);
INSERT INTO sports VALUES(10, 'Gimnasio', 1, 2);
INSERT INTO sports VALUES(11, 'Yoga', 1, 2);
INSERT INTO sports VALUES(12, 'Senderismo', 1, 2);
INSERT INTO sports VALUES(13, 'Balonmano', 7, 1);
INSERT INTO sports VALUES(14, 'Ciclismo', 1, 2);
INSERT INTO sports VALUES(15, 'Golf', 1, 1);
INSERT INTO sports VALUES(16, 'Natación', 1, 2);
INSERT INTO sports VALUES(17, 'Karting', 1, 1);
INSERT INTO sports VALUES(18, 'Pilates', 1, 2);

INSERT INTO meetings VALUES(1, 'Polideportivo Ciudad Jardin', 'Sevilla', '2021/06/15 11:00:00', 'Breve descripcion', 2, 1);
INSERT INTO meetings VALUES(2, 'Pistas Sevilla 1', 'Sevilla', '2021/04/15 11:20:00', 'Breve descripcion', 2, 1);
INSERT INTO meetings VALUES(3, 'Pistas Sevilla 2', 'Sevilla', '2021/05/23 13:30:00', 'Breve descripcion', 4, 1);
INSERT INTO meetings VALUES(4, 'Pistas Sevilla 3', 'Sevilla', '2021/04/30 11:20:00', 'Breve descripcion', 2, 1);
INSERT INTO meetings VALUES(5, 'Pistas Sevilla 4', 'Sevilla', '2021/05/11 17:20:00', 'Breve descripcion', 2, 1);
INSERT INTO meetings VALUES(6, 'Pistas Sevilla 5', 'Sevilla', '2021/04/20 11:45:00', 'Breve descripcion', 4, 1);
INSERT INTO meetings VALUES(7, 'Pistas Sevilla 6', 'Sevilla', '2021/04/15 09:20:00', 'Breve descripcion', 4, 1);

INSERT INTO championships VALUES(1, 'Torneo1', 'Sevilla', 'Descripcion del torneo', '2021/06/25 12:00:00', 16, '2021/06/15 11:00:00', 1);
INSERT INTO championships VALUES(2, 'Torneo2', 'Sevilla', 'Nuevo torneo', '2021/04/12 16:45:00', 16, '2021/04/06 18:45:00', 1);
INSERT INTO championships VALUES(3, 'Torneo3', 'Sevilla', 'Descripcion del torneo', '2021/05/23 12:00:00', 16, '2021/05/15 14:00:00', 1);
INSERT INTO championships VALUES(4, 'Torneo4', 'Sevilla', 'Descripcion del torneo', '2021/05/15 12:00:00', 16, '2021/04/15 11:00:00', 1);
INSERT INTO championships VALUES(5, 'Torneo5', 'Sevilla', 'Descripcion del torneo', '2021/06/15 12:00:00', 16, '2021/05/15 11:00:00', 1);
INSERT INTO championships VALUES(6, 'Torneo6', 'Sevilla', 'Descripcion del torneo', '2021/04/17 12:00:00', 16, '2021/04/16 11:00:00', 1);
INSERT INTO championships VALUES(7, 'Torneo7', 'Sevilla', 'Descripcion del torneo', '2021/05/15 12:00:00', 16, '2021/04/15 11:00:00', 2);
INSERT INTO championships VALUES(8, 'Torneo8', 'Sevilla', 'Descripcion del torneo', '2021/05/15 12:00:00', 16, '2021/04/15 11:00:00', 2);

INSERT INTO teams VALUES(1, 'Equipo1', 1, 1);
INSERT INTO teams VALUES(2, 'Equipo2', 1, 1);
INSERT INTO teams VALUES(3, 'Equipo3', 1, 2);
INSERT INTO teams VALUES(4, 'Equipo4', 1, 2);
INSERT INTO teams VALUES(5, 'Equipo5', 1, 2);
INSERT INTO teams VALUES(6, 'Equipo6', 1, 3);
INSERT INTO teams VALUES(7, 'Equipo7', 1, 3);
INSERT INTO teams VALUES(8, 'Equipo8', 1, 8);

INSERT INTO matches VALUES(1, '2021/06/15 11:00:00', 2, 1, 2, 1, 1, 1, 2);

INSERT INTO payment_type VALUES(1, 'Básico');
INSERT INTO payment_type VALUES(2, 'Premium');

INSERT INTO users VALUES(1, 'Antonio', '1999-02-20', 'antonio@gmail.com', 'password123', '659659654', 'antonio12', 1);
INSERT INTO users VALUES(2, 'Maria', '1989-05-21', 'maria1@gmail.com', 'password133', '666555222', 'maria23', 2);

INSERT INTO statistics VALUES(1, 1, 1);
INSERT INTO statistics VALUES(2, 2, 2);

INSERT INTO payments VALUES(1, 2.5, '2021/02/15 11:00:00', 1, 1);
INSERT INTO payments VALUES(2, 5.0, '2021/01/16 11:45:00', 1, 2);

INSERT INTO teams_participants VALUES(1, 2);
INSERT INTO teams_participants VALUES(2, 1);

