insert into science_field(code, field) values ('PHY', 'physics');
insert into science_field(code, field) values ('CHE', 'chemistry');
insert into science_field(code, field) values ('LIT', 'literature');
insert into science_field(code, field) values ('MAT', 'mathematics');
insert into science_field(code, field) values ('GEO', 'geography');
insert into science_field(code, field) values ('HIS', 'history');
insert into science_field(code, field) values ('BIO', 'biology');
insert into science_field(code, field) values ('PHI', 'philosophy');
insert into science_field(code, field) values ('SSC', 'social sciences');

insert into magazine(issn, membership_price, name, type) values ('12345678', 1000, 'Chemistry, Physics and Mathematics Review', 'OPEN_ACCESS');
insert into magazine(issn, membership_price, name, type) values ('87654321', 500, 'Biology, History and Geography Journal', 'OPEN_ACCESS');
insert into magazine(issn, membership_price, name, type) values ('74651xaa', 0, 'On Literature And Philosophy', 'PAID_ACCESS');

insert into magazine_fields(magazine, field) values ('12345678', 'PHY');
insert into magazine_fields(magazine, field) values ('12345678', 'CHE');
insert into magazine_fields(magazine, field) values ('12345678', 'MAT');
insert into magazine_fields(magazine, field) values ('87654321', 'LIT');
insert into magazine_fields(magazine, field) values ('87654321', 'PHI');
insert into magazine_fields(magazine, field) values ('74651xaa', 'BIO');
insert into magazine_fields(magazine, field) values ('74651xaa', 'HIS');
insert into magazine_fields(magazine, field) values ('74651xaa', 'GEO');

--------------------------------------------------------------------------------------------------------------------------------

insert into user(id, city, country, email, first_name, last_name) values (1, 'Ruma', 'Serbia', 'kosticka.jelena@gmail.com', 'Jelena', 'Kostic');
insert into credentials(username, password, details) values ('jelena', 'jelena', 1);

insert into user(id, city, country, email, first_name, last_name) values (2, 'Novi Sad', 'Serbia', 'admin@science-journal.com', 'Admin', 'Administrator');
insert into credentials(username, password, details) values ('admin', 'admin', 2);

insert into user(id, city, country, email, first_name, last_name) values (3, 'Boston', 'USA', 'mary.redsox@yahoo.com', 'Mary', 'Masters');
insert into credentials(username, password, details) values ('mary', 'mary', 3);
insert into reviewer(id, username, title) values (1, 'mary', 'MsC in Social Sciences');
insert into reviewer_field(reviewer, field) values (1, 'LIT');
insert into reviewer_field(reviewer, field) values (1, 'PHI');

insert into user(id, city, country, email, first_name, last_name) values (4, 'Livingstone', 'South Africa', 'peter.svahili@live.com', 'Peter', 'Peterson');
insert into credentials(username, password, details) values ('peter', 'peter', 4);
insert into reviewer(id, username, title) values (2, 'peter', 'PhD in Science');
insert into reviewer_field(reviewer, field) values (2, 'PHY');
insert into reviewer_field(reviewer, field) values (2, 'CHE');
insert into reviewer_field(reviewer, field) values (2, 'MAT');

insert into user(id, city, country, email, first_name, last_name) values (5, 'Perth', 'Australia', 'john.cangaroo@outlook.com', 'John', 'Doe');
insert into credentials(username, password, details) values ('john', 'john', 5);
insert into reviewer(id, username, title) values (3, 'john', 'MsC in Science');
insert into reviewer_field(reviewer, field) values (3, 'MAT');

insert into user(id, city, country, email, first_name, last_name) values (6, 'Munich', 'Germany', 'anne.anne@live.com', 'Anne', 'Schmith');
insert into credentials(username, password, details) values ('anne', 'anne', 6);
insert into reviewer(id, username, title) values (4, 'anne', 'PhD in Science');
insert into reviewer_field(reviewer, field) values (4, 'CHE');
insert into reviewer_field(reviewer, field) values (4, 'PHY');

insert into user(id, city, country, email, first_name, last_name) values (7, 'London', 'United Kingdom', 'jack.jack@outlook.com', 'Jack', 'Jackson');
insert into credentials(username, password, details) values ('jack', 'jack', 7);
insert into reviewer(id, username, title) values (5, 'jack', 'PhD in Social Science');
insert into reviewer_field(reviewer, field) values (5, 'PHI');
insert into reviewer_field(reviewer, field) values (5, 'LIT');

