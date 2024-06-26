CREATE TABLE public.double_rolls (
	id int NOT NULL auto_increment,
	created timestamp NULL,
	roll int4 NULL DEFAULT '0'::real,
	platform varchar NOT NULL,
	color varchar NOT NULL,
	total_red_money real NULL DEFAULT '0'::real,
	total_black_money real NULL DEFAULT '0'::real,
	total_white_money real NULL DEFAULT '0'::real,
    primary key(id)
);

CREATE TABLE public.crash_points (
	crash_point real NOT NULL,
	created timestamp NOT NULL,
	id int NOT NULL auto_increment,
	platform varchar NOT NULL,
	total_bets_placed int NULL DEFAULT 0,
	total_money_bets real NULL DEFAULT 0,
	total_money_bets_won real NULL DEFAULT 0,
	primary key(id)
);