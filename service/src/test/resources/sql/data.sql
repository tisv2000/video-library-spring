INSERT INTO person (id, name, birthday)
VALUES (1, 'Emma Watson', '1990-04-25'),
       (2, 'Danial Radcliffe', '1989-07-23'),
       (3, 'Nina Dobrev', '1989-01-09'),
       (4, 'Ian Somerhalder', '1978-12-08');
SELECT SETVAL('person_id_seq', (SELECT MAX(id) FROM person));

INSERT INTO movie (id, title, year, country, genre, image, description)
VALUES
       (1, 'The Vampire Diaries', 2009, 'the USA', 'THRILLER', 'vampireDiaries', 'On her first day at high school, Elena meets Stefan and immediately feels a connection with him. However, what she does not know is that Stefan and his brother, Damon, are in fact vampires.'),
       (2, 'Harry Potter and the Sorcerer''s Stone', 2001, 'United Kingdom', 'ADVENTURE', 'harrypotter1','Harry Potter, an eleven-year-old orphan, discovers that he is a wizard and is invited to study at Hogwarts. Even as he escapes a dreary life and enters a world of magic, he finds trouble awaiting him.'),
       (3, 'Harry Potter and the Chamber of Secrets', 2002, 'United Kingdom', 'ADVENTURE', 'harrypotter2','A house-elf warns Harry against returning to Hogwarts, but he decides to ignore it. When students and creatures at the school begin to get petrified, Harry finds himself surrounded in mystery.');
SELECT SETVAL('movie_id_seq', (SELECT MAX(id) FROM movie));

INSERT INTO movie_person (movie_id, person_id, role)
VALUES (1, 3, 'ACTOR'),
       (1, 4, 'ACTOR'),
       (2, 1, 'ACTOR'),
       (2, 2, 'ACTOR'),
       (3, 1, 'ACTOR'),
       (3, 2, 'ACTOR');

INSERT INTO users (id, name, birth_date, password, email, role, gender)
VALUES (1, 'user1', '2000-01-01', 'user1', 'user1', 'USER', 'MALE'),
       (2, 'user2', '1999-01-01', 'user2', 'user2', 'USER', 'FEMALE'),
       (3, 'user3', '1995-01-01', 'user3', 'user3', 'USER', 'MALE'),
       (4, 'admin', '1990-01-01', 'admin', 'admin', 'ADMIN', 'MALE');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO review (user_id, movie_id, text, rate)
VALUES (1, 1, 'Nice movie', 9),
       (2, 2, 'First 3 seasons are the best, but the last few are not that great anymore...', 7),
       (3, 1, 'One of my favorite movies. Watched it many times, definitely recommend!', 10),
       (1, 2, 'Such a lovely story, absolutely love it!', 10),
       (4, 2, 'Good one', 8);