insert into user(id, city, country, email, first_name, last_name) values (8, 'Beijing', 'China', 'li.panda@outlook.com', 'Li-Chun', 'Soo');
insert into credentials(username, password, details) values ('li', 'liSooChin', 8);
insert into reviewer(id, username, title) values (6, 'li', 'MsC in Science');
insert into reviewer_field(reviewer, field) values (6, 'BIO');

insert into user(id, city, country, email, first_name, last_name) values (9, 'Saint Petersburg', 'Russia', 'yashin.lev@live.com', 'Lev', 'Yashin');
insert into credentials(username, password, details) values ('levY', 'YashinLev', 9);
insert into reviewer(id, username, title) values (7, 'levY', 'PhD in Science');
insert into reviewer_field(reviewer, field) values (7, 'BIO');

insert into user(id, city, country, email, first_name, last_name) values (10, 'Paris', 'France', 'macronB@gmail.com', 'Bridgette', 'Macron');
insert into credentials(username, password, details) values ('macronB', 'firstLady', 10);
insert into reviewer(id, username, title) values (8, 'macronB', 'MsC in Science');
insert into reviewer_field(reviewer, field) values (8, 'GEO');

insert into user(id, city, country, email, first_name, last_name) values (11, 'Oslo', 'Norway', 'charlene.oslo@outlook.com', 'Charlene', 'Woodstock');
insert into credentials(username, password, details) values ('charleneW', 'princess', 11);
insert into reviewer(id, username, title) values (9, 'charleneW', 'MsC in Science');
insert into reviewer_field(reviewer, field) values (9, 'GEO');

insert into user(id, city, country, email, first_name, last_name) values (12, 'Istanbul', 'Turkey', 'editor.chief@magazine1.com', 'Chief', 'Editor1');
insert into credentials(username, password, details) values ('editor1', 'editor', 12);
insert into editor(id, username, title, magazine, field) values (1, 'editor1', 'PhD in Science', '12345678', null);

insert into user(id, city, country, email, first_name, last_name) values (13, 'Moscow', 'Russia', 'editor.chief@magazine2.com', 'Chief', 'Editor2');
insert into credentials(username, password, details) values ('editor2', 'editor', 13);
insert into editor(id, username, title, magazine, field) values (2, 'editor2', 'PhD in Social Sciences', '87654321', null);

insert into user(id, city, country, email, first_name, last_name) values (14, 'Saint Petersburg', 'Russia', 'editor.chief@magazine3.com', 'Chief', 'Editor3');
insert into credentials(username, password, details) values ('editor3', 'editor', 14);
insert into editor(id, username, title, magazine, field) values (3, 'editor3', 'PhD in Science', '87654321', null);

insert into user(id, city, country, email, first_name, last_name) values (15, 'London', 'United Kingdom', 'editor.physics@magazine1.com', 'Physics', 'Editor');
insert into credentials(username, password, details) values ('editor_physics', 'editor_physics', 15);
insert into editor(id, username, title, magazine, field) values (4, 'editor_physics', 'PhD in Science', '12345678', 'PHY');

insert into user(id, city, country, email, first_name, last_name) values (16, 'Berlin', 'Germany', 'editor.chemistry@magazine1.com', 'Chemistry', 'Editor');
insert into credentials(username, password, details) values ('editor_chemistry', 'editor_chemistry', 16);
insert into editor(id, username, title, magazine, field) values (5, 'editor_chemistry', 'PhD in Science', '12345678', 'CHE');

insert into user(id, city, country, email, first_name, last_name) values (17, 'Madrid', 'Spain', 'editor.mathematics@magazine1.com', 'Mathematics', 'Editor');
insert into credentials(username, password, details) values ('editor_mathematics', 'editor_mathematics', 17);
insert into editor(id, username, title, magazine, field) values (6, 'editor_mathematics', 'MsC in Science', '12345678', 'MAT');

insert into user(id, city, country, email, first_name, last_name) values (18, 'Paris', 'France', 'editor.literature@magazine2.com', 'Literature', 'Editor');
insert into credentials(username, password, details) values ('editor_literature', 'editor_literature', 18);
insert into editor(id, username, title, magazine, field) values (7, 'editor_literature', 'PhD in Literature', '87654321', 'LIT');

