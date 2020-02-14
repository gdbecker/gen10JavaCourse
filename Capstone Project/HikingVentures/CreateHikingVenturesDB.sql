-- Delete database if already exists
drop database if exists HikingVentures;

create database HikingVentures;

use HikingVentures;

create table if not exists Traveler (
	traveler_id int primary key auto_increment,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
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
    `Name` varchar(30) not null,
    `Description` varchar(100) not null,
    photo_link varchar(300),
    photo_file_path varchar(100)
);

create table if not exists Trip (
	trip_id int primary key auto_increment,
    trip_name varchar(50) not null,
    trip_cost_per_traveler double not null,
    start_date date not null,
    end_date date not null
);

create table if not exists Trip_Traveler (
	trip_id int not null,
    traveler_id int not null,
    primary key pk_Trip_Traveler (trip_id, traveler_id),
    foreign key fk_Trip_Traveler_Trip (trip_id)
		references Trip(trip_id),
	foreign key fk_Trip_Traveler_Traveler (traveler_id)
		references Traveler(traveler_id)
);

create table if not exists Trip_Equipment (
	trip_id int not null,
    equipment_id int not null,
    primary key pk_Trip_Equipment (trip_id, equipment_id),
    foreign key fk_Trip_Equipment_Trip (trip_id)
		references Trip(trip_id),
	foreign key fk_Trip_Equipment_Equipment (equipment_id)
		references Equipment(equipment_id)
);

create table if not exists Location (
	location_id int primary key auto_increment,
    park_name varchar(100) not null,
    nearby_city varchar(20) not null,
    State varchar(2) not null,
    photo_link varchar(300),
    photo_file_path varchar(100)
);

create table if not exists route_type (
	route_type_id int primary key auto_increment,
    `Type` varchar(15) not null,
    `Description` varchar(200) not null 
);

create table if not exists difficulty_rating (
	difficulty_rating_id int primary key auto_increment,
    `Type` varchar(15) not null
);

create table if not exists Trail (
	trail_id int primary key auto_increment,
    location_id int not null,
    foreign key fk_Trail_Location (location_id)
		references Location(location_id),
	trail_name varchar(50) not null,
    difficulty_rating_id int not null,
    foreign key fk_Trail_DifficultyRating (difficulty_rating_id)
		references difficulty_rating(difficulty_rating_id),
    route_type_id int not null,
    foreign key fk_Trail_RouteType (route_type_id)
		references route_type(route_type_id),
	Distance double not null,
    elevation_gain double not null,
    map_link varchar(500),
    photo_link varchar(300),
    photo_file_path varchar(100)
);

create table if not exists Trip_Trail (
	trip_id int not null,
    trail_id int not null,
    primary key pk_Trip_Trail (trip_id, trail_id),
    foreign key fk_Trip_Trail_Trip (trip_id)
		references Trip(trip_id),
	foreign key pk_Trip_Trail_Trail (trail_id)
		references Trail(trail_id)
);

insert into Traveler(first_name, last_name, Address, City, State, Zip, birth_date, photo_file_path) values
('Mark', 'Watney', '2987 Hickory Lane', 'Charlotte', 'NC', '28971', '1981-01-02', 'Mark-Watney.jpg'),
('Sarah', 'Walker', '67 Fox Avenue', 'Matthews', 'NC', '20862', '1993-08-22', 'Sarah-Walker.jpg'),
('Bethany', 'Callahan', '281 Mint Row', 'Gastonia', 'NC', '22881', '1989-12-04', 'Bethany-Callahan.jpg'),
('Sam', 'Smith', '9752 Hidden Pond St', 'Charlotte', 'NC', '28971', '1978-05-01', 'Sam-Smith.jpg'),
('Laura', 'Flowers', '798 Canterbury St', 'Raleigh', 'NC', '27602', '1994-10-12', 'Laura-Flowers.jpg'),
('Luke', 'Berkeley', '12 Candlewick Avenue', 'Charlotte', 'NC', '29241', '1996-03-09', 'Luke-Berkeley.jpg'),
('Kate', 'Cowell', '5327 Trojan Horse Lane', 'Mint Hill', 'NC', '22374', '1990-01-24', 'Kate-Cowell.jpg'),
('Spencer', 'Burt', '671 Waterfall St', 'Charlotte', 'NC', '28971', '1995-03-29', 'Spencer-Burt.jpg');

insert Equipment(`Name`, `Description`, photo_file_path) values
('Sleeping Bag', 'Used for sleeping comfortably at night', 'Sleeping-Bag.jpg'),
('Water Bottle', 'Hydrate!', 'Water-Bottle.jpg'),
('Backpack', 'Carry everything you need', 'Backpack.jpg'),
('Hiking Boots', 'Protect your feet as you are out there', 'Hiking-Boots.jpg'),
('Compass', 'Navigate old-school', 'Compass.jpg'),
('First Aid Kit', 'Be prepared for any emergency', 'First-Aid-Kit.jpg'),
('Handheld GPS', 'Naviagte with ease', 'Handheld-GPS.jpg'),
('Wool Socks', 'Keep your feet warm and comfortable', 'Wool-Socks.jpeg'),
('Rain Jacket', 'Repel the rain', 'Rain-Jacket.jpg'),
('Hiking Pants', 'Flexible for wearing as shorts or pants', 'Hiking-Pants.jpg');

insert into difficulty_rating(`Type`) values
('Easy'),
('Moderate'),
('Hard');

insert into route_type(`Type`, `Description`) values
('Loop', 'Starts and ends at the same location and follows a single trail or multiple trails to form a loop.'),
('Out-and-Back', 'Starts and ends at the same location and follows a single trail or multiple trails to an end point and then returns along the same route.'),
('Point-to-Point', 'Starts and ends in different locations. These routes are often part of a multi-day hiking or backpacking trip or segments of a long distance trail such as the Appalachian Trail or Pacific Crest Trail.');

