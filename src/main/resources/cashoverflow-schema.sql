DROP TABLE notifications;
DROP TABLE noti_entity;
DROP TABLE transactions;
DROP TABLE bank_accounts;
DROP TABLE account_types;
DROP TABLE tx_types;
DROP TABLE noti_messages;
DROP TABLE user_accounts;

CREATE TABLE user_accounts (
	id SERIAL PRIMARY KEY,
	email VARCHAR(254) UNIQUE NOT NULL,
	username VARCHAR(20) UNIQUE NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	"password" VARCHAR(100) NOT NULL,
	creation_date TIMESTAMP NOT NULL
);

CREATE TABLE noti_messages (
	id SERIAL PRIMARY KEY,
	contents VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE tx_types (
	id SERIAL PRIMARY KEY,
	"type" VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE account_types (
	id SERIAL PRIMARY KEY,
	"type" VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE bank_accounts (
	id SERIAL PRIMARY KEY,
	"name" VARCHAR(20) NOT NULL,
	balance NUMERIC(14, 2) NOT NULL,
	description VARCHAR(256),
	creation_date TIMESTAMP NOT NULL,
	account_type_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	FOREIGN KEY (account_type_id) REFERENCES account_types (id),
	FOREIGN KEY (user_id) REFERENCES user_accounts (id)
);

CREATE TABLE transactions (
	id SERIAL PRIMARY KEY,
	amount NUMERIC(14, 2) NOT NULL,
	description VARCHAR(256),
	creation_date TIMESTAMP NOT NULL,
	tx_type_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL,
	FOREIGN KEY (tx_type_id) REFERENCES tx_types (id),
	FOREIGN KEY (account_id) REFERENCES bank_accounts (id)
);

CREATE TABLE noti_entity (
	id SERIAL PRIMARY KEY,
	tx_id INTEGER,
	account_id INTEGER,
	FOREIGN KEY (tx_id) REFERENCES transactions (id),
	FOREIGN KEY (account_id) REFERENCES bank_accounts (id),
	CONSTRAINT entity_chk CHECK (tx_id IS NOT NULL OR account_id IS NOT NULL)
);

CREATE TABLE notifications (
	id SERIAL PRIMARY KEY,
	creation_date TIMESTAMP NOT NULL,
	is_read BOOLEAN NOT NULL,
	noti_message_id INTEGER NOT NULL,
	noti_type_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	FOREIGN KEY (noti_message_id) REFERENCES noti_messages (id),
	FOREIGN KEY (noti_type_id) REFERENCES noti_entity (id),
	FOREIGN KEY (user_id) REFERENCES user_accounts (id)
);

INSERT INTO account_types VALUES(DEFAULT, 'checking');
INSERT INTO account_types VALUES(DEFAULT, 'savings');

INSERT INTO tx_types VALUES(DEFAULT, 'expense');
INSERT INTO tx_types VALUES(DEFAULT, 'income');

INSERT INTO noti_messages VALUES(DEFAULT, 'test noti');
