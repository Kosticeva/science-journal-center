insert into science_field(code, field) values ('PHY', 'physics');
insert into science_field(code, field) values ('CHE', 'chemistry');
insert into science_field(code, field) values ('LIT', 'literature');
insert into science_field(code, field) values ('MAT', 'mathematics');
insert into science_field(code, field) values ('GEO', 'geography');
insert into science_field(code, field) values ('HIS', 'history');
insert into science_field(code, field) values ('BIO', 'biology');
insert into science_field(code, field) values ('PHI', 'philosophy');
insert into science_field(code, field) values ('SSC', 'social sciences')


insert into credentials(username, password) values ('jelena', 'jelena');
insert into user(username, city, country, email, first_name, last_name) values ('jelena', 'Ruma', 'Serbia', 'kosticka.jelena@gmail.com', 'Jelena', 'Kostic');

insert into credentials(username, password) values ('admin', 'admin');
insert into user(username, city, country, email, first_name, last_name) values ('admin', 'Novi Sad', 'Serbia', 'admin@science-journal.com', 'Admin', 'Administrator');

insert into credentials(username, password) values ('mary', 'mary');
insert into user(username, city, country, email, first_name, last_name) values ('mary', 'Boston', 'USA', 'mary.redsox@yahoo.com', 'Mary', 'Masters');
insert into reviewer(id, username, title) values (1, 'mary', 'MsC in Social Sciences');

insert into credentials(username, password) values ('peter', 'peter');
insert into user(username, city, country, email, first_name, last_name) values ('peter', 'Livingstone', 'South Africa', 'peter.svahili@live.com', 'Peter', 'Peterson');
insert into reviewer(id, username, title) values (2, 'peter', 'PhD in Science');

insert into credentials(username, password) values ('john', 'john');
insert into user(username, city, country, email, first_name, last_name) values ('john', 'Perth', 'Australia', 'john.cangaroo@outlook.com', 'John', 'Doe');
insert into reviewer(id, username, title) values (3, 'john', 'MsC in Science');

insert into credentials(username, password) values ('editor1', 'editor');
insert into user(username, city, country, email, first_name, last_name) values ('editor1', 'Istanbul', 'Turkey', 'editor.chief@magazine1.com', 'Chief', 'Editor1');
insert into editor(id, username, title) values (1, 'editor1', 'PhD in Science');

insert into credentials(username, password) values ('editor2', 'editor');
insert into user(username, city, country, email, first_name, last_name) values ('editor2', 'Moscow', 'Russia', 'editor.chief@magazine2.com', 'Chief', 'Editor2');
insert into editor(id, username, title) values (2, 'editor2', 'PhD in Social Sciences');

insert into credentials(username, password) values ('editor3', 'editor');
insert into user(username, city, country, email, first_name, last_name) values ('editor3', 'Saint Petersburg', 'Russia', 'editor.chief@magazine3.com', 'Chief', 'Editor3');
insert into editor(id, username, title) values (3, 'editor3', 'PhD in Science');

insert into credentials(username, password) values ('editor_physics', 'editor_physics');
insert into user(username, city, country, email, first_name, last_name) values ('editor_physics', 'London', 'United Kingdom', 'editor.physics@magazine1.com', 'Physics', 'Editor');
insert into editor(id, username, title) values (4, 'editor_physics', 'PhD in Science');

insert into credentials(username, password) values ('editor_chemistry', 'editor_chemistry');
insert into user(username, city, country, email, first_name, last_name) values ('editor_chemistry', 'Berlin', 'Germany', 'editor.chemistry@magazine1.com', 'Chemistry', 'Editor');
insert into editor(id, username, title) values (5, 'editor_chemistry', 'PhD in Science');

insert into credentials(username, password) values ('editor_mathematics', 'editor_mathematics');
insert into user(username, city, country, email, first_name, last_name) values ('editor_mathematics', 'Madrid', 'Spain', 'editor.mathematics@magazine1.com', 'Mathematics', 'Editor');
insert into editor(id, username, title) values (6, 'editor_mathematics', 'MsC in Science');

insert into credentials(username, password) values ('editor_literature', 'editor_literature');
insert into user(username, city, country, email, first_name, last_name) values ('editor_literature', 'Paris', 'France', 'editor.literature@magazine2.com', 'Literature', 'Editor');
insert into editor(id, username, title) values (7, 'editor_literature', 'PhD in Literature');

