INSERT INTO `line`
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

INSERT INTO `seats`
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 1),
       (5, 5, 1),
       (6, 6, 1),
       (7, 7, 1),
       (8, 8, 1),
       (9, 9, 1),
       (10, 10, 1);

INSERT INTO `seats`
VALUES (11, 1, 2),
       (12, 2, 2),
       (13, 3, 2),
       (14, 4, 2),
       (15, 5, 2),
       (16, 6, 2),
       (17, 7, 2),
       (18, 8, 2),
       (19, 9, 2),
       (20, 10, 2);

INSERT INTO `seats`
VALUES (31, 1, 3),
       (32, 2, 3),
       (33, 3, 3),
       (34, 4, 3),
       (35, 5, 3),
       (36, 6, 3),
       (37, 7, 3),
       (38, 8, 3),
       (39, 9, 3),
       (40, 10, 3);

INSERT INTO `seats`
VALUES (41, 1, 4),
       (42, 2, 4),
       (43, 3, 4),
       (44, 4, 4),
       (45, 5, 4),
       (46, 6, 4),
       (47, 7, 4),
       (48, 8, 4),
       (49, 9, 4),
       (50, 10, 4);

INSERT INTO `seats`
VALUES (51, 1, 5),
       (52, 2, 5),
       (53, 3, 5),
       (54, 4, 5),
       (55, 5, 5),
       (56, 6, 5),
       (57, 7, 5),
       (58, 8, 5),
       (59, 9, 5),
       (60, 10, 5);

INSERT INTO `users`
VALUES (1, 'max@gmail.com', 'Max', '$2a$10$BBbOBAgO24CCehQjxEP7.ejZKZF7RtXismommRLKB.688YFouX5sa'),
       (2, 'john@gmail.com', 'John', '$2a$10$JnU7MhS2IlPeavPDQRUngekTcDmtoSVDuk7XXQw5dlLFCENN1TfdC'),
       (3, 'admin1@gmail.com', 'Admin1', '$2a$10$vteOkHsvUZfnlgkZvNtkze96gKey/yg/QKYbRlgyPq50J3...vdA2'),
       (4, 'admin2@gmail.com', 'Admin2', '$2a$10$hYElkcYfpjbebMq.HY7s3.4arLZOY7pjOT0pDDDBCIaC7.GmK.EtK');

INSERT INTO `roles`
VALUES (1, 'USER'),
       (2, 'USER'),
       (3, 'ADMIN'),
       (4, 'ADMIN');


INSERT INTO `movies`
VALUES (1, 'USA',
        'There is one rotten place on earth, from where even the most notorious villains dream of dumping. Belle Reeve Prison is for superpowered criminals. She''s hell. It is also the recruitment base for the super-secret project "Suicide Squad".',
        'James Gunn', 'https://zupimages.net/up/21/36/dqo1.jpeg', 'THE SUICIDE SQUAD', '2021'),
       (2, 'USA',
        'Master of the martial arts Shang-Chi must confront ghosts from his own past as he is pulled into the web of intrigue by the mysterious Ten Rings organization.',
        'Destin Daniel Cretton', 'https://zupimages.net/up/21/36/zl4e.jpg', 'SHANG CHI AND THE LEGEND OF THE TEN RINGS',
        '2021'),
       (3, 'USA',
        'An adventure comedy about a lonely bank teller who discovers that he is in fact a background character in the Free City video game. Will he have the courage to rewrite his code, finally turn his attention to the beautiful girl and save the world?',
        'Shawn Levy', 'https://zupimages.net/up/21/36/h2cl.jpg', 'FREE GUY', '2021'),
       (4, 'USA',
        'There is a legend about the mysterious Candyman - a ghost from a parallel world. But one has only to stand in front of the mirror and say his name five times in a row, the darkness will cross the border. The macabre sequel brings a fearsome urban legend back to the present day.',
        'Nia DaCosta', 'https://zupimages.net/up/21/36/dc7p.jpg', 'CANDYMAN', '2021');


INSERT INTO `movie_sessions`
VALUES (1, '2021-09-22', '10:00', 100, 1),
       (2, '2021-09-22', '14:00', 100, 1),
       (3, '2021-09-22', '18:00', 120, 1),
       (4, '2021-09-22', '21:00', 150, 1),
       (5, '2021-09-23', '10:00', 100, 1),
       (6, '2021-09-23', '14:00', 100, 1),
       (7, '2021-09-23', '18:00', 120, 1),
       (8, '2021-09-23', '21:00', 150, 1),
       (9, '2021-09-12', '10:00', 100, 1),
       (10, '2021-09-12', '14:00', 100, 1),
       (11, '2021-09-12', '18:00', 120, 1),
       (12, '2021-09-12', '21:00', 150, 1),
       (13, '2021-09-24', '14:00', 50, 2),
       (14, '2021-09-24', '18:00', 100, 2),
       (15, '2021-09-24', '21:00', 100, 2),
       (16, '2021-09-22', '14:00', 50, 2),
       (17, '2021-09-22', '18:00', 100, 2),
       (18, '2021-09-22', '21:00', 100, 2),
       (19, '2021-09-25', '14:00', 50, 2),
       (20, '2021-09-25', '18:00', 100, 2),
       (21, '2021-09-25', '21:00', 100, 2),
       (22, '2021-09-10', '18:00', 150, 3),
       (23, '2021-09-10', '21:00', 150, 3),
       (24, '2021-09-23', '18:00', 150, 3),
       (25, '2021-09-23', '21:00', 150, 3),
       (26, '2021-10-23', '18:00', 150, 4),
       (27, '2021-09-25', '18:00', 150, 4);

INSERT INTO `orders`
VALUES (1, 'CONFIRMED', '2021-09-08 10:00', 300, 1),
       (2, 'CONFIRMED', '2021-09-09 20:00', 350, 2),
       (3, 'CONFIRMED', '2021-09-05 20:30', 400, 2),
       (4, 'CONFIRMED', '2021-09-05 12:00', 250, 1),
       (5, 'CONFIRMED', '2021-09-05 20:30', 200, 2);

INSERT INTO `tickets`
VALUES (1, 1, 1, 1, 10),
       (2, 2, 10, 2, 18),
       (3, 1, 3, 3, 20),
       (4, 1, 5, 3, 10),
       (5, 1, 5, 3, 11),
       (6, 1, 15, 4, 5),
       (7, 1, 20, 5, 1),
       (8, 1, 20, 5, 2),
       (9, 1, 20, 5, 3);

INSERT INTO `movie_sessions_booked_seats`
VALUES (1, 10),
       (10, 18),
       (3, 20),
       (5, 10),
       (5, 11),
       (15, 5),
       (20, 1),
       (20, 2),
       (20, 3);
