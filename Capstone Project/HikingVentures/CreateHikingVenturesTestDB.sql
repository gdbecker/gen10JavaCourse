-- Delete database if already exists
drop database if exists HikingVenturesTest;

create database HikingVenturesTest;

use HikingVenturesTest;

create table if not exists Traveler (
	traveler_id int primary key auto_increment,
    first_name varchar(30),
    last_name varchar(30),
    Address varchar(50),
    City varchar(20),
    State varchar(2),
    Zip varchar(5),
    birth_date date,
    photo_link varchar(300),
    photo_file_path varchar(100)
);

create table if not exists Equipment (
	equipment_id int primary key auto_increment,
    `Name` varchar(30),
    `Description` varchar(100),
    photo_link varchar(300),
    photo_file_path varchar(100)
);

create table if not exists Trip (
	trip_id int primary key auto_increment,
    trip_name varchar(50),
    trip_cost_per_traveler double,
    start_date date,
    end_date date
);

create table if not exists Trip_Traveler (
	trip_id int,
    traveler_id int,
    primary key pk_Trip_Traveler (trip_id, traveler_id),
    foreign key fk_Trip_Traveler_Trip (trip_id)
		references Trip(trip_id),
	foreign key fk_Trip_Traveler_Traveler (traveler_id)
		references Traveler(traveler_id)
);

create table if not exists Trip_Equipment (
	trip_id int,
    equipment_id int,
    primary key pk_Trip_Equipment (trip_id, equipment_id),
    foreign key fk_Trip_Equipment_Trip (trip_id)
		references Trip(trip_id),
	foreign key fk_Trip_Equipment_Equipment (equipment_id)
		references Equipment(equipment_id)
);

create table if not exists Location (
	location_id int primary key auto_increment,
    park_name varchar(100),
    nearby_city varchar(20),
    State varchar(2),
    photo_link varchar(300),
    photo_file_path varchar(100)
);

create table if not exists route_type (
	route_type_id int primary key auto_increment,
    `Type` varchar(15),
    `Description` varchar(200)  
);

create table if not exists difficulty_rating (
	difficulty_rating_id int primary key auto_increment,
    `Type` varchar(15)
);

create table if not exists Trail (
	trail_id int primary key auto_increment,
    location_id int,
    foreign key fk_Trail_Location (location_id)
		references Location(location_id),
	trail_name varchar(50),
    difficulty_rating_id int,
    foreign key fk_Trail_DifficultyRating (difficulty_rating_id)
		references difficulty_rating(difficulty_rating_id),
    route_type_id int,
    foreign key fk_Trail_RouteType (route_type_id)
		references route_type(route_type_id),
	Distance double,
    elevation_gain double,
    map_link varchar(1000),
    photo_link varchar(300),
    photo_file_path varchar(100)
);

create table if not exists Trip_Trail (
	trip_id int,
    trail_id int,
    primary key pk_Trip_Trail (trip_id, trail_id),
    foreign key fk_Trip_Trail_Trip (trip_id)
		references Trip(trip_id),
	foreign key pk_Trip_Trail_Trail (trail_id)
		references Trail(trail_id)
);

/* Add in security tables */
create table `user`(
`id` int primary key auto_increment,
`username` varchar(30) not null unique,
`password` varchar(100) not null,
`enabled` boolean not null);

create table `role`(
`id` int primary key auto_increment,
`role` varchar(30) not null
);

create table `user_role`(
`user_id` int not null,
`role_id` int not null,
primary key(`user_id`,`role_id`),
foreign key (`user_id`) references `user`(`id`),
foreign key (`role_id`) references `role`(`id`));