INSERT INTO user_type VALUES(1, 'Básico');
INSERT INTO user_type VALUES(2, 'Premium');
INSERT INTO user_type VALUES(3, 'Administrador');

INSERT INTO users(username,password,enabled) VALUES ('antonio98','us3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'antonio98','usuario');
INSERT INTO authorities(id,username,authority) VALUES (2,'antonio98','premium');
INSERT INTO users(username,password,enabled) VALUES ('fernando98','us3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'fernando98','usuario');
INSERT INTO authorities(id,username,authority) VALUES (4,'fernando98','premium');
INSERT INTO users(username,password,enabled) VALUES ('marta98','us3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'marta98','usuario');

INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(1, 'Antonio', '1999-02-20', 'antonio@gmail.com', '654893274', 2,0,'antonio98');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(2, 'Fernando', '1995-07-06', 'fernando1@gmail.com', '635897412', 1,10,'fernando98');
INSERT INTO usuarios(id, name, birthdate, correo, phone, type_id,puntos, username) VALUES(3, 'Marta', '1992-03-25', 'marta1@gmail.com', '954785123', 1,0,'marta98');

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
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(17, 'Karting', 1, 1);
INSERT INTO sports(id, name, number_of_players_in_team, sport_type) VALUES(18, 'Pilates', 1, 2);

INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id) 
VALUES(1, 'Polideportivo Ciudad Jardin', 'Sevilla', '2021/06/15 11:00:00', 'Nos lo vamos a pasar genial! Apuntarse!', 2,1, 1);
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id) 
VALUES(2, 'Polideportivo San Pablo', 'Sevilla', '2021/04/15 11:20:00', 'Vamos a realizar mucho deporte.', 2,1, 1);
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id) 
VALUES(3, 'Tenis Betis', 'Sevilla', '2021/05/23 13:30:00', 'No dudéis en apuntarse!!', 4,2, 1);
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id) 
VALUES(4, 'Club Santa Clara', 'Sevilla', '2021/04/30 11:20:00', 'Lo vamos a pasar increible!!', 2,3, 1);
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id) 
VALUES(5, 'SADUS Bermejales', 'Sevilla', '2021/05/11 17:20:00', 'Apúntense amigos! Lo vamos a pasar muy bien!', 2,1, 1);
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id) 
VALUES(6, 'Polideportivo Marbella Vice', 'Sevilla', '2021/04/20 11:45:00', 'Quedada para pasar un buen rato!', 4,2, 1);
INSERT INTO meetings(id, address, city, date, description, number_of_players,meeting_creator, sport_id) 
VALUES(7, 'Polideportivo Los Caños', 'Sevilla', '2021/04/15 09:20:00', 'Mientras más seamos mejor! No dudéis en venir!', 4,3, 1);

INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(1, 'Torneo ATP', 'Sevilla', 'Torneo oficial de la ATP', '2021/06/25 12:00:00', 8, '2021/06/15 11:00:00', 1, 1);
INSERT INTO championships(id, name, city, description, finish_date, max_teams, start_date, sport_id, owner) 
VALUES(2, 'Torneo entre amigos', 'Sevilla', 'Torneo disputado entre amigos! Nos falta gente!', '2021/04/12 16:45:00', 4, '2021/04/06 18:45:00', 1, 1);
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

INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(1, 'West Jamon', 1, 1, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(2, 'Shalcke Temeto', 1, 1, 2);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(3, 'Vodka Juniors', 1, 2, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(4, 'Yayo Vallecano', 1, 2, 3);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(5, 'Jugadores de barrio', 1, 2, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(6, 'Unión Penosa', 1, 3, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(7, 'Nottingham Miedo', 1, 3, 1);
INSERT INTO teams(id, name, team_size, championships_id, owner) VALUES(8, 'Árbitro penalty', 1, 8, 2);

INSERT INTO matches VALUES(1, '2021/06/15 11:00:00', 2, 1, 2, 1, 1, 1, 2);

INSERT INTO statistics(id, sport_id, user_id) VALUES(1, 1, 1);
INSERT INTO statistics(id, sport_id, user_id) VALUES(2, 2, 2);

INSERT INTO pay_type(id, name) VALUES(1, 'Premium');
INSERT INTO pay_type(id, name) VALUES(2, 'Championship');

INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id) VALUES(1, 2.0, '2021/01/16 11:45:00', 2, 8, 8, 3);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id) VALUES(2, 5.0, '2021/01/16 11:45:00', 1, null, 1, 1);
INSERT INTO pay(id, amount, date, pay_type_id, championship_id, team_id, user_id) VALUES(3, 5.0, '2021/04/07 12:15:00', 1, null, 1, 2);

INSERT INTO teams_participants(teams_id, participants_id) VALUES(8, 3);
INSERT INTO teams_participants(teams_id, participants_id) VALUES(2, 1);



