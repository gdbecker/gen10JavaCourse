-- Delete database if already exists
drop database if exists HotelReservation;

create database HotelReservation;

use HotelReservation;

create table if not exists ADA (
	ADAID int primary key auto_increment,
    ADAOption varchar(5) not null
);

create table if not exists Amenities (
	AmenitiesID int primary key auto_increment,
    AmenitiesDescription varchar(40) not null
);

create table if not exists RoomType (
	RoomTypeID int primary key auto_increment,
    RoomTypeName varchar(10) not null
);

create table if not exists Room (
	RoomNum int not null,
	RoomID int primary key auto_increment,
    RoomTypeID int not null,
    foreign key fk_Room_RoomType (RoomTypeID)
		references RoomType(RoomTypeID),
	AmenitiesID int not null,
    foreign key fk_Room_Amenities (AmenitiesID)
		references Amenities(AmenitiesID),
	ADAID int not null,
    foreign key fk_Room_ADA (ADAID)
		references ADA(ADAID),
	StandardOccupancy int not null,
    MaximumOccupancy int not null,
    PriceBase double not null,
    PriceExtraGuest double null
);

create table if not exists Guest (
	GuestID int primary key auto_increment,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    Address varchar(100) null,
    City varchar(30) null,
    State varchar(2) null,
    Zip varchar(5) null,
    Phone varchar(14)
);

create table if not exists Reservation (
	ReservationID int primary key auto_increment,
    RoomID int not null,
    foreign key fk_Reservation_Room (RoomID)
		references Room(RoomID),
	GuestID int not null,
    foreign key fk_Reservation_Guest (GuestID)
		references Guest(GuestID),
	NumAdults int not null,
    NumChildren int not null,
    StartDate date not null,
    EndDate date not null,
    TotalRoomCost double not null
);

create table if not exists ReservationRoom (
	ReservationID int not null,
    RoomID int not null,
    primary key pk_ReservationRoom (ReservationID, RoomID),
    foreign key fk_ReservationRoom_Reservation (ReservationID)
		references Reservation(ReservationID),
	foreign key fk_ReservationRoom_Room (RoomID)
		references Room(RoomID)
);