insert into credentials(username, password) values ('editor_philosophy', 'editor_philosophy');
insert into user(username, city, country, email, first_name, last_name) values ('editor_philosophy', 'Rome', 'Italy', 'editor.philosophy@magazine2.com', 'Philosophy', 'Editor');
insert into editor(id, username, title) values (8, 'editor_philosophy', 'MsC in Philosophy');

insert into credentials(username, password) values ('editor_geography', 'editor_geography');
insert into user(username, city, country, email, first_name, last_name) values ('editor_geography', 'Kyev', 'Ukraine', 'editor.geography@magazine3.com', 'Geography', 'Editor');
insert into editor(id, username, title) values (9, 'editor_geography', 'MsC in Science');

insert into credentials(username, password) values ('editor_history', 'editor_history');
insert into user(username, city, country, email, first_name, last_name) values ('editor_history', 'Bucharest', 'Romaina', 'editor.history@magazine3.com', 'History', 'Editor');
insert into editor(id, username, title) values (10, 'editor_history', 'MsC in History');

insert into credentials(username, password) values ('editor_biology', 'editor_biology');
insert into user(username, city, country, email, first_name, last_name) values ('editor_biology', 'Minsk', 'Belarus', 'editor.biology@magazine3.com', 'Biology', 'Editor');
insert into editor(id, username, title) values (11, 'editor_biology', 'MsC in Science');

insert into magazine(issn, membership, name, price, type, chief_editor) values ('12345678', 0, 'Chemistry, Physics and Mathematics Review', 0, 'OPEN_ACCESS', 1);
insert into magazine(issn, membership, name, price, type, chief_editor) values ('87654321', 0, 'Biology, History and Geography Journal', 0, 'OPEN_ACCESS', 2);
insert into magazine(issn, membership, name, price, type, chief_editor) values ('74651xaa', 500, 'On Literature And Philosophy', 50, 'PAID_ACCESS', 3);

insert into board(magazine_issn, field_code, editor_id) values ('12345678', 'PHY', 4);
insert into board(magazine_issn, field_code, editor_id) values ('12345678', 'CHE', 5);
insert into board(magazine_issn, field_code, editor_id) values ('12345678', 'MAT', 6);
insert into board(magazine_issn, field_code, editor_id) values ('87654321', 'PHI', 7);
insert into board(magazine_issn, field_code, editor_id) values ('87654321', 'LIT', 8);
insert into board(magazine_issn, field_code, editor_id) values ('87654321', 'SSC', 7);
insert into board(magazine_issn, field_code, editor_id) values ('74651xaa', 'GEO', 9);
insert into board(magazine_issn, field_code, editor_id) values ('74651xaa', 'HIS', 10);
insert into board(magazine_issn, field_code, editor_id) values ('74651xaa', 'BIO', 11);

insert into reviewer_magazine(magazine_issn, reviewer_id) values ('12345678', 2);
insert into reviewer_magazine(magazine_issn, reviewer_id) values ('74651xaa', 2);
insert into reviewer_magazine(magazine_issn, reviewer_id) values ('12345678', 3);
insert into reviewer_magazine(magazine_issn, reviewer_id) values ('87654321', 1);


insert into credentials(username, password) values ('raMillikan', 'avogadro');
insert into user(username, city, country, email, first_name, last_name) values ('raMillikan', 'Chicago', 'USA', 'milli.624@yahoo.com', 'Robert', 'Millikan');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 1, 'elementary particles, Avogadro, charge, physics', 'The Millikan oil drop experiment, published in final form in 1913, demonstrated that charge comes in discrete chunks and was a bridge between classical electromagnetism and modern quantum physics.',100, 'MAJOR_PAPER_CORRECTION', 'On the Elementary Electrical Charge and the Avogadro Constant' ,'raMillikan' ,'PHY', '12345678');

insert into credentials(username, password) values ('cDavisson', 'nickel');
insert into user(username, city, country, email, first_name, last_name) values ('cDavisson', 'New York', 'USA', 'davis.nickel@yahoo.com', 'Clinton', 'Davisson');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 2, 'diffraction, electron, nickel, physics', 'The intensity of scattering of a homogeneous beam of electrons of adjustable speed incident upon a single crystal of nickel has been measured as a function of direction. The crystal is cut parallel to a set of its {111}-planes and bombardment is at normal incidence. The distribution in latitude and azimuth has been determined for such scattered electrons as have lost little or none of their incident energy.', 100, 'STRUCTURE_REVISION', 'Diffraction of Electrons by a Crystal of Nickel','cDavisson' ,'PHY', '12345678');

