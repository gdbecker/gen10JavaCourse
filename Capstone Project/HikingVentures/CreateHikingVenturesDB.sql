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
    map_link varchar(1000),
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

insert into Traveler(first_name, last_name, Address, City, State, Zip, birth_date, photo_link, photo_file_path) values
('Mark', 'Watney', '2987 Hickory Lane', 'Charlotte', 'NC', '28971', '1981-01-02', 'https://previews.123rf.com/images/avemario/avemario1612/avemario161200023/67563798-hardworking-man-with-inspired-smile-looking-at-screen-of-his-generic-laptop-while-watching-video-onl.jpg', 'Mark-Watney.jpg'),
('Sarah', 'Walker', '67 Fox Avenue', 'Matthews', 'NC', '20862', '1993-08-22', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Yvonne_strahovski_%28cropped%29.jpg/220px-Yvonne_strahovski_%28cropped%29.jpg', 'Sarah-Walker.jpg'),
('Bethany', 'Callahan', '281 Mint Row', 'Gastonia', 'NC', '22881', '1989-12-04', 'https://st.depositphotos.com/3438085/4785/i/950/depositphotos_47859411-stock-photo-generic-expressions.jpg', 'Bethany-Callahan.jpg'),
('Sam', 'Smith', '9752 Hidden Pond St', 'Charlotte', 'NC', '28971', '1978-05-01', 'https://image1.masterfile.com/getImage/NzAwLTAxNTg3MDk3ZW4uMDAwMDAwMDA=AJ-a0a/700-01587097en_Masterfile.jpg', 'Sam-Smith.jpg'),
('Laura', 'Flowers', '798 Canterbury St', 'Raleigh', 'NC', '27602', '1994-10-12', 'https://pbs.twimg.com/profile_images/646496617525288960/kNke3rKq_400x400.jpg', 'Laura-Flowers.jpg'),
('Luke', 'Berkeley', '12 Candlewick Avenue', 'Charlotte', 'NC', '29241', '1996-03-09', 'https://www.vermontgas.com/wp-content/uploads/2016/11/Phil-Schiller-headshot-e1362692403868.jpg', 'Luke-Berkeley.jpg'),
('Kate', 'Cowell', '5327 Trojan Horse Lane', 'Mint Hill', 'NC', '22374', '1990-01-24', 'https://media.istockphoto.com/photos/woman-talking-on-generic-phone-near-river-and-bridge-picture-id839363644?k=6&m=839363644&s=612x612&w=0&h=uhrczlnn7h-o388m1b2FH43MdQ1TjFEZygxTy4PZcNM=', 'Kate-Cowell.jpg'),
('Spencer', 'Burt', '671 Waterfall St', 'Charlotte', 'NC', '28971', '1995-03-29', 'https://image.freepik.com/free-photo/young-man-traveler-with-backpack-relaxing-outdoor_1421-175.jpg', 'Spencer-Burt.jpg');

insert Equipment(`Name`, `Description`, photo_link, photo_file_path) values
('Sleeping Bag', 'Used for sleeping comfortably at night', 'https://images-na.ssl-images-amazon.com/images/I/710ZIY%2BvMLL._AC_SX466_.jpg', 'Sleeping-Bag.jpg'),
('Water Bottle', 'Hydrate!', 'https://media1.s-nbcnews.com/i/newscms/2019_27/1456290/screen_shot_2019-07-05_at_12-09-39_pm_3279718479aabc2067270235b6b037bc.png', 'Water-Bottle.jpg'),
('Backpack', 'Carry everything you need', 'https://i5.walmartimages.com/asr/bf178d0b-f187-44f1-8146-0189f70711da_1.afd2401889eac3d5ea2383f343b928b6.jpeg?odnWidth=450&odnHeight=450&odnBg=ffffff', 'Backpack.jpg'),
('Hiking Boots', 'Protect your feet as you are out there', 'https://www.switchbacktravel.com/sites/default/files/articles%20/Hiking%20Boots%20Round-up%20%28m%29.jpg', 'Hiking-Boots.jpg'),
('Compass', 'Navigate old-school', 'https://travelgearzone.com/wp-content/uploads/2017/01/Suunto-A-10-Field-Compass.png', 'Compass.jpg'),
('First Aid Kit', 'Be prepared for any emergency', 'https://images-na.ssl-images-amazon.com/images/I/91QCnvvW5YL._AC_SL1500_.jpg', 'First-Aid-Kit.jpg'),
('Handheld GPS', 'Naviagte with ease', 'https://www.thegpsstore.com/Assets/ProductImages/Garmin-Etrex-10-Handheld-GPS.jpg', 'Handheld-GPS.jpg'),
('Wool Socks', 'Keep your feet warm and comfortable', 'https://www.rei.com/media/product/604587', 'Wool-Socks.jpeg'),
('Rain Jacket', 'Repel the rain', 'https://shop.geocaching.com/media/catalog/product/cache/8a26566e3285c035850b6b415dcfb529/r/a/rain-jacket.jpg', 'Rain-Jacket.jpg'),
('Hiking Pants', 'Flexible for wearing as shorts or pants', 'https://cdn.shopify.com/s/files/1/1815/0901/products/hiking-pants-waterproof-camping-trekking-fleece-outdoor-hiking-pants-3_2000x.jpg?v=1571611303', 'Hiking-Pants.jpg');

insert into difficulty_rating(`Type`) values
('Easy'),
('Moderate'),
('Hard');

insert into route_type(`Type`, `Description`) values
('Loop', 'Starts and ends at the same location and follows a single trail or multiple trails to form a loop.'),
('Out-and-Back', 'Starts and ends at the same location and follows a single trail or multiple trails to an end point and then returns along the same route.'),
('Point-to-Point', 'Starts and ends in different locations. These routes are often part of a multi-day hiking or backpacking trip or segments of a long distance trail such as the Appalachian Trail or Pacific Crest Trail.');

insert into Location(park_name, nearby_city, State, photo_link, photo_file_path) values
('Great Smoky Mountains National Park', 'Cherokee', 'NC', 'https://koa.com/blog/images/Our-guide-to-the-best-sites-in-and-around-Great-Smoky-Mountains-National-Park..jpg?preset=blogPhoto', 'Great-Smoky-Mountains-National-Park.jpg'),
('Cherokee National Forest', 'Boone', 'NC', 'https://www.fs.usda.gov/Internet/FSE_MEDIA/stelprdb5196738.jpg', 'Cherokee-National-Forest.jpg'),
('Uwharrie National Forest', 'Albemarle', 'NC', 'https://taketothetrail.com/wp-content/uploads/2018/11/uwharrie-forest-fp-1080x628.jpg', 'Uwharrie-National-Forest.jpg'),
('Crowders Mountain State Park', 'Kings Mountain', 'NC', 'https://media2.trover.com/T/5d9cdb926e1f1f5258007874/fixedw_large_2x.jpg', 'Crowders-Mountain-State-Park.jpg'),
('Lake Norman State Park', 'Troutman', 'NC', 'https://www.orthocarolina.com/imagecache/mobile/compReg/the_insiders_guide_to_lake_norman_state_park.jpg', 'Lake-Norman-State-Park.jpg');

insert into Trail(location_id, trail_name, difficulty_rating_id, route_type_id, Distance, elevation_gain, map_link, photo_link, photo_file_path) values
(1, 'Chimney Tops Trail', 3, 2, '3.6', '1289', '<iframe class="alltrails" src="https://www.alltrails.com/widget/trail/us/tennessee/chimney-tops-trail?u=i" width="100%" height="400" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" title="AllTrails: Trail Guides and Maps for Hiking, Camping, and Running"></iframe>', 'https://static.rootsrated.com/image/upload/s--Yg3a_Amy--/t_rr_large_traditional/e73vrzffkhpqevff6ctm.jpg', 'Chimney-Tops-Trail.jpg'),
(1, 'Rainbow Falls Trail', 3, 2, '5.5', '1653', '', 'https://www.chaletvillage.com/wp-content/uploads/2017/03/Beautiful-photo-of-Abrams-Falls-in-the-Smoky-Mountains.jpg', 'Rainbow-Falls-Trail.jpg'),
(2, 'Blue Hole Falls', 1, 1, '0.5', '137', '', 'https://www.shutterbug.com/images/styles/960-wide/public/photo_post/%5Buid%5D/JTM_5575.jpg', 'Blue-Hole-Falls.jpg'),
(2, "Martins Creek Falls", 2, 2, '2.0', '364', '', 'https://waterfallsingeorgia.com/wp-content/uploads/2017/08/Martin-Creek-Falls2.jpg', 'Martins-Creek-Falls.jpg'),
(3, 'Badin Lake Trail', 2, 1, '5.2', '301', '', 'https://media-cdn.tripadvisor.com/media/photo-s/04/7a/2b/46/badin-lake.jpg', 'Badin-Lake-Trail.jpg'),
(3, 'Uwharrie OHV Trail', 2, 1, '9.3', '1197', '', 'https://www.riderplanet-usa.com/atv/trails/photo/51a47dd220194a7a946c4fe4390b7291.jpg', 'Uwharrie-OHV-Trail.jpg'),
(4, 'Pinnacle Trail', 2, 2, '3.7', '767', '', 'https://static.rootsrated.com/image/upload/s--E15vZ4YX--/t_rr_large_traditional/t8zzvrurewkfvdw0tqdh.jpg', 'Pinnacle-Trail.jpg'),
(4, 'Rocktop Trail', 3, 1, '5.9', '1387', '', 'https://hikingthecarolinas.com/crowders/mountain_distance.jpg', 'Rocktop-Trail.jpg'),
(5, 'Lake Norman Lakeshore Trail', 2, 1, '5.7', '511', '', 'https://cdn-assets.alltrails.com/uploads/photo/image/11308508/extra_large_00a2bb43d1fac98e4a94a61fc81d2b90.jpg', 'Lake-Norman-Lakeshore-Trail.jpg'),
(5, 'Alder Trail', 1, 1, '0.9', '49', '', 'https://3.bp.blogspot.com/-C29Ji6ArTAg/V5FhNKn9TmI/AAAAAAAAVa0/3B8ecyIaSSgYXWCjIkUl0HQ2lXTS7PA5wCLcB/s1600/IMG_5833.JPG', 'Alder-Trail.jpg');

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
