INSERT INTO category (id, name) values (1, 'Holy Grail');
INSERT INTO category (id, name) values (2, 'Cheese Shop');
INSERT INTO category (id, name) values (3, 'Life of Brian');
INSERT INTO category (id, name) values (4, 'International Philosophy Match');

INSERT INTO question(id,category,question,answer) values (1,1,'What do the Knights of Ni say?','Ni!');
INSERT INTO question(id,category,question,answer) values (2,1,'What is the airspeed velocity of an unladen swallow?','African or European?');
INSERT INTO question(id,category,question,answer) values (3,1,'What is the shape of the beacon at castle Anthrax?','Grail Shaped.');
INSERT INTO question(id,category,question,answer) values (4,2,'Why is the cheese shop so clean?','Because there is no cheese in it.');
INSERT INTO question(id,category,question,answer) values (5,3,'What have the Romans ever done for us?','Apart from better sanitation and medicine and education and irrigation and public health and roads and a freshwater system and baths and public order... nothing.');
INSERT INTO question(id,category,question,answer) values (6,4,'Who wins, Germany or Greece?','Greece');
INSERT INTO question(id,category,question,answer) values (7,4,'What is the final score in the match?','Greece 1, Germany 0');
commit;