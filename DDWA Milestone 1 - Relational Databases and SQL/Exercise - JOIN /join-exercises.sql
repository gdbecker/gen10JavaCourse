USE PersonalTrainer;

-- Select all columns from ExerciseCategory and Exercise.
-- The tables should be joined on ExerciseCategoryId.
-- This query returns all Exercises and their associated ExerciseCategory.
-- 64 rows
--------------------
select *
from ExerciseCategory
inner join Exercise on ExerciseCategory.ExerciseCategoryId = Exercise.ExerciseCategoryId;
-- [done]
    
-- Select ExerciseCategory.Name and Exercise.Name
-- where the ExerciseCategory does not have a ParentCategoryId (it is null).
-- Again, join the tables on their shared key (ExerciseCategoryId).
-- 9 rows
--------------------
select
	ExerciseCategory.Name,
    Exercise.Name
from ExerciseCategory
inner join Exercise on ExerciseCategory.ExerciseCategoryId = Exercise.ExerciseCategoryId
where ExerciseCategory.ParentCategoryId is null;
-- [done]

-- The query above is a little confusing. At first glance, it's hard to tell
-- which Name belongs to ExerciseCategory and which belongs to Exercise.
-- Rewrite the query using an aliases. 
-- Alias ExerciseCategory.Name as 'CategoryName'.
-- Alias Exercise.Name as 'ExerciseName'.
-- 9 rows
--------------------
select
	ExerciseCategory.Name CategoryName,
    Exercise.Name ExerciseName
from ExerciseCategory
inner join Exercise on ExerciseCategory.ExerciseCategoryId = Exercise.ExerciseCategoryId
where ExerciseCategory.ParentCategoryId is null;
-- [done]

-- Select FirstName, LastName, and BirthDate from Client
-- and EmailAddress from Login 
-- where Client.BirthDate is in the 1990s.
-- Join the tables by their key relationship. 
-- What is the primary-foreign key relationship?
-- 35 rows
--------------------
select
	Client.FirstName,
    Client.LastName,
    Client.BirthDate,
    Login.EmailAddress
from Client
inner join Login on Client.ClientId = Login.ClientId
where Client.BirthDate between '1990-01-01' and '1999-12-31';
-- [done]

-- Select Workout.Name, Client.FirstName, and Client.LastName
-- for Clients with LastNames starting with 'C'?
-- How are Clients and Workouts related?
-- 25 rows
--------------------
select
	Workout.Name,
    Client.FirstName,
    Client.LastName
from Client
left outer join ClientWorkout on Client.ClientId = ClientWorkout.ClientId
left outer join Workout on ClientWorkout.WorkoutId = Workout.WorkoutId
where Client.LastName like 'C%' and Workout.Name is not null;
-- [done]

-- Select Names from Workouts and their Goals.
-- This is a many-to-many relationship with a bridge table.
-- Use aliases appropriately to avoid ambiguous columns in the result.
--------------------
select
	Workout.Name WorkoutName,
    Goal.Name WorkoutGoal
from Goal
left outer join WorkoutGoal on Goal.GoalId = WorkoutGoal.GoalId
left outer join Workout on WorkoutGoal.WorkoutId = Workout.WorkoutId;
-- [done]

-- Select FirstName and LastName from Client.
-- Select ClientId and EmailAddress from Login.
-- Join the tables, but make Login optional.
-- 500 rows
--------------------
select
	Client.FirstName,
    Client.LastName,
    Login.ClientId,
    Login.EmailAddress
from Login
right outer join Client on Login.ClientId = Client.ClientId;
-- [done]

-- Using the query above as a foundation, select Clients
-- who do _not_ have a Login.
-- 200 rows
--------------------
select
	Client.FirstName,
    Client.LastName,
    Login.ClientId,
    Login.EmailAddress
from Login
right outer join Client on Login.ClientId = Client.ClientId
where Login.ClientId is null;
-- [done]

