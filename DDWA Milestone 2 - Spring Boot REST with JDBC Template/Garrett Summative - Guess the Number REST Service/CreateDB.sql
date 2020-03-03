-- Delete database if already exists
drop database if exists GuessTheNumberGame;

create database GuessTheNumberGame;

use GuessTheNumberGame;

create table if not exists Game (
	GameID int primary key auto_increment,
    Answer varchar(4) not null,
    `Status` varchar(15) not null
);

create table if not exists Round (
	RoundID int primary key auto_increment,
    GameID int not null,
    foreign key fk_Round_Game (GameID)
		references Game(GameID),
	GuessValue varchar(4) not null,
	GuessTime varchar(30) not null,
    GuessResult varchar(15) not null
);