insert into credentials(username, password) values ('aCompton', 'compMan');
insert into user(username, city, country, email, first_name, last_name) values ('aCompton', 'Saint Louis', 'USA', 'compMan.XX@yahoo.com', 'Arthur', 'Compton');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 3, 'x-rays, gamma-rays, light particles', 'A quantum theory of the scattering of X-rays and gamma-rays by light elements.—The hypothesis is suggested that when an X-ray quantum is scattered it spends all of its energy and momentum upon some particular electron.', 100, 'MAJOR_PAPER_CORRECTION', 'A Quantum Theory of the Scattering of X-rays by Light Elements', 'aCompton' ,'PHY', '12345678');

insert into credentials(username, password) values ('cAnderson', 'surfer');
insert into user(username, city, country, email, first_name, last_name) values ('cAnderson', 'Pasadena', 'USA', 'surf.s.up@yahoo.com', 'Carl', 'Anderson');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn, accepted) values (123, 4, 'electron, quantum, charge', 'Out of a group of 1300 photographs of cosmic-ray tracks in a vertical Wilson chamber 15 tracks were of positive particles which could not have a mass as great as that of the proton. From an examination of the energy-loss and ionization produced it is concluded that the charge is less than twice, and is probably exactly equal to, that of the proton.', 100, 'MINOR_PAPER_CORRECTION', 'The Positive Electron', 'cAnderson' ,'PHY', '12345678', true);

insert into credentials(username, password) values ('hBeethe', 'deutschland');
insert into user(username, city, country, email, first_name, last_name) values ('hBeethe', 'Ithaca', 'USA', 'deutschland.4life@yahoo.com', 'Hans', 'Bethe');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn, accepted) values (123, 5, 'stars, Hidrogen, Helium', 'Finally, the suggested mechanism of energy production is used to draw conclusions about astrophysical problems, such as the mass-luminosity relation, the stability against temperature changes, and stellar evolution.', 100, 'THEME_REVISION', 'Energy Production in Stars', 'hBeethe' ,'PHY', '12345678', false);







insert into credentials(username, password) values ('oLowry', 'proteins');
insert into user(username, city, country, email, first_name, last_name) values ('oLowry', 'Saint Louis', 'USA', 'proteins@yahoo.com', 'Oliver', 'Lowry');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 6, 'Folin-phenol, protein, reagent', 'Since 1922 when Wu proposed the use of the Folin phenol reagent for the measurement of proteins, a number of modified analytical procedures utilizing this reagent have been reported for the determination of proteins in serum, in antigen-antibody precipitates and in insulin.', 100, 'REVIEW', 'Protein measurement with the folin phenol reagent.' ,'oLowry' ,'CHE', '12345678');

insert into credentials(username, password) values ('urlichK', 'urlick');
insert into user(username, city, country, email, first_name, last_name) values ('urlichK', 'Cambridge', 'United Kingdom', 'goblin@yahoo.com', 'Urlich', 'Laemmli');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 7, 'bacteriofag, proteinm cleavage, assembly', 'Using an improved method of gel electrophoresis, many hitherto unknown proteins have been found in bacteriophage T4 and some of these have been identified with specific gene products. Four major components of the head are cleaved during the process of assembly, apparently after the precursor proteins have assembled into some large intermediate structure.', 100, 'APPLICATION_CORRECTION', 'Cleavage of structural proteins during the assembly of the head of bacteriophage T4.','urlichK' ,'CHE', '12345678');




insert into credentials(username, password) values ('jCool', 'cooleyMan');
insert into user(username, city, country, email, first_name, last_name) values ('jCool', 'Boston', 'USA', 'the_cool.man@yahoo.com', 'James', 'Cooley');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 8, 'fourier, algorithm, machine calculation', 'An efficient method for the calculation of the interactions of a 2n factorial experiment was introduces by Yates and is widely known by his name.',100, 'STRUCTURE_REVISION', 'An algorithm for the machine calculation of complex Fourier series', 'jCool' ,'MAT', '12345678');

insert into credentials(username, password) values ('rCourant', 'french');
insert into user(username, city, country, email, first_name, last_name) values ('rCourant', 'Paris', 'France', 'remmy@yahoo.com', 'Remmy', 'Courant');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 9, 'differential equasions, partial difference, mathematical physics', 'Problems involving the classical linear partial differential equations canbe reduced to algebraic ones of a very much simpler structure by replacing the differentials by difference quotients on some mesh.', 100, 'APPLICATION_CORRECTION', 'On the partial difference equations of mathematical physics', 'rCourant' ,'MAT', '12345678');

