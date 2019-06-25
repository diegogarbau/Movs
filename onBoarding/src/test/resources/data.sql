SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE PLAYS;
TRUNCATE TABLE ACTORS;
TRUNCATE TABLE MOVIES;
SET FOREIGN_KEY_CHECKS = 1;

insert into MOVIES (title, genre, year) values('Titanic','Drama',1997);
insert into MOVIES (title, genre, year) values('BraveHeart','Drama',1996);
insert into MOVIES (title, genre, year) values('Avatar','Sci-fi',2009);
insert into MOVIES (title, genre, year) values('Cadena Perpetua','Drama',1994);
insert into MOVIES (title, genre, year) values('Batman','Drama',1989);