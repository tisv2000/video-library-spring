--liquibase formatted sql

--changeset lana:1
INSERT INTO person (id, name, birthday)
VALUES (1, 'Emma Watson', '1990-04-25'),
       (2, 'Brad Pitt', '1963-12-18'),
       (3, 'Danial Radcliffe', '1989-07-23'),
       (4, 'Rupert Grint', '1988-08-24'),
       (5, 'Russel Crowe', '1964-04-07'),
       (6, 'Angelina Jolie', '1975-06-04'),
       (7, 'Tom Cruise', '1962-07-03'),
       (8, 'Peter Jackson', '1961-10-31'),
       (9, 'Orlando Bloom', '1977-01-13'),
       (10, 'Hans Zimmer', '1957-09-12'),
       (11, 'Johnny Depp', '1963-06-09'),
       (12, 'Cameron Diaz', '1972-08-30'),
       (13, 'Kate Winslet', '1975-10-05'),
       (14, 'Nina Dobrev', '1989-01-09'),
       (15, 'Ian Somerhalder', '1978-12-08'),
       (16, 'Paul Wesley', '1982-07-23'),
       (17, 'Zac Efron', '1987-10-18'),
       (18, 'Zendaya', '1996-09-01'),
       (19, 'Hugh Jackman', '1968-10-12');
SELECT SETVAL('person_id_seq', (SELECT MAX(id) FROM person));

INSERT INTO movie (id, title, year, country, genre, image, description)
VALUES
    (1, 'The Holiday', 2006, 'the USA', 'DRAMA', 'holiday', 'Two girls from different countries swap homes for the holidays to get away from their relationship issues. However, their lives change unexpectedly when they meet and fall in love with two local guys.'),
    (2, 'Titanic', 1997, 'the USA', 'DRAMA', 'titanic','Seventeen-year-old Rose hails from an aristocratic family and is set to be married. When she boards the Titanic, she meets Jack Dawson, an artist, and falls in love with him.'),
    (3, 'The Vampire Diaries', 2009, 'the USA', 'THRILLER', 'vampireDiaries', 'On her first day at high school, Elena meets Stefan and immediately feels a connection with him. However, what she does not know is that Stefan and his brother, Damon, are in fact vampires.'),
    (4, 'The Lord of the Rings: The Fellowship of the Ring', 2001, 'New Zealand', 'FANTASY', 'lordOfTheRings1','A young hobbit, Frodo, who has found the One Ring that belongs to the Dark Lord Sauron, begins his journey with eight companions to Mount Doom, the only place where it can be destroyed.'),
    (5, 'The Lord of the Rings: The Two Towers', 2002, 'New Zealand', 'FANTASY', 'lordOfTheRings2','Frodo and Sam arrive in Mordor with the help of Gollum. A number of new allies join their former companions to defend Isengard as Saruman launches an assault from his domain.'),
    (6, 'The Lord of the Rings: The Return of the King', 2003, 'New Zealand', 'FANTASY', 'lordOfTheRings3','The former Fellowship members prepare for the final battle. While Frodo and Sam approach Mount Doom to destroy the One Ring, they follow Gollum, unaware of the path he is leading them to.'),
    (7, 'Pirates of the Caribbean: The Curse of the Black Pearl', 2003, 'the USA', 'ADVENTURE', 'pirates1','A blacksmith joins forces with Captain Jack Sparrow, a pirate, in a bid to free the love of his life from Jack''s associates, who kidnapped her suspecting she has the medallion.'),
    (8, 'The Greatest Showman', 2017, 'the USA', 'MUSICAL', 'greatestshowman','P T Barnum becomes a worldwide sensation in the show business. His imagination and innovative ideas take him to the top of his game.'),
    (9, 'Harry Potter and the Sorcerer''s Stone', 2001, 'United Kingdom', 'FANTASY', 'harrypotter1','Harry Potter, an eleven-year-old orphan, discovers that he is a wizard and is invited to study at Hogwarts. Even as he escapes a dreary life and enters a world of magic, he finds trouble awaiting him.'),
    (10, 'Harry Potter and the Chamber of Secrets', 2002, 'United Kingdom', 'FANTASY', 'harrypotter2','A house-elf warns Harry against returning to Hogwarts, but he decides to ignore it. When students and creatures at the school begin to get petrified, Harry finds himself surrounded in mystery.'),
    (11, 'Harry Potter and the Prisoner of Azkaban', 2004, 'United Kingdom', 'FANTASY', 'harrypotter3','Harry, Ron and Hermoine return to Hogwarts just as they learn about Sirius Black and his plans to kill Harry. However, when Harry runs into him, he learns that the truth is far from reality.'),
    (12, 'Harry Potter and the Goblet of Fire', 2005, 'United Kingdom', 'FANTASY', 'harrypotter4','When Harry is chosen as a fourth participant of the inter-school Triwizard Tournament, he is unwittingly pulled into a dark conspiracy that endangers his life.'),
    (13, 'Harry Potter and the Order of the Phoenix', 2007, 'United Kingdom', 'FANTASY', 'harrypotter5','Harry Potter and Dumbledore''s warning about the return of Lord Voldemort is not heeded by the wizard authorities who, in turn, look to undermine Dumbledore''s authority at Hogwarts and discredit Harry.'),
    (14, 'Harry Potter and the Half-Blood Prince', 2009, 'United Kingdom', 'FANTASY', 'harrypotter6','Dumbledore and Harry Potter learn more about Voldemort''s past and his rise to power. Meanwhile, Harry stumbles upon an old potions textbook belonging to a person calling himself the Half-Blood Prince.'),
    (15, 'Harry Potter and the Deathly Hallows: Part 1', 2010, 'United Kingdom', 'FANTASY', 'harrypotter7','After Voldemort takes over the Ministry of Magic, Harry, Ron and Hermione are forced into hiding. They try to decipher the clues left to them by Dumbledore to find and destroy Voldemort''s Horcruxes.'),
    (16, 'Harry Potter and the Deathly Hallows: Part 2', 2011, 'United Kingdom', 'FANTASY', 'harrypotter8','Harry, Ron and Hermione race against time to destroy the remaining Horcruxes. Meanwhile, the students and teachers unite to defend Hogwarts against Lord Voldemort and the Death Eaters.');