insert into credentials(username, password) values ('householder', 'drHouse');
insert into user(username, city, country, email, first_name, last_name) values ('householder', 'Oak Ridge', 'Tennessee', 'house@yahoo.com', 'Alston', 'Householder');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 10, 'triangularization, matrix, nonsymmetric matrix', 'A method for the inversion of a nonsymmetric matrix, due to J. W. Givens, has been in use at Oak Ridge National Laboratory and has proved to be highly stable numerically but to require a rather large number of arithmetic operations', 100, 'REVIEWER_PROPOSAL', 'Unitary Triangularization of a Nonsymmetric Matrix', 'householder' ,'MAT', '12345678');


insert into credentials(username, password) values ('plato', 'Platoooo');
insert into user(username, city, country, email, first_name, last_name) values ('plato', 'Athens', 'Greece', 'socrates@yahoo.com', 'Plato', 'Man');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 11, 'political science, utopias', 'Old view of the ideal class order and political views', 200, 'MINOR_PAPER_CORRECTION', 'The Republic', 'plato' ,'PHI', '87654321');

insert into credentials(username, password) values ('aBacon', 'piggy');
insert into user(username, city, country, email, first_name, last_name) values ('aBacon', 'San Diego', 'USA', 'pig@yahoo.com', 'Andrew', 'Bacon');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 12, 'modal logic, free logic, empty names', 'The result of combining classical quantificational logic with modal logic proves necessitism – the claim that necessarily everything is necessarily identical to something.', 200, 'REVIEW_ANALYSIS', 'Quantificational Logic and Empty Names', 'aBacon' ,'PHI', '87654321');




insert into credentials(username, password) values ('watsonC', 'reek');
insert into user(username, city, country, email, first_name, last_name) values ('watsonC', 'Oxford', 'United Kingdom', 'dna@yahoo.com', 'Watson', 'Creek');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 13, 'molecular structure, nucleic acids, deoxyribose nucleic acid', 'A structure for the salt of the deoxyribose nucleic acid.',50, 'APPLICATION_CORRECTION', 'Molecular structure of nucleic acids', 'watsonC' ,'BIO', '74651xaa');

insert into credentials(username, password) values ('mendelG', 'genes');
insert into user(username, city, country, email, first_name, last_name) values ('mendelG', 'Stockholm', 'Sweden', 'genes@yahoo.com', 'Gregor', 'Mendel');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 14, 'plant, hybrids, genetics', 'EXPERIENCE OF ARTIFICIAL FERTILIZATION, such as is effected with ornamental plants in order to obtain new variations in color, has led to the experiments which will here be discussed',50, 'MINOR_PAPER_CORRECTION', 'EXPERIMENTS IN PLANT HYBRIDIZATION ', 'mendelG' ,'BIO', '74651xaa');


insert into credentials(username, password) values ('hkGibbs', 'Gibson');
insert into user(username, city, country, email, first_name, last_name) values ('hkGibbs', 'Madison', 'USA', 'gibbs@yahoo.com', 'H.K', 'Gibbs');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 15, 'degraded lands, global agriculture, land use', 'Degraded lands have often been suggested as a solution to issues of land scarcity and as an ideal way to meet mounting global demands for agricultural goods, but their locations and conditions are not well known.', 50, 'REVIEW', 'Mapping the world''s degraded lands', 'hkGibbs' ,'GEO', '74651xaa');

insert into credentials(username, password) values ('phVerburg', 'vehrer');
insert into user(username, city, country, email, first_name, last_name) values ('phVerburg', 'Bern', 'Switzerland', 'alps@yahoo.com', 'P.H.', 'Verburg');
insert into paper(file, id, keywords, abstract, price, state, title, author_username, field_code, magazine_issn) values (123, 16, 'Dyn-CLUE, Land use change, Modelling, Scenarios, Spatially explicit', 'In Switzerland, the decreasing significance of agriculture has led to prominent processes of land abandonment in mountainous areas where the maintenance of open land relies on human intervention.',50, 'REVIEW_ANALYSIS', 'Future landscapes of Switzerland: Risk areas for urbanisation and land abandonment', 'phVerburg' ,'GEO', '74651xaa');


--add more reviewers, make sure that the geo area is fine af
--add tasks, SELECT id, state, author_username, magazine_issn from paper