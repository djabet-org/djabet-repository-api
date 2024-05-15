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