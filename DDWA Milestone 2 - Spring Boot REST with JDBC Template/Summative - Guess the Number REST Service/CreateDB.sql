-- Delete database if already exists
drop database if exists GuessTheNumberGame;

create database GuessTheNumberGame;

use GuessTheNumberGame;

create table if not exists Game (
	GameID int primary key auto_increment,
    Answer int not null,
    Status varchar(10) not null
);

create table if not exists Round (
	RoundID int primary key auto_increment,
    GameID int not null,
    foreign key fk_Round_Game (GameID)
		references Game(GameID),
	GuessValue int not null,
	GuessTime varchar(30) not null,
    GuessResult varchar(10) not null
);