insert into user(id, city, country, email, first_name, last_name) values (19, 'Rome', 'Italy', 'editor.philosophy@magazine2.com', 'Philosophy', 'Editor');
insert into credentials(username, password, details) values ('editor_philosophy', 'editor_philosophy', 19);
insert into editor(id, username, title, magazine, field) values (8, 'editor_philosophy', 'MsC in Philosophy', '87654321', 'PHI');

insert into user(id, city, country, email, first_name, last_name) values (20, 'Kyev', 'Ukraine', 'editor.geography@magazine3.com', 'Geography', 'Editor');
insert into credentials(username, password, details) values ('editor_geography', 'editor_geography', 20);
insert into editor(id, username, title, magazine, field) values (9, 'editor_geography', 'MsC in Science', '74651xaa', 'GEO');

insert into user(id, city, country, email, first_name, last_name) values (21, 'Bucharest', 'Romaina', 'editor.history@magazine3.com', 'History', 'Editor');
insert into credentials(username, password, details) values ('editor_history', 'editor_history', 21);
insert into editor(id, username, title, magazine, field) values (10, 'editor_history', 'MsC in History', '74651xaa', 'HIS');

insert into user(id, city, country, email, first_name, last_name) values (22, 'Minsk', 'Belarus', 'editor.biology@magazine3.com', 'Biology', 'Editor');
insert into credentials(username, password, details) values ('editor_biology', 'editor_biology', 22);
insert into editor(id, username, title, magazine, field) values (11, 'editor_biology', 'MsC in Science', '74651xaa', 'BIO');

insert into reviewer_magazine(magazine, reviewer) values ('12345678', 2);
insert into reviewer_magazine(magazine, reviewer) values ('12345678', 3);
insert into reviewer_magazine(magazine, reviewer) values ('12345678', 4);
insert into reviewer_magazine(magazine, reviewer) values ('74651xaa', 2);
insert into reviewer_magazine(magazine, reviewer) values ('74651xaa', 6);
insert into reviewer_magazine(magazine, reviewer) values ('74651xaa', 7);
insert into reviewer_magazine(magazine, reviewer) values ('74651xaa', 8);
insert into reviewer_magazine(magazine, reviewer) values ('74651xaa', 9);
insert into reviewer_magazine(magazine, reviewer) values ('87654321', 1);
insert into reviewer_magazine(magazine, reviewer) values ('87654321', 5);

--------------------------------------------------------------------------------------------------------------------

insert into user(id, city, country, email, first_name, last_name) values (23, 'Chicago', 'USA', 'milli.624@yahoo.com', 'Robert', 'Millikan');
insert into credentials(username, password, details) values ('raMillikan', 'avogadro', 23);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 1, 'elementary particles, Avogadro, charge, physics', 'The Millikan oil drop experiment, published in final form in 1913, demonstrated that charge comes in discrete chunks and was a bridge between classical electromagnetism and modern quantum physics.', 'MAJOR_PAPER_CORRECTION', 'On the Elementary Electrical Charge and the Avogadro Constant' ,'raMillikan' ,'PHY', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (24, 'New York', 'USA', 'davis.nickel@yahoo.com', 'Clinton', 'Davisson');
insert into credentials(username, password, details) values ('cDavisson', 'nickel', 24);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 2, 'diffraction, electron, nickel, physics', 'The intensity of scattering of a homogeneous beam of electrons of adjustable speed incident upon a single crystal of nickel has been measured as a function of direction. The crystal is cut parallel to a set of its {111}-planes and bombardment is at normal incidence. The distribution in latitude and azimuth has been determined for such scattered electrons as have lost little or none of their incident energy.', 'STRUCTURE_REVISION', 'Diffraction of Electrons by a Crystal of Nickel','cDavisson' ,'PHY', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (25, 'Saint Louis', 'USA', 'compMan.XX@yahoo.com', 'Arthur', 'Compton');
insert into credentials(username, password, details) values ('aCompton', 'compMan', 25);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 3, 'x-rays, gamma-rays, light particles', 'A quantum theory of the scattering of X-rays and gamma-rays by light elements.—The hypothesis is suggested that when an X-ray quantum is scattered it spends all of its energy and momentum upon some particular electron.', 'MAJOR_PAPER_CORRECTION', 'A Quantum Theory of the Scattering of X-rays by Light Elements', 'aCompton' ,'PHY', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (26, 'Pasadena', 'USA', 'surf.s.up@yahoo.com', 'Carl', 'Anderson');
insert into credentials(username, password, details) values ('cAnderson', 'surfer', 26);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine, accepted) values (123, 4, 'electron, quantum, charge', 'Out of a group of 1300 photographs of cosmic-ray tracks in a vertical Wilson chamber 15 tracks were of positive particles which could not have a mass as great as that of the proton. From an examination of the energy-loss and ionization produced it is concluded that the charge is less than twice, and is probably exactly equal to, that of the proton.', 'MINOR_PAPER_CORRECTION', 'The Positive Electron', 'cAnderson' ,'PHY', '12345678', true);

