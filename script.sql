DROP DATABASE testapp;

CREATE DATABASE testapp;

USE testapp;

CREATE TABLE users
(	
 id SERIAL,
 name VARCHAR (60),
 access_key VARCHAR (60),
 token VARCHAR (60),
 authentication_type INT,
 PRIMARY KEY (id)
);


CREATE TABLE groups
(
 id SERIAL,
 owner_id BIGINT UNSIGNED NOT NULL,
 userName VARCHAR (60),
 FOREIGN KEY (owner_id) REFERENCES users (id),
  PRIMARY KEY (id)
);

CREATE TABLE tests	
(
 id SERIAL,
 owner_id BIGINT UNSIGNED NOT NULL,
 group_id BIGINT UNSIGNED NOT NULL,
 title VARCHAR (60),
 start_time TIMESTAMP,
 end_time TIMESTAMP DEFAULT '1970-01-01 00:00:01',
 FOREIGN KEY (owner_id) REFERENCES users (id),
 FOREIGN KEY (group_id) REFERENCES groups (id),
 PRIMARY KEY (id)
);

CREATE TABLE group_participants
(
 user_id BIGINT UNSIGNED NOT NULL,
 group_id BIGINT UNSIGNED NOT NULL,
 status tinyint(1) NOT NULL DEFAULT '0',
 FOREIGN KEY (user_id) REFERENCES users (id),
 FOREIGN KEY (group_id) REFERENCES groups (id),
 PRIMARY KEY (user_id, group_id)
);

CREATE TABLE test_participants
(
 test_id BIGINT UNSIGNED NOT NULL,
 user_id BIGINT UNSIGNED NOT NULL,
 start_time TIMESTAMP,
 end_time TIMESTAMP DEFAULT '1970-01-01 00:00:01',
 grade FLOAT,
 FOREIGN KEY (user_id) REFERENCES users (id),
 FOREIGN KEY (test_id) REFERENCES tests (id),
 PRIMARY KEY (test_id, user_id)
);

CREATE TABLE test_questions_mc	
(
 id serial,
 test_id BIGINT UNSIGNED NOT NULL,
 question_text VARCHAR(300),
 option_1 VARCHAR(250),
 option_2 VARCHAR(250),
 option_3 VARCHAR(250),
 option_4 VARCHAR(250),
 option_5 VARCHAR(250),
 correct_answer INT UNSIGNED NOT NULL,
 value_pts FLOAT,
 FOREIGN KEY (test_id) REFERENCES tests (id),
 PRIMARY KEY (id)
);

CREATE TABLE answer_participant
(
 question_id BIGINT UNSIGNED NOT NULL,
 user_id BIGINT UNSIGNED NOT NULL,
 given_answer INT UNSIGNED NOT NULL,
 is_correct BOOLEAN,
 FOREIGN KEY (user_id) REFERENCES users (id),
 FOREIGN KEY (question_id) REFERENCES test_questions_mc (id),
 PRIMARY KEY (question_id, user_id)
);
