create database if not exists MovieCatalogue;

use MovieCatalogue;

create table if not exists Genre (
	GenreID int primary key auto_increment,
    GenreName varchar(30) not null
); 

create table if not exists Director (
	DirectorID int primary key auto_increment,
    FirstName varchar(30) not null,
    LastName varchar (30) not null,
    BirthDate date null
);

create table if not exists Rating (
	RatingID int primary key auto_increment,
    RatingName char(5) not null
);

create table if not exists Actor (
	ActorID int primary key auto_increment,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    BirthDate date null
);

create table if not exists Movie (
	MovieID int primary key auto_increment,
    GenreID int not null,
    foreign key fk_Movie_Genre (GenreID)
		references Genre(GenreID),
    DirectorID int null,
    foreign key fk_Movie_Director (DirectorID)
		references Director(DirectorID),
    RatingID int null,
    foreign key fk_Movie_Rating (RatingID)
		references Rating(RatingID),
    Title varchar(128) not null,
    ReleaseDate date null
);

create table if not exists CastMembers (
	CastMemberID int primary key auto_increment,
    ActorID int not null,
    foreign key fk_CastMembers_Actor (ActorID)
		references Actor(ActorID),
    MovieID int not null,
    foreign key fk_CastMembers_Movie (MovieID)
		references Movie(MovieID),
    Role varchar(50) not null
);