insert into user(id, city, country, email, first_name, last_name) values (27, 'Ithaca', 'USA', 'deutschland.4life@yahoo.com', 'Hans', 'Bethe');
insert into credentials(username, password, details) values ('hBeethe', 'deutschland', 27);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine, accepted) values (123, 5, 'stars, Hidrogen, Helium', 'Finally, the suggested mechanism of energy production is used to draw conclusions about astrophysical problems, such as the mass-luminosity relation, the stability against temperature changes, and stellar evolution.', 'THEME_REVISION', 'Energy Production in Stars', 'hBeethe' ,'PHY', '12345678', false);

insert into user(id, city, country, email, first_name, last_name) values (28, 'Saint Louis', 'USA', 'proteins@yahoo.com', 'Oliver', 'Lowry');
insert into credentials(username, password, details) values ('oLowry', 'proteins', 28);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 6, 'Folin-phenol, protein, reagent', 'Since 1922 when Wu proposed the use of the Folin phenol reagent for the measurement of proteins, a number of modified analytical procedures utilizing this reagent have been reported for the determination of proteins in serum, in antigen-antibody precipitates and in insulin.', 'REVIEW', 'Protein measurement with the folin phenol reagent.' ,'oLowry' ,'CHE', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (29, 'Cambridge', 'United Kingdom', 'goblin@yahoo.com', 'Urlich', 'Laemmli');
insert into credentials(username, password, details) values ('urlichK', 'urlick', 29);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 7, 'bacteriofag, proteinm cleavage, assembly', 'Using an improved method of gel electrophoresis, many hitherto unknown proteins have been found in bacteriophage T4 and some of these have been identified with specific gene products. Four major components of the head are cleaved during the process of assembly, apparently after the precursor proteins have assembled into some large intermediate structure.', 'APPLICATION_CORRECTION', 'Cleavage of structural proteins during the assembly of the head of bacteriophage T4.','urlichK' ,'CHE', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (30, 'Boston', 'USA', 'the_cool.man@yahoo.com', 'James', 'Cooley');
insert into credentials(username, password, details) values ('jCool', 'cooleyMan', 30);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 8, 'fourier, algorithm, machine calculation', 'An efficient method for the calculation of the interactions of a 2n factorial experiment was introduces by Yates and is widely known by his name.','STRUCTURE_REVISION', 'An algorithm for the machine calculation of complex Fourier series', 'jCool' ,'MAT', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (31, 'Paris', 'France', 'remmy@yahoo.com', 'Remmy', 'Courant');
insert into credentials(username, password, details) values ('rCourant', 'french', 31);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 9, 'differential equasions, partial difference, mathematical physics', 'Problems involving the classical linear partial differential equations canbe reduced to algebraic ones of a very much simpler structure by replacing the differentials by difference quotients on some mesh.', 'APPLICATION_CORRECTION', 'On the partial difference equations of mathematical physics', 'rCourant' ,'MAT', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (32, 'Oak Ridge', 'USA', 'house@yahoo.com', 'Alston', 'Householder');
insert into credentials(username, password, details) values ('householder', 'drHouse', 32);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 10, 'triangularization, matrix, nonsymmetric matrix', 'A method for the inversion of a nonsymmetric matrix, due to J. W. Givens, has been in use at Oak Ridge National Laboratory and has proved to be highly stable numerically but to require a rather large number of arithmetic operations', 'REVIEWER_PROPOSAL', 'Unitary Triangularization of a Nonsymmetric Matrix', 'householder' ,'MAT', '12345678');