-- Does the Client, Romeo Seaward, have a Login?
-- Decide using a single query.
-- nope :(
--------------------
select
	Client.FirstName,
    Client.LastName,
    Login.EmailAddress
from Login
right outer join Client on Login.ClientId = Client.ClientId
where Client.FirstName = 'Romeo' and Client.LastName = 'Seaward';

-- Select ExerciseCategory.Name and its parent ExerciseCategory's Name.
-- This requires a self-join.
-- 12 rows
--------------------
select 
	child.`Name` ChildName,
    parent.`Name` ParentName
from ExerciseCategory parent
inner join ExerciseCategory child on parent.ExerciseCategoryId = child.ParentCategoryId;
-- [done]
    
-- Rewrite the query above so that every ExerciseCategory.Name is
-- included, even if it doesn't have a parent.
-- 16 rows
--------------------
select 
	child.`Name` ChildName,
    parent.`Name` ParentName
from ExerciseCategory parent
right outer join ExerciseCategory child on parent.ExerciseCategoryId = child.ParentCategoryId;
-- [done]
    
-- Are there Clients who are not signed up for a Workout?
-- 50 rows
--------------------
select
	Client.FirstName,
    Client.LastName,
    ClientWorkout.WorkoutId
from Client
left outer join ClientWorkout on Client.ClientId = ClientWorkout.ClientId
where ClientWorkout.WorkoutId is null;

-- Which Beginner-Level Workouts satisfy at least one of Shell Creane's Goals?
-- Goals are associated to Clients through ClientGoal.
-- Goals are associated to Workouts through WorkoutGoal.
-- 6 rows, 4 unique rows
--------------------
select 
	Workout.Name
from Workout
left outer join WorkoutGoal on Workout.WorkoutId = WorkoutGoal.WorkoutId
left outer join Goal on WorkoutGoal.GoalId = Goal.GoalId
left outer join ClientGoal on Goal.GoalId = ClientGoal.GoalId
left outer join Client on ClientGoal.ClientId = Client.ClientId
left outer join Level on Workout.LevelId = Level.LevelId
where Client.FirstName = 'Shell' and Client.LastName = 'Creane' and Level.Name = 'Beginner';
-- [done]

-- Select all Workouts. 
-- Join to the Goal, 'Core Strength', but make it optional.
-- You may have to look up the GoalId before writing the main query.
-- If you filter on Goal.Name in a WHERE clause, Workouts will be excluded.
-- Why?
-- 26 Workouts, 3 Goals
--------------------
select
	Workout.Name,
    Goal.Name
from Workout
left outer join WorkoutGoal on Workout.WorkoutId = WorkoutGoal.WorkoutId and WorkoutGoal.GoalId = 10 
left outer join Goal on WorkoutGoal.GoalId = Goal.GoalId;
-- [done]

-- The relationship between Workouts and Exercises is... complicated.
-- Workout links to WorkoutDay (one day in a Workout routine)
-- which links to WorkoutDayExerciseInstance 
-- (Exercises can be repeated in a day so a bridge table is required) 
-- which links to ExerciseInstance 
-- (Exercises can be done with different weights, repetions,
-- laps, etc...) 
-- which finally links to Exercise.
-- Select Workout.Name and Exercise.Name for related Workouts and Exercises.
--------------------
select 
	Workout.`Name`,
    Exercise.`Name`
from Workout
left outer join WorkoutDay on Workout.WorkoutId = WorkoutDay.WorkoutId
left outer join WorkoutDayExerciseInstance on WorkoutDay.WorkoutDayId = WorkoutDayExerciseInstance.WorkoutDayId
left outer join ExerciseInstance on WorkoutDayExerciseInstance.ExerciseInstanceId = ExerciseInstance.ExerciseInstanceId
left outer join Exercise on ExerciseInstance.ExerciseId = Exercise.ExerciseId;
-- [done]
   
-- An ExerciseInstance is configured with ExerciseInstanceUnitValue.
-- It contains a Value and UnitId that links to Unit.
-- Example Unit/Value combos include 10 laps, 15 minutes, 200 pounds.
-- Select Exercise.Name, ExerciseInstanceUnitValue.Value, and Unit.Name
-- for the 'Plank' exercise. 
-- How many Planks are configured, which Units apply, and what 
-- are the configured Values?
-- 4 rows, 1 Unit, and 4 distinct Values
--------------------
select
	Exercise.`Name`,
    ExerciseInstanceUnitValue.`Value`,
    Unit.`Name`
from Exercise
left outer join ExerciseInstance on Exercise.ExerciseId = ExerciseInstance.ExerciseId and Exercise.`Name` = 'Plank'
left outer join ExerciseInstanceUnitValue on ExerciseInstance.ExerciseInstanceId = ExerciseInstanceUnitValue.ExerciseInstanceId
left outer join Unit on ExerciseInstanceUnitValue.UnitId = Unit.UnitId
where Unit.`Name` is not null;
-- [done]
    