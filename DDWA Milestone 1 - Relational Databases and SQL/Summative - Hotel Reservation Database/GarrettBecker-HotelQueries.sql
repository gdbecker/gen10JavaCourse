use HotelReservation;

-- 1
-- Write a query that returns a list of reservations that end in July 2023, 
-- including the name of the guest, the room number(s), and the reservation dates.
select
	Guest.FirstName,
    Guest.LastName,
    Room.RoomNum,
    Reservation.StartDate,
    Reservation.EndDate
from Guest
	join Reservation on Guest.GuestID = Reservation.GuestID
	join ReservationRoom on Reservation.ReservationID = ReservationRoom.ReservationID
    join Room on ReservationRoom.RoomID = Room.RoomID
where Reservation.EndDate between '2023-07-01' and '2023-07-31';
-- Results
-- 4 rows

----------

-- 2
-- Write a query that returns a list of all reservations for rooms with a jacuzzi, 
-- displaying the guest's name, the room number, and the dates of the reservation.
select
	Guest.FirstName,
    Guest.LastName,
    Room.RoomNum,
    Reservation.StartDate,
    Reservation.EndDate
from Guest
	join Reservation on Guest.GuestID = Reservation.GuestID
	join ReservationRoom on Reservation.ReservationID = ReservationRoom.ReservationID
    join Room on ReservationRoom.RoomID = Room.RoomID
    join Amenities on Room.AmenitiesID = Amenities.AmenitiesID
where Amenities.AmenitiesID = 3 or Amenities.AmenitiesID = 4;
-- Results
-- 11 rows

----------

-- 3
-- Write a query that returns all the rooms reserved for a specific guest, 
-- including the guest's name, the room(s) reserved, the starting date of the reservation, 
-- and how many people were included in the reservation. 
-- (Choose a guest's name from the existing data.)
select
	Guest.FirstName,
    Guest.LastName,
    Room.RoomNum,
    Reservation.StartDate,
    Reservation.EndDate,
    Reservation.NumAdults + Reservation.NumChildren NumGuests
from Guest
	left outer join Reservation on Guest.GuestID = Reservation.GuestID
	left outer join ReservationRoom on Reservation.ReservationID = ReservationRoom.ReservationID
    left outer join Room on ReservationRoom.RoomID = Room.RoomID
where Guest.FirstName = 'Mack';
-- Results
-- For Guest.FirstName = 'Mack' resulted in 4 rows

----------
-- 4
-- Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
-- The results should include all rooms, whether or not there is a reservation associated with the room.
select 
	Room.RoomNum,
    Reservation.ReservationID,
    Reservation.TotalRoomCost
from Room
left outer join ReservationRoom on Room.RoomID = ReservationRoom.RoomID
left outer join Reservation on ReservationRoom.ReservationID = Reservation.ReservationID;
-- Results
-- 26 rows

----------
-- 5
-- Write a query that returns all the rooms accommodating at least three guests and that are reserved 
-- on any date in April 2023.
select
	Room.RoomNum
from Room
left outer join ReservationRoom on Room.RoomID = ReservationRoom.RoomID
left outer join Reservation on ReservationRoom.ReservationID = Reservation.ReservationID
where 
	Reservation.NumAdults + Reservation.NumChildren >= 3 and
    (Reservation.StartDate between '2023-04-01' and '2023-04-30' or Reservation.EndDate between '2023-04-01' and '2023-04-30');
-- Results
-- 0 rows

----------

-- 6
-- Write a query that returns a list of all guest names and the number of reservations per guest, 
-- sorted starting with the guest with the most reservations and then by the guest's last name.
select
	Guest.LastName,
    Guest.FirstName,
    count(Reservation.ReservationID) NumReservations
from Guest
left outer join Reservation on Guest.GuestID = Reservation.GuestID
group by Guest.LastName, Guest.FirstName
order by count(Reservation.ReservationID) desc, Guest.LastName asc;
-- Results
-- 11 rows

----------

-- 7
-- Write a query that displays the name, address, and phone number of a guest based on their phone number. 
-- (Choose a phone number from the existing data.)
select 
	FirstName,
    LastName,
    Address,
    Phone
from Guest
where Phone = '(834) 727-1001';
-- Results
-- For phone # (834) 727-1001 resulted in 1 row for Vise