insert into user(id, city, country, email, first_name, last_name) values (33, 'Athens', 'Greece', 'socrates@yahoo.com', 'Plato', 'Man');
insert into credentials(username, password, details) values ('plato', 'Platoooo', 33);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 11, 'political science, utopias', 'Old view of the ideal class order and political views', 'MINOR_PAPER_CORRECTION', 'The Republic', 'plato' ,'PHI', '87654321');

insert into user(id, city, country, email, first_name, last_name) values (34, 'San Diego', 'USA', 'pig@yahoo.com', 'Andrew', 'Bacon');
insert into credentials(username, password, details) values ('aBacon', 'piggy', 34);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 12, 'modal logic, free logic, empty names', 'The result of combining classical quantificational logic with modal logic proves necessitism – the claim that necessarily everything is necessarily identical to something.', 'REVIEW_ANALYSIS', 'Quantificational Logic and Empty Names', 'aBacon' ,'PHI', '87654321');

insert into user(id, city, country, email, first_name, last_name) values (35, 'Oxford', 'United Kingdom', 'dna@yahoo.com', 'Watson', 'Creek');
insert into credentials(username, password, details) values ('watsonC', 'reek', 35);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 13, 'molecular structure, nucleic acids, deoxyribose nucleic acid', 'A structure for the salt of the deoxyribose nucleic acid.', 'APPLICATION_CORRECTION', 'Molecular structure of nucleic acids', 'watsonC' ,'BIO', '74651xaa');

insert into user(id, city, country, email, first_name, last_name) values (36, 'Stockholm', 'Sweden', 'genes@yahoo.com', 'Gregor', 'Mendel');
insert into credentials(username, password, details) values ('mendelG', 'genes', 36);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 14, 'plant, hybrids, genetics', 'EXPERIENCE OF ARTIFICIAL FERTILIZATION, such as is effected with ornamental plants in order to obtain new variations in color, has led to the experiments which will here be discussed','MINOR_PAPER_CORRECTION', 'EXPERIMENTS IN PLANT HYBRIDIZATION ', 'mendelG' ,'BIO', '74651xaa');

insert into user(id, city, country, email, first_name, last_name) values (37, 'Madison', 'USA', 'gibbs@yahoo.com', 'H.K', 'Gibbs');
insert into credentials(username, password, details) values ('hkGibbs', 'Gibson', 37);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 15, 'degraded lands, global agriculture, land use', 'Degraded lands have often been suggested as a solution to issues of land scarcity and as an ideal way to meet mounting global demands for agricultural goods, but their locations and conditions are not well known.', 'REVIEW', 'Mapping the worlds degraded lands', 'hkGibbs' ,'GEO', '74651xaa');

insert into user(id, city, country, email, first_name, last_name) values (38, 'Bern', 'Switzerland', 'alps@yahoo.com', 'P.H.', 'Verburg');
insert into credentials(username, password, details) values ('phVerburg', 'vehrer', 38);
insert into application(file, id, keywords, abstract, state, title, author, field, magazine) values (123, 16, 'Dyn-CLUE, Land use change, Modelling, Scenarios, Spatially explicit', 'In Switzerland, the decreasing significance of agriculture has led to prominent processes of land abandonment in mountainous areas where the maintenance of open land relies on human intervention.', 'REVIEW_ANALYSIS', 'Future landscapes of Switzerland: Risk areas for urbanisation and land abandonment', 'phVerburg' ,'GEO', '74651xaa');

---------------------------------------------------------------------------------------------------------------
insert into task(id, deadline, finished, summary, type, application, assignee) values (1,'2018-03-12', 1, '', 'THEME_REVISION', 1, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 1);
insert into task(id, deadline, finished, summary, type, application, assignee) values (2,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 1, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 2);
insert into task(id, deadline, finished, summary, type, application, assignee) values (3,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 1, 'editor_physics');
insert into comment(public_comment, review_summary, task) values ('Peter and Anne', null, 3);
insert into task(id, deadline, finished, summary, type, application, assignee) values (4,'2018-03-25', 1, '', 'REVIEW', 1, 'peter');
insert into comment(public_comment, review_summary, task) values ('Not bad', 'MAJOR_CHANGES_NEEDED', 4);
insert into task(id, deadline, finished, summary, type, application, assignee) values (5,'2018-03-25', 1, '', 'REVIEW', 1, 'anne');
insert into comment(public_comment, review_summary, task) values ('Some parts need citation', 'MINOR_CHANGES_NEEDED', 5);
insert into task(id, deadline, finished, summary, type, application, assignee) values (6,'2018-03-30', 1, '', 'REVIEW_ANALYSIS', 1, 'editor_physics');
insert into comment(public_comment, review_summary, task) values ('Agree with Peter', 'MAJOR_CHANGES_NEEDED', 6);
insert into task(id, deadline, finished, summary, type, application, assignee) values (7,'2019-04-30', 0, '', 'MAJOR_PAPER_CORRECTION', 1, 'raMillikan');