insert into Location(park_name, nearby_city, State, photo_file_path) values
('Great Smoky Mountains National Park', 'Cherokee', 'NC', 'Great-Smoky-Mountains-National-Park.jpg'),
('Cherokee National Forest', 'Boone', 'NC', 'Cherokee-National-Forest.jpg'),
('Uwharrie National Forest', 'Albemarle', 'NC', 'Uwharrie-National-Forest.jpg'),
('Crowders Mountain State Park', 'Kings Mountain', 'NC', 'Crowders-Mountain-State-Park.jpg'),
('Lake Norman State Park', 'Troutman', 'NC', 'Lake-Norman-State-Park.jpg');

insert into Trail(location_id, trail_name, difficulty_rating_id, route_type_id, Distance, elevation_gain, map_link, photo_file_path) values
(1, 'Chimney Tops Trail', 3, 2, '3.6', '1289', 'https://www.alltrails.com/trail/us/tennessee/chimney-tops-trail/print?title=Chimney%2BTops%2BTrail&at_map_id=22388256&map_center_lat=35.629762033890714&map_center_lon=-83.47392797470093&map_type=alltrailsOutdoorsV2&timestamp=1581274271&paper_size=letter&paper_orientation=portrait&grid_format=decimal&map_zoom=15&map_datum=wgs84', 'Chimney-Tops-Trail.jpg'),
(1, 'Rainbow Falls Trail', 3, 2, '5.5', '1653', 'https://www.alltrails.com/trail/us/tennessee/rainbow-falls-trail/print', 'Rainbow-Falls-Trail.jpg'),
(2, 'Blue Hole Falls', 1, 1, '0.5', '137', 'https://www.alltrails.com/trail/us/tennessee/blue-hole-falls/print?map_center_lat=36.43250132616073&map_center_lon=-82.07253999999999&map_zoom=17&map_type=alltrailsOutdoorsV2', 'Blue-Hole-Falls.jpg'),
(2, "Martin's Creek Falls", 2, 2, '2.0', '364', 'https://www.alltrails.com/trail/us/tennessee/martins-creek-falls/print?map_center_lat=36.12035678978906&map_center_lon=-82.394835&map_zoom=15&map_type=alltrailsOutdoorsV2', 'Martins-Creek-Falls.jpg'),
(3, 'Badin Lake Trail', 2, 1, '5.2', '301', 'https://www.alltrails.com/trail/us/north-carolina/badin-lake-trail/print?map_center_lat=35.451266467612555&map_center_lon=-80.07380962371826&map_zoom=14&map_type=alltrailsOutdoorsV2', 'Badin-Lake-Trail.jpg'),
(3, 'Uwharrie OHV Trail', 2, 1, '9.3', '1197', 'https://www.alltrails.com/trail/us/north-carolina/uwharrie-jeep-trail/print?map_center_lat=35.41472589791322&map_center_lon=-80.05282500000001&map_zoom=13&map_type=alltrailsOutdoorsV2', 'Uwharrie-OHV-Trail.jpg'),
(4, 'Pinnacle Trail', 2, 2, '3.7', '767', 'https://www.alltrails.com/trail/us/north-carolina/pinnacle-trail--2/print?map_center_lat=35.21215578931309&map_center_lon=-81.303035&map_zoom=15&map_type=alltrailsOutdoorsV2', 'Pinnacle-Trail.jpg'),
(4, 'Rocktop Trail', 3, 1, '5.9', '1387', 'https://www.alltrails.com/trail/us/north-carolina/rocktop-trail/print?map_center_lat=35.22784763526057&map_center_lon=-81.2817907333374&map_zoom=14&map_type=alltrailsOutdoorsV2', 'Rocktop-Trail.jpg'),
(5, 'Lake Norman Lakeshore Trail', 2, 1, '5.7', '511', 'https://www.alltrails.com/trail/us/north-carolina/lake-norman-lakeshore-trail--2/print', 'Lake-Norman-Lakeshore-Trail.jpg'),
(5, 'Alder Trail', 1, 1, '0.9', '49', 'https://www.alltrails.com/trail/us/north-carolina/alder-trail-loop-hike/print', 'Alder-Trail.jpg');

insert into Trip(trip_name, trip_cost_per_traveler, start_date, end_date) values
('Bro Trip 2020', '50.50', '2020-04-02', '2020-04-04'),
('Personal Day Trip', '10', '2020-05-06', '2020-05-06'),
('Weekend Fun', '35.00', '2020-06-10', '2020-06-12'),
('Exploration Day', '20', '2020-05-12', '2020-05-12');

insert Trip_Traveler(trip_id, traveler_id) values
(1, 1),
(1, 4),
(1, 6),
(1, 8),
(2, 2),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(4, 3),
(4, 5),
(4, 7);

insert Trip_Equipment(trip_id, equipment_id) values
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 6),
(1, 7),
(1, 8),
(2, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 6),
(3, 7),
(3, 8),
(3, 10),
(4, 2),
(4, 3),
(4, 4),
(4, 7),
(4, 8),
(4, 9);

insert into Trip_Trail(trip_id, trail_id) values
(1, 3),
(2, 5),
(3, 9),
(4, 2);

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

insert into `user`(`id`,`username`,`password`,`enabled`)
    values(1,"admin", "password", true);

insert into `role`(`id`,`role`)
    values(1,"ROLE_ADMIN");
    
insert into `user_role`(`user_id`,`role_id`)
    values(1,1);
