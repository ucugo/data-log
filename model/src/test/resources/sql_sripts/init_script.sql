# CREATE DATABASE 'data_log';
# USE 'data_log';

CREATE TABLE 'user'(
'id' bigint(20) NOT NULL,
'firstname' VARCHAR(255) NOT NULL,
'lastname' VARCHAR(255) NOT NULL,
'username' VARCHAR(255) NOT NULL,
'password' VARCHAR(255) NOT NULL,
PRIMARY KEY ('id')
);

INSERT INTO 'user' ('id','firstname', 'username', 'username', 'password') VALUES (1, 'firstname', 'lastname', 'username', 'password');