insert into task(id, deadline, finished, summary, type, application, assignee) values (8,'2018-03-12', 1, '', 'THEME_REVISION', 2, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 8);
insert into task(id, deadline, finished, summary, type, application, assignee) values (9,'2019-03-12', 0, '', 'STRUCTURE_REVISION', 2, 'editor1');

insert into task(id, deadline, finished, summary, type, application, assignee) values (10,'2018-03-12', 1, '', 'THEME_REVISION', 3, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 10);
insert into task(id, deadline, finished, summary, type, application, assignee) values (11,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 3, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 11);
insert into task(id, deadline, finished, summary, type, application, assignee) values (12,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 3, 'editor_physics');
insert into comment(public_comment, review_summary, task) values ('Peter and Anne', null, 12);
insert into task(id, deadline, finished, summary, type, application, assignee) values (13,'2018-03-25', 1, '', 'REVIEW', 3, 'peter');
insert into comment(public_comment, review_summary, task) values ('Lot of grammar errors', 'MINOR_CHANGES_NEEDED', 13);
insert into task(id, deadline, finished, summary, type, application, assignee) values (14,'2018-03-25', 1, '', 'REVIEW', 3, 'anne');
insert into comment(public_comment, review_summary, task) values ('A LOT of grammar errors', 'MAJOR_CHANGES_NEEDED', 14);
insert into task(id, deadline, finished, summary, type, application, assignee) values (15,'2018-03-30', 1, '', 'REVIEW_ANALYSIS', 3, 'editor_physics');
insert into comment(public_comment, review_summary, task) values ('Agree with both, Anne has a point', 'MAJOR_CHANGES_NEEDED', 15);
insert into task(id, deadline, finished, summary, type, application, assignee) values (16,'2019-04-30', 0, '', 'MAJOR_PAPER_CORRECTION', 3, 'aCompton');

insert into task(id, deadline, finished, summary, type, application, assignee) values (17,'2018-03-12', 1, '', 'THEME_REVISION', 4, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 17);
insert into task(id, deadline, finished, summary, type, application, assignee) values (18,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 4, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 18);
insert into task(id, deadline, finished, summary, type, application, assignee) values (19,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 4, 'editor_physics');
insert into comment(public_comment, review_summary, task) values ('Peter and Anne', null, 19);
insert into task(id, deadline, finished, summary, type, application, assignee) values (20,'2018-03-25', 1, '', 'REVIEW', 4, 'peter');
insert into comment(public_comment, review_summary, task) values ('Paper is not formatted properly', 'MINOR_CHANGES_NEEDED', 20);
insert into task(id, deadline, finished, summary, type, application, assignee) values (21,'2018-03-25', 1, '', 'REVIEW', 4, 'anne');
insert into comment(public_comment, review_summary, task) values ('Not formatted properly', 'MINOR_CHANGES_NEEDED', 21);
insert into task(id, deadline, finished, summary, type, application, assignee) values (22,'2018-03-30', 1, '', 'REVIEW_ANALYSIS', 4, 'editor_physics');
insert into comment(public_comment, review_summary, task) values ('Needs proper formatting', 'MINOR_CHANGES_NEEDED', 22);
insert into task(id, deadline, finished, summary, type, application, assignee) values (23,'2019-04-30', 0, '', 'MINOR_PAPER_CORRECTION', 4, 'cAnderson');

insert into task(id, deadline, finished, summary, type, application, assignee) values (24,'2019-03-12', 0, '', 'THEME_REVISION', 5, 'editor1');

