USE PersonalTrainer;

-- Select all rows and columns from the Exercise table.
-- 64 rows
--------------------
SELECT *
FROM Exercise;
-- [done]

-- Select all rows and columns from the Client table.
-- 500 rows
--------------------
SELECT *
FROM Client;
-- [done]

-- Select all columns from Client where the City is Metairie.
-- 29 rows
--------------------
SELECT *
FROM Client
WHERE City = 'Metairie';
-- [done]

-- Is there a Client with the ClientId '818u7faf-7b4b-48a2-bf12-7a26c92de20c'?
-- nope
--------------------
SELECT *
FROM Client
WHERE ClientID = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';
-- [done]

-- How many rows in the Goal table?
-- 17 rows
--------------------
SELECT *
FROM Goal;
-- [done]

-- Select Name and LevelId from the Workout table.
-- 26 rows
--------------------
SELECT 
	Name,
    LevelID
FROM Workout;
-- [done]

-- Select Name, LevelId, and Notes from Workout where LevelId is 2.
-- 11 rows
--------------------
SELECT
	Name,
    LevelId,
    Notes
FROM Workout
WHERE LevelId = 2;
-- [done]

-- Select FirstName, LastName, and City from Client 
-- where City is Metairie, Kenner, or Gretna.
-- 77 rows
--------------------
SELECT 
	FirstName,
    LastName,
    City
FROM Client
WHERE City = 'Metairie' OR City = 'Kenner' OR City = 'Gretna';
-- [done]

-- Select FirstName, LastName, and BirthDate from Client
-- for Clients born in the 1980s.
-- 72 rows
--------------------
SELECT
	FirstName,
    LastName,
    BirthDate
FROM Client
WHERE BirthDate LIKE '1980-__-__' 
	OR BirthDate LIKE '1981-__-__'
    OR BirthDate LIKE '1982-__-__'
    OR BirthDate LIKE '1983-__-__'
    OR BirthDate LIKE '1984-__-__'
    OR BirthDate LIKE '1985-__-__'
    OR BirthDate LIKE '1986-__-__'
    OR BirthDate LIKE '1987-__-__'
    OR BirthDate LIKE '1988-__-__'
    OR BirthDate LIKE '1989-__-__';
-- [done]

-- Write the query above in a different way. 
-- If you used BETWEEN, you can't use it again.
-- If you didn't use BETWEEN, use it!
-- Still 72 rows
--------------------
SELECT
	FirstName,
    LastName,
    BirthDate
FROM Client
WHERE BirthDate BETWEEN '1980-01-01' AND '1989-12-31';
-- [done]

-- How many rows in the Login table have a .gov EmailAddress?
-- 17 rows
--------------------
SELECT *
FROM Login
WHERE EmailAddress LIKE '%.gov';
-- [done]

-- How many Logins do NOT have a .com EmailAddress?
-- 122 rows
--------------------
SELECT *
FROM Login
WHERE EmailAddress NOT LIKE '%.com';
-- [done]

-- Select first and last name of Clients without a BirthDate.
-- 37 rows
--------------------
SELECT 
	FirstName,
    LastName
FROM Client
WHERE BirthDate IS NULL;
-- [done]

-- Select the Name of each ExerciseCategory that has a parent.
-- (ParentCategoryId value is not null)
-- 12 rows
--------------------
SELECT Name
FROM ExerciseCategory
WHERE ParentCategoryID IS NOT NULL;
-- [done]

-- Select Name and Notes of each level 3 Workout that
-- contains the word 'you' in its Notes.
-- 4 rows
--------------------
SELECT
	Name,
    Notes
FROM Workout
WHERE LevelId = 3 
AND Notes LIKE '%you%';
-- [done]

-- Select FirstName, LastName, City from Clients who have a LastName
-- that starts with L,M, or N and who live in LaPlace.
-- 5 rows
--------------------
SELECT
	FirstName,
    LastName,
    City
FROM Client
WHERE (LastName LIKE 'L%' 
	OR LastName LIKE 'M%' 
	OR LastName LIKE 'N%') 
AND City = 'LaPlace';
-- [done]

-- Select InvoiceId, Description, Price, Quantity, ServiceDate 
-- and the line item total, a calculated value, where the line item total
-- is between 15 and 25 dollars.
-- 667 rows
--------------------
SELECT 
	InvoiceId,
	Description,
    Price,
    Quantity,
    ServiceDate
FROM InvoiceLineItem 
WHERE (Price * Quantity >= 15 AND Price * Quantity <= 25);
-- [done]

-- Does the Client, Estrella Bazely, have a Login? 
-- If so, what is her email address?
-- This requires two queries:
-- First, select a Client record for Estrella Bazely. Does it exist?
-- Second, if it does, select a Login record that matches its ClientId.
--------------------
SELECT ClientId
FROM Client
WHERE FirstName = 'Estrella' AND LastName = 'Bazely';
-- Answer: YES she has a login

SELECT *
FROM Login
WHERE ClientId =
(SELECT ClientId
FROM Client
WHERE FirstName = 'Estrella' AND LastName = 'Bazely');
-- [done] Pulled up the login record for her

-- What are the Goals of the Workout with the Name 'This Is Parkour'?
-- You need three queries!:
-- 1. Select the WorkoutId from Workout where Name equals 'This Is Parkour'.
-- 2. Select GoalId from WorkoutGoal where the WorkoutId matches the WorkoutId
--    from your first query.
-- 3. Select everything from Goal where the GoalId is one of the GoalIds from your
--    second query.
-- 1 row, 3 rows, 3 rows
--------------------
SELECT WorkoutId
FROM Workout
WHERE Name = 'This Is Parkour';
-- Answer: WorkoutId = 12

SELECT GoalId
FROM WorkoutGoal
WHERE WorkoutId = 12;
-- Answer: 3, 8, 15

SELECT *
FROM Goal
WHERE GoalId = 3 OR GoalId = 8 OR GoalId = 12;
-- [done] Pulled up required data