SELECT SETVAL('movie_id_seq', (SELECT MAX(id) FROM movie));

INSERT INTO movie_person (movie_id, person_id, role)
VALUES (1, 10, 'COMPOSER'),
       (1, 12, 'ACTOR'),
       (1, 13, 'ACTOR'),
       (2, 13, 'ACTOR'),
       (3, 14, 'ACTOR'),
       (3, 15, 'ACTOR'),
       (3, 16, 'ACTOR'),
       (4, 8, 'PRODUCER'),
       (4, 8, 'DIRECTOR'),
       (4, 8, 'ACTOR'),
       (4, 9, 'ACTOR'),
       (5, 8, 'PRODUCER'),
       (5, 8, 'DIRECTOR'),
       (5, 8, 'ACTOR'),
       (5, 9, 'ACTOR'),
       (6, 8, 'PRODUCER'),
       (6, 8, 'DIRECTOR'),
       (6, 8, 'ACTOR'),
       (6, 9, 'ACTOR'),
       (7, 9, 'ACTOR'),
       (7, 10, 'COMPOSER'),
       (7, 11, 'ACTOR'),
       (8, 17, 'ACTOR'),
       (8, 18, 'ACTOR'),
       (8, 19, 'ACTOR'),
       (9, 1, 'ACTOR'),
       (9, 3, 'ACTOR'),
       (9, 4, 'ACTOR'),
       (10, 1, 'ACTOR'),
       (10, 3, 'ACTOR'),
       (10, 4, 'ACTOR'),
       (11, 1, 'ACTOR'),
       (11, 3, 'ACTOR'),
       (11, 4, 'ACTOR'),
       (12, 1, 'ACTOR'),
       (12, 3, 'ACTOR'),
       (12, 4, 'ACTOR'),
       (13, 1, 'ACTOR'),
       (13, 3, 'ACTOR'),
       (13, 4, 'ACTOR'),
       (14, 1, 'ACTOR'),
       (14, 3, 'ACTOR'),
       (14, 4, 'ACTOR'),
       (15, 1, 'ACTOR'),
       (15, 3, 'ACTOR'),
       (15, 4, 'ACTOR'),
       (16, 1, 'ACTOR'),
       (16, 3, 'ACTOR'),
       (16, 4, 'ACTOR');

INSERT INTO users (id, name, birth_date, password, email, role, gender)
VALUES (1, 'user1', '2000-01-01', '{noop}123', 'user1@gmail.com', 'USER', 'MALE'),
       (2, 'user2', '1999-01-01', '{noop}123', 'user2@gmail.com', 'USER', 'FEMALE'),
       (3, 'user3', '1995-01-01', '{noop}123', 'user3@gmail.com', 'USER', 'MALE'),
       (4, 'admin', '1990-01-01', '{noop}123', 'admin@gmail.com', 'ADMIN', 'MALE');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO review (user_id, movie_id, text, rate)
VALUES (1, 1, 'Nice movie.', 9),
       (1, 3, 'One of my favorite movies. Watched it many times, definitely recommended!', 10),
       (2, 1, 'Such a lovely story, absolutely love it!', 10),
       (2, 3, 'First 3 seasons are the best, but the last few are not that great anymore...', 7),
       (3, 1, 'Good one', 8),
       (3, 3, 'Cool movie', 9),
       (4, 1, 'Great movie', 10),
       (4, 3, 'I really like this movie', 9);



select m.title
from movie_person mp inner join movie m on mp.person_id = 1 and mp.movie_id = m.id;