insert into task(id, deadline, finished, summary, type, application, assignee) values (25,'2018-03-12', 1, '', 'THEME_REVISION', 6, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 25);
insert into task(id, deadline, finished, summary, type, application, assignee) values (26,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 6, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 26);
insert into task(id, deadline, finished, summary, type, application, assignee) values (27,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 6, 'editor_chemistry');
insert into comment(public_comment, review_summary, task) values ('Peter and Anne', null, 27);
insert into task(id, deadline, finished, summary, type, application, assignee) values (28,'2018-03-25', 0, '', 'REVIEW', 6, 'peter');
insert into task(id, deadline, finished, summary, type, application, assignee) values (29,'2019-03-25', 0, '', 'REVIEW', 6, 'anne');

insert into task(id, deadline, finished, summary, type, application, assignee) values (30,'2018-03-12', 1, '', 'THEME_REVISION', 7, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 30);
insert into task(id, deadline, finished, summary, type, application, assignee) values (31,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 7, 'editor1');
insert into comment(public_comment, review_summary, task) values ('Paper does not have references', 'REJECT', 31);
insert into task(id, deadline, finished, summary, type, application, assignee) values (32,'2019-03-15', 0, '', 'APPLICATION_CORRECTION', 7, 'urlichK');

insert into task(id, deadline, finished, summary, type, application, assignee) values (33,'2018-03-12', 1, '', 'THEME_REVISION', 8, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 33);
insert into task(id, deadline, finished, summary, type, application, assignee) values (34,'2019-03-12', 0, '', 'STRUCTURE_REVISION', 8, 'editor1');

insert into task(id, deadline, finished, summary, type, application, assignee) values (35,'2018-03-12', 1, '', 'THEME_REVISION', 9, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 35);
insert into task(id, deadline, finished, summary, type, application, assignee) values (36,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 9, 'editor1');
insert into comment(public_comment, review_summary, task) values ('Paper does not have references', 'REJECT', 36);
insert into task(id, deadline, finished, summary, type, application, assignee) values (37,'2019-03-15', 0, '', 'APPLICATION_CORRECTION', 9, 'rCourant');

insert into task(id, deadline, finished, summary, type, application, assignee) values (38,'2018-03-12', 1, '', 'THEME_REVISION', 10, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 38);
insert into task(id, deadline, finished, summary, type, application, assignee) values (39,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 10, 'editor1');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 39);
insert into task(id, deadline, finished, summary, type, application, assignee) values (40,'2019-03-15', 0, '', 'REVIEWER_PROPOSAL', 10, 'editor_mathematics');

insert into task(id, deadline, finished, summary, type, application, assignee) values (41,'2018-03-12', 1, '', 'THEME_REVISION', 11, 'editor2');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 41);
insert into task(id, deadline, finished, summary, type, application, assignee) values (42,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 11, 'editor2');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 42);
insert into task(id, deadline, finished, summary, type, application, assignee) values (43,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 11, 'editor_philosophy');
insert into comment(public_comment, review_summary, task) values ('Mary and Jack', null, 43);
insert into task(id, deadline, finished, summary, type, application, assignee) values (44,'2018-03-25', 1, '', 'REVIEW', 11, 'mary');
insert into comment(public_comment, review_summary, task) values ('I do not agree with the premise given in second chapter', 'MAJOR_CHANGES_NEEDED', 44);
insert into task(id, deadline, finished, summary, type, application, assignee) values (45,'2018-03-25', 1, '', 'REVIEW', 11, 'jack');
insert into comment(public_comment, review_summary, task) values ('Second chapter is not presented coherently', 'MAJOR_CHANGES_NEEDED', 45);
insert into task(id, deadline, finished, summary, type, application, assignee) values (46,'2018-03-30', 1, '', 'REVIEW_ANALYSIS', 11, 'editor_philosophy');
insert into comment(public_comment, review_summary, task) values ('Agree with reviewers', 'MAJOR_CHANGES_NEEDED', 46);
insert into task(id, deadline, finished, summary, type, application, assignee) values (47,'2019-04-30', 0, '', 'MAJOR_PAPER_CORRECTION', 11, 'plato');

insert into task(id, deadline, finished, summary, type, application, assignee) values (48,'2018-03-12', 1, '', 'THEME_REVISION', 12, 'editor2');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 48);
insert into task(id, deadline, finished, summary, type, application, assignee) values (49,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 12, 'editor2');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 49);
insert into task(id, deadline, finished, summary, type, application, assignee) values (50,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 12, 'editor_philosophy');
insert into comment(public_comment, review_summary, task) values ('Mary and Jack', null, 50);
insert into task(id, deadline, finished, summary, type, application, assignee) values (51,'2018-03-25', 1, '', 'REVIEW', 12, 'mary');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 51);
insert into task(id, deadline, finished, summary, type, application, assignee) values (52,'2018-03-25', 1, '', 'REVIEW', 12, 'jack');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 52);
insert into task(id, deadline, finished, summary, type, application, assignee) values (53,'2019-03-30', 0, '', 'REVIEW_ANALYSIS', 12, 'editor_philosophy');

