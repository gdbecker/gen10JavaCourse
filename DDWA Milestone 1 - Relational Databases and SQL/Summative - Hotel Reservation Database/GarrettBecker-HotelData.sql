use HotelReservation;

-- Add all data into HotelReservation
insert into ADA (ADAOption) values
	('Yes'),
    ('No');
    
insert into Amenities (AmenitiesDescription) values
	('Refrigerator'),
    ('Microwave, Refrigerator'),
    ('Microwave, Jacuzzi'),
    ('Microwave, Refrigerator, Jacuzzi'),
    ('Microwave, Refrigerator, Oven');
    
insert into RoomType (RoomTypeName) values
	('Single'),
    ('Double'),
    ('Suite');
    
insert into Room (RoomNum, RoomTypeID, AmenitiesID, ADAID, StandardOccupancy, MaximumOccupancy, PriceBase, PriceExtraGuest) values
	(201, 2, 3, 2, 2, 4, 199.99, 10),
    (202, 2, 1, 1, 2, 4, 174.99, 10),
    (203, 2, 3, 2, 2, 4, 199.99, 10),
    (204, 2, 1, 1, 2, 4, 174.99, 10),
    (205, 1, 4, 2, 2, 2, 174.99, null),
    (206, 1, 2, 1, 2, 2, 149.99, null),
    (207, 1, 4, 2, 2, 2, 174.99, null),
    (208, 1, 2, 1, 2, 2, 149.99, null),
    (301, 2, 3, 2, 2, 4, 199.99, 10),
    (302, 2, 1, 1, 2, 4, 174.99, 10),
    (303, 2, 3, 2, 2, 4, 199.99, 10),
    (304, 2, 1, 1, 2, 4, 174.99, 10),
    (305, 1, 4, 2, 2, 2, 174.99, null),
    (306, 1, 2, 1, 2, 2, 149.99, null),
    (307, 1, 4, 2, 2, 2, 174.99, null),
    (308, 1, 2, 1, 2, 2, 149.99, null),
    (401, 3, 5, 1, 3, 8, 399.99, 20),
    (402, 3, 5, 1, 3, 8, 399.99, 20);
    
insert into Guest (FirstName, LastName, Address, City, State, Zip, Phone) values
	('Garrett','Becker','5609 Appalachian Trail', 'Boone','NC', '29019','(919) 098-2341'),
    ('Mack','Simmer','379 Old Shore Street', 'Council Bluffs','IA', '51501','(201) 553-0508'),
    ('Bettyann','Seery','750 Wintergreen Dr.', 'Wasilla','AK', '99654','(487) 277-9632'),
    ('Duane','Cullison','9662 Foxrun Lane', 'Harlingen','TX', '78552','(308) 494-0198'),
    ('Karie','Yang','9378 W. Augusta Ave.', 'West Deptford','NJ', '08096','(214) 730-0298'),
    ('Aurore','Lipton','762 Wild Rose Street', 'Saginaw','MI', '48601','(377) 507-0974'),
    ('Zachery','Luechtefeld','7 Poplar Dr.', 'Arvada','CO', '80003','(814) 485-2615'),
    ('Jeremiah','Pendergrass','70 Oakwood St.', 'Zion','IL', '60099','(279) 491-0960'),
	('Walter','Holaway','7556 Arrowhead St.', 'Cumberland','RI', '02864','(446) 396-6785'),
    ('Wilfred','Vise','77 West Surrey Street', 'Oswego','NY', '13126','(834) 727-1001'),
    ('Maritza','Tilton','939 Linda Rd.', 'Burke','VA', '22015','(446) 351-6860'),
	('Joleen','Tison','87 Queen St.', 'Drexel Hill','PA', '19026','(231) 893-2755');

insert into Reservation (RoomID, GuestID, NumAdults, NumChildren, StartDate, EndDate, TotalRoomCost) values
	(16, 2, 1, 0, '2023-02-02', '2023-02-04', 299.98),
    (3, 3, 2, 1, '2023-02-05', '2023-02-10', 999.95),
    (13, 4, 2, 0, '2023-02-22', '2023-02-24', 349.98),
    (1, 5, 2, 2, '2023-03-06', '2023-03-07', 199.99),
    (15, 1, 1, 1, '2023-03-17', '2023-03-20', 524.97),
    (10, 6, 3, 0, '2023-03-18', '2023-03-23', 924.95),
    (2, 7, 2, 2, '2023-03-29', '2023-03-31', 349.98),
    (12, 8, 2, 0, '2023-03-31', '2023-04-05', 874.95),
    (9, 9, 1, 0, '2023-04-09', '2023-04-13', 799.96),
    (7, 10, 1, 1, '2023-04-23', '2023-04-24', 174.99),
    (17, 11, 2, 4, '2023-05-30', '2023-06-02', 1199.97),
    (6, 12, 2, 0, '2023-06-10', '2023-06-14', 599.96),
    (8, 12, 1, 0, '2023-06-10', '2023-06-14', 599.96),
    (12, 6, 3, 0, '2023-06-17', '2023-06-18', 184.99),
    (5, 1, 2, 0, '2023-06-28', '2023-07-02', 699.96),
    (4, 9, 3, 1, '2023-07-13', '2023-07-14', 184.99),
    (17, 10, 4, 2, '2023-07-18', '2023-07-21', 1259.97),
    (11, 3, 2, 1, '2023-07-28', '2023-07-29', 199.99),
    (13, 3, 1, 0, '2023-08-30', '2023-09-01', 349.98),
    (8, 2, 2, 0, '2023-09-16', '2023-09-17', 149.99),
    (3, 5, 2, 2, '2023-09-13', '2023-09-15', 399.98),
    (17, 4, 2, 2, '2023-11-22', '2023-11-25', 1199.97),
    (6, 2, 2, 0, '2023-11-22', '2023-11-25', 449.97),
    (9, 2, 2, 2, '2023-11-22', '2023-11-25', 599.97),
    (10, 11, 2, 0, '2023-12-24', '2023-12-28', 699.96);
    
insert into ReservationRoom (ReservationID, RoomID) values
	(1, 16),
    (2, 3),
    (3, 13),
    (4, 1),
    (5, 15),
    (6, 10),
    (7, 2),
    (8, 12),
    (9, 9),
    (10, 7),
    (11, 17),
    (12, 6),
    (13, 8),
    (14, 12),
    (15, 5),
    (16, 4),
    (17, 17),
    (18, 11),
    (19, 13),
    (20, 8),
    (21, 3),
    (22, 17),
    (23, 6),
    (24, 9),
    (25, 10);
    
-- Delete Jeremiah Pendergrass from database
delete ReservationRoom
from ReservationRoom
left outer join Reservation on ReservationRoom.RoomID = Reservation.RoomID
left outer join Guest on Reservation.GuestID = Guest.GuestID
where Guest.GuestID = 8;

delete Reservation
from Reservation
left outer join Guest on Reservation.GuestID = Guest.GuestID
where Guest.GuestID = 8;

delete Guest
from Guest
where GuestID = 8;