-- Delete database if already exists
drop database if exists HikingVentures;

create database HikingVentures;

use HikingVentures;

create table if not exists Traveler (
	TravelerID int primary key auto_increment,
    FristName varchar(30) not null,
    LastName varchar(30) not null,
    Address varchar(50),
    City varchar(20),
    State varchar(2),
    Zip varchar(5),
    BirthDate date
);

create table if not exists Equipment (
	EquipmentID int primary key auto_increment,
    `Name` varchar(30) not null,
    `Description` varchar(50) not null,
    PhotoLink varchar(50) not null
);

create table if not exists Trip (
	TripID int primary key auto_increment,
    TripName varchar(50) not null,
    TripCostPerTraveler double not null,
    StartDate date not null,
    EndDate date not null
);

create table if not exists Trip_Traveler (
	TripID int not null,
    TravelerID int not null,
    primary key pk_Trip_Traveler (TripID, TravelerID),
    foreign key fk_Trip_Traveler_Trip (TripID)
		references Trip(TripID),
	foreign key fk_Trip_Traveler_Traveler (TravelerID)
		references Traveler(TravelerID)
);

create table if not exists Trip_Equipment (
	TripID int not null,
    EquipmentID int not null,
    primary key pk_Trip_Equipment (TripID, EquipmentID),
    foreign key fk_Trip_Equipment_Trip (TripID)
		references Trip(TripID),
	foreign key fk_Trip_Equipment_Equipment (EquipmentID)
		references Equipment(EquipmentID)
);

create table if not exists Location (
	LocationID int primary key auto_increment,
    ParkName varchar(30) not null,
    NearbyCity varchar(20) not null,
    State varchar(2) not null
);

create table if not exists RouteType (
	RouteTypeID int primary key auto_increment,
    `Type` varchar(15) not null,
    Description varchar(100) not null 
);

create table if not exists Trail (
	TrailID int primary key auto_increment,
    LocationID int not null,
    foreign key fk_Trail_Location (LocationID)
		references Location(LocationID),
	TrailName varchar(20) not null,
    DifficultyRating varchar(15) not null,
    RouteTypeID int not null,
    foreign key fk_Trail_RouteType (RouteTypeID)
		references RouteType(RouteTypeID),
	Distance double not null,
    ElevationGain double not null,
    MapLink varchar(200) not null
);

create table if not exists Trip_Trail (
	TripID int not null,
    TrailID int not null,
    primary key pk_Trip_Trail (TripID, TrailID),
    foreign key fk_Trip_Trail_Trip (TripID)
		references Trip(TripID),
	foreign key pk_Trip_Trail_Trail (TrailID)
		references Trail(TrailID)
);