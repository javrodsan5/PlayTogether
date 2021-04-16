INSERT INTO user_type VALUES(1, 'Básico');
INSERT INTO user_type VALUES(2, 'Premium');
INSERT INTO user_type VALUES(3, 'Administrador');

INSERT INTO users(username,password,enabled) VALUES ('antonio98','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'antonio98','usuario');
INSERT INTO authorities(id,username,authority) VALUES (2,'antonio98','premium');
INSERT INTO users(username,password,enabled) VALUES ('fernando98','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'fernando98','usuario');
INSERT INTO authorities(id,username,authority) VALUES (4,'fernando98','premium');
INSERT INTO users(username,password,enabled) VALUES ('marta98','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'marta98','usuario');

INSERT INTO users(username,password,enabled) VALUES ('antonio01','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'antonio01','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio02','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'antonio02','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio03','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'antonio03','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio04','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'antonio04','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio05','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'antonio05','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio06','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'antonio06','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio07','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'antonio07','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio08','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'antonio08','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio09','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'antonio09','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio10','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (15,'antonio10','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio11','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (16,'antonio11','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio12','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (17,'antonio12','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio13','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (18,'antonio13','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio14','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (19,'antonio14','premium');
INSERT INTO users(username,password,enabled) VALUES ('antonio15','Usuar10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (20,'antonio15','premium');

INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(1, 'Antonio', '1999-02-20', 'antonio@gmail.com', '654893274', 1, 10,'antonio98');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(2, 'Fernando', '1995-07-06', 'fernando1@gmail.com', '635897412', 2, 10,'fernando98');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(3, 'Marta', '1992-03-25', 'marta1@gmail.com', '954785123', 1, 10,'marta98');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(4, 'Antonio', '1999-02-20', 'antonio1@gmail.com', '654893270', 2,10,'antonio01');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(5, 'Antonio', '1999-02-20', 'antonio2@gmail.com', '654893271', 2,10,'antonio02');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(6, 'Antonio', '1999-02-20', 'antonio3@gmail.com', '654893272', 2,10,'antonio03');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(7, 'Antonio', '1999-02-20', 'antonio4@gmail.com', '654893273', 2,10,'antonio04');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(8, 'Antonio', '1999-02-20', 'antonio5@gmail.com', '654893574', 2,10,'antonio05');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(9, 'Antonio', '1999-02-20', 'antonio6@gmail.com', '654893275', 2,10,'antonio06');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(10, 'Antonio', '1999-02-20', 'antonio7@gmail.com', '654893276', 2,10,'antonio07');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(11, 'Antonio', '1999-02-20', 'antonio8@gmail.com', '654893277', 2,10,'antonio08');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(12, 'Antonio', '1999-02-20', 'antonio9@gmail.com', '654893278', 2,10,'antonio09');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(13, 'Antonio', '1999-02-20', 'antonio10@gmail.com', '654893279', 2,10,'antonio10');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(14, 'Antonio', '1999-02-20', 'antonio11@gmail.com', '654893214', 2,10,'antonio11');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(15, 'Antonio', '1999-02-20', 'antonio12@gmail.com', '654893224', 2,10,'antonio12');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(16, 'Antonio', '1999-02-20', 'antonio13@gmail.com', '654893234', 2,10,'antonio13');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(17, 'Antonio', '1999-02-20', 'antonio14@gmail.com', '654893244', 2,10,'antonio14');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(18, 'Antonio', '1999-02-20', 'antonio15@gmail.com', '654893254', 2,10,'antonio15');

INSERT INTO sport_type(id, name) VALUES(1, 'Equipo');
INSERT INTO sport_type(id, name) VALUES(2, 'Individual');

INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(1, 'Tenis', 1, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(2, 'Fútbol 11', 11, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(3, 'Fútbol sala', 5, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(4, 'Fútbol 7', 7, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(5, 'Pádel', 2, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(6, 'Baloncesto', 5, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(7, 'Running', 1, 2);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(8, 'Voleibol', 6, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(9, 'Hockey', 6, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(10, 'Gimnasio', 1, 2);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(11, 'Yoga', 1, 2);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(12, 'Senderismo', 1, 2);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(13, 'Balonmano', 7, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(14, 'Ciclismo', 1, 2);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(15, 'Golf', 1, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(16, 'Natación', 1, 2);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(17, 'Karting', 1, 2);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(18, 'Pilates', 1, 2);

INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id, creation_date) 
VALUES(1, 'Polideportivo Ciudad Jardin', 'Sevilla', '2021/06/15 11:00:00', 'Nos lo vamos a pasar genial! Apuntarse!', 2,1, 1, '2021/03/15');
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id, creation_date) 
VALUES(2, 'Polideportivo San Pablo', 'Sevilla', '2021/04/15 11:20:00', 'Vamos a realizar mucho deporte.', 2,1, 1, '2021/04/13');
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id, creation_date) 
VALUES(3, 'Tenis Betis', 'Sevilla', '2021/05/23 13:30:00', 'No dudéis en apuntarse!!', 4,2, 1, '2021/02/15');
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id, creation_date) 
VALUES(4, 'Club Santa Clara', 'Sevilla', '2021/04/30 11:20:00', 'Lo vamos a pasar increible!!', 2,3, 1, '2021/01/15');
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id, creation_date) 
VALUES(5, 'SADUS Bermejales', 'Sevilla', '2021/05/11 17:20:00', 'Apúntense amigos! Lo vamos a pasar muy bien!', 2,1, 1,'2020/12/15');
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id, creation_date) 
VALUES(6, 'Polideportivo Marbella Vice', 'Sevilla', '2021/04/20 11:45:00', 'Quedada para pasar un buen rato!', 4,2, 1, '2020/11/15');
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id, creation_date) 
VALUES(7, 'Polideportivo Los Caños', 'Sevilla', '2021/04/15 09:20:00', 'Mientras más seamos mejor! No dudéis en venir!', 4,3, 1, '2020/10/15');

INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(1, 'Torneo ATP', 'Sevilla', 'Torneo oficial de la ATP', '2021/06/25 12:00:00', 8, '2021/06/15 11:00:00', 1, 1);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(2, 'Torneo entre amigos', 'Sevilla', 'Torneo disputado entre amigos! Nos falta gente!', '2021/07/06 18:45:00', 4, '2021/04/06 18:45:00', 1, 1);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(3, 'Torneo Oficial', 'Sevilla', 'Torneo con jugadores semiprofesionales. Apuntarse sólo jugadores con ranking ATP.', '2021/05/23 12:00:00', 16, '2021/05/15 14:00:00', 1, 1);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(4, 'Torneo Pachanga', 'Sevilla', 'Torneo organizado para pasarlo bien con otras personas', '2021/05/15 12:00:00', 8, '2021/04/15 11:00:00', 1, 1);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(5, 'Torneo para jugadores avanzados', 'Sevilla', 'Torneo organizado para jugadores avanzandos.', '2021/06/15 12:00:00', 8, '2021/05/15 11:00:00', 1, 2);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(6, 'Torneo Profesional', 'Sevilla', 'Sólo jugadores profesionales!', '2021/04/17 12:00:00', 16, '2021/04/16 11:00:00', 1, 2);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(7, 'Torneo Antonio SL', 'Sevilla', 'Torneo patrocinado por Antonio SL! Apuntarse!', '2021/05/15 12:00:00', 4, '2021/04/15 11:00:00', 2, 1);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(8, 'Torneo US', 'Sevilla', 'Torneo para estudiantes de la US.', '2021/05/15 12:00:00', 4, '2021/04/15 11:00:00', 3, 1);

INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(1, 'West Jamon', 1, 2, 4);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(2, 'Shalcke Temeto', 1, 2, 5);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(3, 'Vodka Juniors', 1, 2, 6);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(4, 'Yayo Vallecano', 1, 2, 7);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(5, 'Jugadores de barrio', 1, 3, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(6, 'Unión Penosa', 1, 3, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(7, 'Nottingham Miedo', 1, 8, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(8, 'Equipazo', 1, 8, 4);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(9, 'Equipito', 1, 8, 5);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(10, 'Árbitro penalty', 1, 8, 2);

INSERT INTO teams_participants(teams_id, participants_id) VALUES(1, 4); 
INSERT INTO teams_participants(teams_id, participants_id) VALUES(2, 5); 
INSERT INTO teams_participants(teams_id, participants_id) VALUES(3, 6); 
INSERT INTO teams_participants(teams_id, participants_id) VALUES(4, 7); 

INSERT INTO matches VALUES(1, '2021/06/15 11:00:00', 2, 1, 2, 1, 1, 1, 2);

INSERT INTO statistics(id, sport_id, user_id) VALUES(1, 1, 1);
INSERT INTO statistics(id, sport_id, user_id) VALUES(2, 2, 2);

INSERT INTO pay_type(id, name) VALUES(1, 'Premium');
INSERT INTO pay_type(id, name) VALUES(2, 'Championship');
INSERT INTO pay_type(id, name) VALUES(3, 'Invitation');

INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(1, 2.0, '2021/01/16', 2, 8, 8, 3, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(2, 5.0, '2021/01/16', 1, null, null, 1, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(3, 5.0, CURRENT_TIMESTAMP, 1, null, null, 2, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(4, 5.0, CURRENT_TIMESTAMP, 1, null, null, 4, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(5, 5.0, CURRENT_TIMESTAMP, 1, null, null, 5, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(6, 5.0, CURRENT_TIMESTAMP, 1, null, null, 6, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(7, 5.0, CURRENT_TIMESTAMP, 1, null, null, 7, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(8, 5.0, CURRENT_TIMESTAMP, 1, null, null, 8, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(9, 5.0, CURRENT_TIMESTAMP, 1, null, null, 9, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(10, 5.0, CURRENT_TIMESTAMP, 1, null, null, 10, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(11, 5.0, CURRENT_TIMESTAMP, 1, null, null, 11, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(12, 5.0, CURRENT_TIMESTAMP, 1, null, null, 12, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(13, 5.0, CURRENT_TIMESTAMP, 1, null, null, 13, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(14, 5.0, CURRENT_TIMESTAMP, 1, null, null, 14, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(15, 5.0, CURRENT_TIMESTAMP, 1, null, null, 15, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(16, 5.0, CURRENT_TIMESTAMP, 1, null, null, 16, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(17, 5.0, CURRENT_TIMESTAMP, 1, null, null, 17, null);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id, invitation_id) VALUES(18, 5.0, CURRENT_TIMESTAMP, 1, null, null, 18, null);

INSERT INTO invitations VALUES(1, "", null, 3, 3);
INSERT INTO invitations VALUES(2, "", 1, 3, null);

