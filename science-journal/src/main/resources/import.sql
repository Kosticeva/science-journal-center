insert into science_field(code, field) values ('AST', 'Astronomija');
insert into science_field(code, field) values ('ANA', 'Anatomija');
insert into science_field(code, field) values ('ZOO', 'Zoologija');
insert into science_field(code, field) values ('HIS', 'Istorija');
insert into science_field(code, field) values ('GLG', 'Geologija');
insert into science_field(code, field) values ('PLN', 'Paleontologija');

insert into magazine(issn, name, type, MEMBERSHIP_PRICE, MEMBERSHIP_CURRENCY) values ('87654321', 'Politikin Zabavnik', 'PAID_ACCESS', 0, 'RSD');
insert into magazine_fields(magazine, field) values ('87654321', 'ZOO');
insert into magazine_fields(magazine, field) values ('87654321', 'ANA');
insert into magazine_fields(magazine, field) values ('87654321', 'HIS');

insert into magazine(issn, name, type, MEMBERSHIP_PRICE, MEMBERSHIP_CURRENCY) values ('12345678', 'Nacionalna Geografija', 'OPEN_ACCESS', 1000, 'RSD');
insert into magazine_fields(magazine, field) values ('12345678', 'AST');
insert into magazine_fields(magazine, field) values ('12345678', 'GLG');
insert into magazine_fields(magazine, field) values ('12345678', 'PLN');

insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (1, 'Marija', 'Markovic', 'Zagreb', 'Croatia', 'marija.autor.4321@gmail.com', 15.8216904, 45.7408527);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (2, 'Petar', 'Petrovic', 'Subotica', 'Serbia', 'petar.autor.1234@gmail.com', 19.6650593, 46.1005467);
--insert into credentials(username, password, details) values ('ppetrovic', 'petarP123', 1);
insert into credentials(username, password, details) values ('ppetrovic', '$2a$10$ud4N2RdYoeovtZUB02FJC.lviTM2qxfdkGS.4.XYrBsDmzFLR1wzK', 2);
--insert into credentials(username, password, details) values ('mmarkovic', 'marijaM123', 2);
insert into credentials(username, password, details) values ('mmarkovic', '$2a$10$cqxPLV1BEwIIoLHAlU7dUuBDvqYxMmdxkcID1QxANP/NdV7oFNU0m', 1);

insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (3, 'Recenzent', 'Recenzent1', 'Kragujevac', 'Serbia', 'recenzent1@gmail.com', 20.9114225, 44.0127932);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (4, 'Recenzent', 'Recenzent2', 'Podgorica', 'Montenegro', 'recenzent2@gmail.com', 19.2593642, 42.4304196);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (5, 'Recenzent', 'Recenzent3', 'Belgrade', 'Serbia', 'recenzent3@gmail.com', 20.4489216, 44.786568);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (6, 'Recenzent', 'Recenzent4', 'Belgrade', 'Serbia', 'recenzent4@gmail.com', 20.4489216, 44.786568);
--insert into credentials(username, password, details) values ('recenzent1', 'rec1', 3);
insert into credentials(username, password, details) values ('recenzent1', '$2a$10$TQF4cBo7jieZyYJRySZa9uhD66507R8L.LShfYjff6suZR8SIkfQq', 3);
insert into credentials(username, password, details) values ('recenzent2', '$2a$10$SQ9wQoci0RpiTil.gv5N2O3H2wNpkuNqn7ErYdNM6nfiLeVvRjx1a', 4);
insert into credentials(username, password, details) values ('recenzent3', '$2a$10$koePaplbR1P/qa41A8ttgupYdSuuLD4.uvKHbcpuSDEo34gr3137C', 5);
insert into credentials(username, password, details) values ('recenzent4', '$2a$10$tFuhxBWHbxiY1slN/t3IsOX1sHS1dcUYqP5LuCZ34FEYjjJdXROzi', 6);
insert into reviewer(title, id, username) values ('MSc', 1, 'recenzent1');
insert into reviewer_magazine(reviewer, magazine) values (1, '12345678');
insert into reviewer_magazine(reviewer, magazine) values (1, '87654321');
insert into reviewer_field(reviewer, field) values (1, 'AST');
insert into reviewer_field(reviewer, field) values (1, 'PLN');
insert into reviewer_field(reviewer, field) values (1, 'ANA');
insert into reviewer_field(reviewer, field) values (1, 'HIS');
insert into reviewer(title, id, username) values ('MSc', 2, 'recenzent2');
insert into reviewer_magazine(reviewer, magazine) values (2, '12345678');
insert into reviewer_magazine(reviewer, magazine) values (2, '87654321');
insert into reviewer_field(reviewer, field) values (2, 'AST');
insert into reviewer_field(reviewer, field) values (2, 'ZOO');
insert into reviewer_field(reviewer, field) values (2, 'HIS');
insert into reviewer(title, id, username) values ('PHd', 3, 'recenzent3');
insert into reviewer_magazine(reviewer, magazine) values (3, '12345678');
insert into reviewer_magazine(reviewer, magazine) values (3, '87654321');
insert into reviewer_field(reviewer, field) values (3, 'GLG');
insert into reviewer_field(reviewer, field) values (3, 'ZOO');
insert into reviewer_field(reviewer, field) values (3, 'ANA');
insert into reviewer(title, id, username) values ('PHd', 4, 'recenzent4');
insert into reviewer_magazine(reviewer, magazine) values (4, '12345678');
insert into reviewer_magazine(reviewer, magazine) values (4, '87654321');
insert into reviewer_field(reviewer, field) values (4, 'GLG');
insert into reviewer_field(reviewer, field) values (4, 'PLN');
insert into reviewer_field(reviewer, field) values (4, 'HIS');

insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (7, 'Urednik', 'Glavni_NG', 'Belgrade', 'Serbia', 'urednik_gl_ng@gmail.com', 20.4489216, 44.786568);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (8, 'Urednik', 'Glavni_PZ', 'Belgrade', 'Serbia', 'urednik_gl_pz@gmail.com', 20.4489216, 44.786568);
--insert into credentials(username, password, details) values ('urednikNG', 'NatGeo', 7);
insert into credentials(username, password, details) values ('urednikNG', '$2a$10$zFDi8oOFTUVgo/xMjreVwuQUqrmf6mXT1uvZbJi8Oq53NKXE19iyS', 7);
--insert into credentials(username, password, details) values ('urednikPZ', 'PolZab', 8);
insert into credentials(username, password, details) values ('urednikPZ', '$2a$10$qFafnm/TUAc7gvTV4lyFeeXcDAl8rFIydN22nQyn0rs5n0TGONGNW', 8);
insert into editor(title, id, username, magazine) values ('PHd', 5, 'urednikNG', '12345678');
insert into editor(title, id, username, magazine) values ('PHd', 6, 'urednikPZ', '87654321');

insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (9, 'Urednik', 'Istorija', 'Novi Sad', 'Serbia', 'urednik_hist_pz@gmail.com', 19.8335496, 45.2671352);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (10, 'Urednik', 'Anatomija', 'Novi Sad', 'Serbia', 'urednik_anat_pz@gmail.com', 19.8335496, 45.2671352);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (11, 'Urednik', 'Zoologija', 'Novi Sad', 'Serbia', 'urednik_zoo_pz@gmail.com', 19.8335496, 45.2671352);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (12, 'Urednik', 'Paleontologija', 'Belgrade', 'Serbia', 'urednik_paleo_ng@gmail.com', 20.4489216, 44.786568);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (13, 'Urednik', 'Geologija', 'Belgrade', 'Serbia', 'urednik_geo_ng@gmail.com', 20.4489216, 44.786568);
insert into user(id, first_name, last_name, city, country, email, longitude, latitude) values (14, 'Urednik', 'Astronomija', 'Belgrade', 'Serbia', 'urednik_astr_ng@gmail.com', 20.4489216, 44.786568);
--insert into credentials(username, password, details) values ('urednikHIS', 'HIS', 9);
insert into credentials(username, password, details) values ('urednikHIS', '$2a$10$9m.jgOPq10O7t4.Brgv0k.W/fnrAFU6Rf7p2vTyWcIe6ccQVIOQTS', 9);
insert into credentials(username, password, details) values ('urednikANA', '$2a$10$ibUf1ev5HvwS..eHaYhEMuFvdcJsvJO2nOjJofDFf4wKRDSfWU4Q2', 10);
insert into credentials(username, password, details) values ('urednikZOO', '$2a$10$EATdipnIu8k2/aRqlu1VvuaAFVk6iIcOK1nZVjOeEEA4NiRisW0fq', 11);
insert into credentials(username, password, details) values ('urednikPLN', '$2a$10$QhrmLdme4hSHhKfckMwNq.VdTzMCq3w06Z4eMj2eDgInCWDKHNtta', 12);
insert into credentials(username, password, details) values ('urednikGLG', '$2a$10$j8p/fnTtCVMqlXJBuNotB.MEqUV5tjryd73pyqJdRSf0Fy3byMuCu', 13);
insert into credentials(username, password, details) values ('urednikAST', '$2a$10$QUpTJUuic3xhg0r78TxS6u8roHsOx3elEzL9XGQ6AK/0UpmxocXT6', 14);
insert into editor(title, id, username, magazine, field) values ('PHd', 7, 'urednikAST', '12345678', 'AST');
insert into editor(title, id, username, magazine, field) values ('PHd', 8, 'urednikGLG', '12345678', 'GLG');
insert into editor(title, id, username, magazine, field) values ('PHd', 9, 'urednikPLN', '12345678', 'PLN');
insert into editor(title, id, username, magazine, field) values ('PHd', 10, 'urednikZOO', '87654321', 'ZOO');
insert into editor(title, id, username, magazine, field) values ('PHd', 11, 'urednikANA', '87654321', 'ANA');
insert into editor(title, id, username, magazine, field) values ('PHd', 12, 'urednikHIS', '87654321', 'HIS');

insert into issue(id, edition, magazine, print_date, price, currency) values (1, 'Novembar 2018', '12345678', '2018-10-20', 0, 'RSD');
insert into issue(id, edition, magazine, print_date, price, currency) values (2, 'Oktobar 2018', '12345678', '2018-09-20', 0, 'RSD');
insert into issue(id, edition, magazine, print_date, price, currency) values (3, '3214', '87654321', '2019-01-04', 300, 'RSD');
insert into issue(id, edition, magazine, print_date, price, currency) values (4, '3215', '87654321', '2019-01-11', 300, 'RSD');

