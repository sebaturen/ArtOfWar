CREATE TABLE `character_info` (
	`internal_id`		int NOT NULL,
	`name`				varchar(20) NOT NULL,
	`realm`				varchar(50) NOT NULL,
	`battlegroup` 		varchar(50) NOT NULL,
	`class`				int NOT NULL,
	`race`				int NOT NULL,
	`gender`			int NOT NULL,
	`level`				int NOT NULL,
	`achievementPoints`	bigint(20) NOT NULL,
	`thumbnail`			varchar(70) NOT NULL,
	`calcClass`			varchar(2) NOT NULL,
	`faction`			int NOT NULL,
	`totalHonorableKills` int NOT NULL,
	`guild_name`		varchar(50) NOT NULL,
	`lastModified`		bigint(20) NOT NULL,
	PRIMARY KEY(internal_id),
	FOREIGN KEY(guild_name) REFERENCES guild_info(name),
	FOREIGN KEY(internal_id) REFERENCES gMembers_id_name(internal_id),
	FOREIGN KEY(class) REFERENCES playable_class(id),
	FOREIGN KEY(race) REFERENCES races(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;