insert into task(id, deadline, finished, summary, type, application, assignee) values (54,'2018-03-12', 1, '', 'THEME_REVISION', 13, 'editor3');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 54);
insert into task(id, deadline, finished, summary, type, application, assignee) values (55,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 13, 'editor3');
insert into comment(public_comment, review_summary, task) values ('Needs citation', 'REJECT', 55);
insert into task(id, deadline, finished, summary, type, application, assignee) values (56,'2019-03-15', 0, '', 'APPLICATION_CORRECTION', 13, 'watsonC');

insert into task(id, deadline, finished, summary, type, application, assignee) values (57,'2018-03-12', 1, '', 'THEME_REVISION', 14, 'editor3');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 57);
insert into task(id, deadline, finished, summary, type, application, assignee) values (58,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 14, 'editor3');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 58);
insert into task(id, deadline, finished, summary, type, application, assignee) values (59,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 14, 'editor_biology');
insert into comment(public_comment, review_summary, task) values ('Lev and Li-Chun', null, 59);
insert into task(id, deadline, finished, summary, type, application, assignee) values (60,'2018-03-25', 1, '', 'REVIEW', 14, 'levY');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 60);
insert into task(id, deadline, finished, summary, type, application, assignee) values (61,'2018-03-25', 1, '', 'REVIEW', 14, 'li');
insert into comment(public_comment, review_summary, task) values ('Some minor grammar errors', 'MINOR_CHANGES_NEEDED', 61);
insert into task(id, deadline, finished, summary, type, application, assignee) values (62,'2018-03-30', 1, '', 'REVIEW_ANALYSIS', 14, 'editor_biology');
insert into comment(public_comment, review_summary, task) values ('Li-Chun has a point', 'MINOR_CHANGES_NEEDED', 62);
insert into task(id, deadline, finished, summary, type, application, assignee) values (63,'2019-03-30', 0, '', 'MINOR_PAPER_CORRECTION', 14, 'mendelG');

insert into task(id, deadline, finished, summary, type, application, assignee) values (64,'2018-03-12', 1, '', 'THEME_REVISION', 15, 'editor3');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 64);
insert into task(id, deadline, finished, summary, type, application, assignee) values (65,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 15, 'editor3');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 65);
insert into task(id, deadline, finished, summary, type, application, assignee) values (66,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 15, 'editor_geography');
insert into comment(public_comment, review_summary, task) values ('Charlene and Brigitte', null, 66);
insert into task(id, deadline, finished, summary, type, application, assignee) values (67,'2018-03-25', 0, '', 'REVIEW', 15, 'charleneW');
insert into task(id, deadline, finished, summary, type, application, assignee) values (68,'2019-03-25', 0, '', 'REVIEW', 15, 'macronB');

insert into task(id, deadline, finished, summary, type, application, assignee) values (69,'2018-03-12', 1, '', 'THEME_REVISION', 16, 'editor3');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 69);
insert into task(id, deadline, finished, summary, type, application, assignee) values (70,'2018-03-12', 1, '', 'STRUCTURE_REVISION', 16, 'editor3');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 70);
insert into task(id, deadline, finished, summary, type, application, assignee) values (71,'2018-03-15', 1, '', 'REVIEWER_PROPOSAL', 16, 'editor_geography');
insert into comment(public_comment, review_summary, task) values ('Charlene and Brigitte', null, 71);
insert into task(id, deadline, finished, summary, type, application, assignee) values (72,'2018-03-25', 1, '', 'REVIEW', 16, 'macronB');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 72);
insert into task(id, deadline, finished, summary, type, application, assignee) values (73,'2018-03-25', 1, '', 'REVIEW', 16, 'charleneW');
insert into comment(public_comment, review_summary, task) values ('OK', 'ACCEPT', 73);
insert into task(id, deadline, finished, summary, type, application, assignee) values (74,'2019-03-30', 0, '', 'REVIEW_ANALYSIS', 16, 'editor_geography');