insert into application(id, version, title, abstract, keywords, author, magazine, field, file, state, accepted, timestamp) values (1, 0, 'Sazvežđa', 'O nastanku sazvežđa', 'zvezde,grci,teleskop,nebo', 'ppetrovic', '12345678', 'AST', 'C:\\Users\\kosti\\git\\science-journal-center\\science-journal\\target\\www\\upload-dir\\12345678\\applications\\1-Sazvežđa.pdf', 'ACCEPTED', 1, '2019-01-27');
insert into application_coauthors(paper, author) values (1, 1);
--insert into task(id, assignee, deadline, application_id, summary, type, finished) values (1, 'urednikNG', '2019-02-14', 1, 'Analiza recenzija', 5, 0);
insert into application(id, version, title, abstract, keywords, author, magazine, field, file, state, accepted, timestamp) values (2, 0, 'Okeansko dno', 'O značaju i veličini okeanskog dna', 'okean,dno,Pacifik,Mont Everest,Mauna Kea,Marijanski rov,kontinent,voda', 'ppetrovic', '12345678', 'GLG', 'C:\\Users\\kosti\\git\\science-journal-center\\science-journal\\target\\www\\upload-dir\\12345678\\applications\\2-Okeansko dno.pdf', 'ACCEPTED', 1, '2019-01-27');
insert into application_coauthors(paper, author) values (2, 1);
--insert into task(id, assignee, deadline, application_id, summary, type, finished) values (2, 'urednikNG', '2019-02-14', 2, 'Analiza recenzija', 5, 0);
insert into application(id, version, title, abstract, keywords, author, magazine, field, file, state, accepted, timestamp) values (3, 0, 'Svet u doba Krede', 'O dinosaurusima koji su vladali u doba Krede', 'dinosaurus,Kreda,Pangea,Laurazija,Gondvana,Jura,Brachiosaurus,Apatosaurus,Hypsilophodon,klima,biljke', 'mmarkovic', '12345678', 'PLN', 'C:\\Users\\kosti\\git\\science-journal-center\\science-journal\\target\\www\\upload-dir\\12345678\\applications\\3-Svet u doba Krede.pdf', 'ACCEPTED', 1, '2019-01-27');
insert into application_coauthors(paper, author) values (3, 2);
--insert into task(id, assignee, deadline, application_id, summary, type, finished) values (3, 'urednikNG', '2019-02-14', 3, 'Analiza recenzija', 5, 0);
insert into application(id, version, title, abstract, keywords, author, magazine, field, file, state, accepted, timestamp) values (4, 0, 'Zečevi i kunići', 'O najbitnijim karakteristikama lagomorfa', 'zec,kunic,lagomorfe,sisari,glodari,zvizdare', 'mmarkovic', '87654321', 'ZOO', 'C:\\Users\\kosti\\git\\science-journal-center\\science-journal\\target\\www\\upload-dir\\87654321\\applications\\4-Zečevi i kunići.pdf', 'ACCEPTED', 1, '2019-01-27');
insert into application_coauthors(paper, author) values (4, 2);
--insert into task(id, assignee, deadline, application_id, summary, type, finished) values (4, 'urednikPZ', '2019-02-14', 4, 'Analiza recenzija', 5, 0);
insert into application(id, version, title, abstract, keywords, author, magazine, field, file, state, accepted, timestamp) values (5, 0, 'Koža', 'O najbitnijim karakteristikama kože', 'koza,omotac,stopala,kapci,dlacice,cula,znoj', 'mmarkovic', '87654321', 'ANA', 'C:\\Users\\kosti\\git\\science-journal-center\\science-journal\\target\\www\\upload-dir\\87654321\\applications\\5-Koža.pdf', 'ACCEPTED', 1, '2019-01-27');
insert into application_coauthors(paper, author) values (5, 2);
insert into application_coauthors(paper, author) values (5, 1);
--insert into task(id, assignee, deadline, application_id, summary, type, finished) values (5, 'urednikPZ', '2019-02-14', 5, 'Analiza recenzija', 5, 0);
insert into application(id, version, title, abstract, keywords, author, magazine, field, file, state, accepted, timestamp) values (6, 0, 'Mongoli', 'O kulturnom nasleđu Mongola', 'mongoli,konjanici,Jurta,srednja Azija,nomadi,Gobi', 'ppetrovic', '87654321', 'HIS', 'C:\\Users\\kosti\\git\\science-journal-center\\science-journal\\target\\www\\upload-dir\\87654321\\applications\\6-Mongoli.pdf', 'ACCEPTED', 1, '2019-01-27');
insert into application_coauthors(paper, author) values (6, 2);
insert into application_coauthors(paper, author) values (6, 1);
--insert into task(id, assignee, deadline, application_id, summary, type, finished) values (6, 'urednikPZ', '2019-02-14',6, 'Analiza recenzija', 5, 0);




