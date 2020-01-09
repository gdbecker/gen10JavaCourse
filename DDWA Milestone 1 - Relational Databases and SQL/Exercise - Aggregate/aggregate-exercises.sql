use PersonalTrainer;

-- Use an aggregate to count the number of Clients.
-- 500 rows
--------------------
select
	count(ClientId)
from Client;
-- [done]

-- Use an aggregate to count Client.BirthDate.
-- The number is different than total Clients. Why?
-- 463 rows
--------------------
select
	count(BirthDate)
from Client;
-- [done]

-- Group Clients by City and count them.
-- Order by the number of Clients desc.
-- 20 rows
--------------------
select
	City,
    count(City)
from Client
group by Client.City
order by count(City) desc;
-- [done]

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- Group by InvoiceId.
-- You'll need an expression for the line item total: Price * Quantity.
-- Aggregate per group using SUM().
-- 1000 rows
--------------------
select
	InvoiceId,
    sum(Price * Quantity) Total
from InvoiceLineItem
group by InvoiceId;
-- [done]

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- (See above.)
-- Only include totals greater than $500.00.
-- Order from lowest total to highest.
-- 234 rows
--------------------
select
	InvoiceId,
    sum(Price * Quantity) Total
from InvoiceLineItem
group by InvoiceId
having sum(Price * Quantity) >= 500
order by sum(Price * Quantity) asc;
-- [done]

-- Calculate the average line item total
-- grouped by InvoiceLineItem.Description.
-- 3 rows
--------------------
select
	Description,
    (sum(Price * Quantity))/(count(Price)) Average
from InvoiceLineItem
group by Description
order by (sum(Price * Quantity))/(count(Price)) asc;
-- [done]

-- Select ClientId, FirstName, and LastName from Client
-- for clients who have *paid* over $1000 total.
-- Paid is Invoice.InvoiceStatus = 2.
-- Order by LastName, then FirstName.
-- 146 rows
--------------------
select 
	Client.ClientId,
    Client.FirstName,
    Client.LastName,
    sum(InvoiceLineItem.Price * InvoiceLineItem.Quantity) Total
from Client
left outer join Invoice on Client.ClientId = Invoice.ClientId and Invoice.InvoiceStatus = 2
left outer join InvoiceLineItem on Invoice.InvoiceId = InvoiceLineItem.InvoiceId
group by Client.ClientId, Client.FirstName, Client.LastName
having sum(InvoiceLineItem.Price * InvoiceLineItem.Quantity) >= 1000
order by Client.LastName asc, Client.FirstName asc;
-- [done]

-- Count exercises by category.
-- Group by ExerciseCategory.Name.
-- Order by exercise count descending.
-- 13 rows
--------------------
select
	ExerciseCategory.Name,
    count(Exercise.ExerciseId) NumExercises
from ExerciseCategory
right outer join Exercise on ExerciseCategory.ExerciseCategoryId = Exercise.ExerciseCategoryId
group by ExerciseCategory.Name
order by NumExercises desc;
-- [done]

-- Select Exercise.Name along with the minimum, maximum,
-- and average ExerciseInstance.Sets.
-- Order by Exercise.Name.
-- 64 rows
--------------------
select
	Exercise.Name,
    min(ExerciseInstance.Sets) MinSets,
    max(ExerciseInstance.Sets) MaxSets,
    avg(ExerciseInstance.Sets) AvgSets
from Exercise
inner join ExerciseInstance on Exercise.ExerciseId = ExerciseInstance.ExerciseId
group by Exercise.ExerciseId, Exercise.Name
order by Exercise.Name;
-- [done]

-- Find the minimum and maximum Client.BirthDate
-- per Workout.
-- 26 rows
-- Sample: 
-- WorkoutName, EarliestBirthDate, LatestBirthDate
-- '3, 2, 1... Yoga!', '1928-04-28', '1993-02-07'
--------------------
select
	Workout.Name WorkoutName,
	min(Client.BirthDate) EarliestBirthDate,
    max(Client.BirthDate) LatestBirthDate
from Workout
left outer join ClientWorkout on Workout.WorkoutId = ClientWorkout.WorkoutId
left outer join Client on ClientWorkout.ClientId = Client.ClientId
group by Workout.Name;
-- [done]

-- Count client goals.
-- Be careful not to exclude rows for clients without goals.
-- 500 rows total
-- 50 rows with no goals
--------------------
select
	Client.FirstName,
    Client.LastName,
    count(Goal.GoalId) NumGoals
from Client
left outer join ClientGoal on Client.ClientId = ClientGoal.ClientId
left outer join Goal on ClientGoal.GoalId = Goal.GoalId
group by Client.ClientId;
-- [done]

-- Select Exercise.Name, Unit.Name, 
-- and minimum and maximum ExerciseInstanceUnitValue.Value
-- for all exercises with a configured ExerciseInstanceUnitValue.
-- Order by Exercise.Name, then Unit.Name.
-- 82 rows
--------------------
select
	Exercise.Name ExerciseName,
    Unit.Name UnitName,
    min(ExerciseInstanceUnitValue.Value) MinValue,
    max(ExerciseInstanceUnitValue.Value) `MaxValue`
from Exercise
left outer join ExerciseInstance on Exercise.ExerciseId = ExerciseInstance.ExerciseId
left outer join ExerciseInstanceUnitValue on ExerciseInstance.ExerciseInstanceId = ExerciseInstanceUnitValue.ExerciseInstanceId
left outer join Unit on ExerciseInstanceUnitValue.UnitId = Unit.UnitId
group by Exercise.ExerciseId, Unit.UnitId
order by Exercise.Name, Unit.Name;
-- [done]

-- Modify the query above to include ExerciseCategory.Name.
-- Order by ExerciseCategory.Name, then Exercise.Name, then Unit.Name.
-- 82 rows
--------------------
select
	ExerciseCategory.Name ExerciseCategory,
	Exercise.Name ExerciseName,
    Unit.Name UnitName,
    min(ExerciseInstanceUnitValue.Value) MinValue,
    max(ExerciseInstanceUnitValue.Value) `MaxValue`
from Exercise
left outer join ExerciseInstance on Exercise.ExerciseId = ExerciseInstance.ExerciseId
left outer join ExerciseInstanceUnitValue on ExerciseInstance.ExerciseInstanceId = ExerciseInstanceUnitValue.ExerciseInstanceId
left outer join Unit on ExerciseInstanceUnitValue.UnitId = Unit.UnitId
left outer join ExerciseCategory on Exercise.ExerciseCategoryId = ExerciseCategory.ExerciseCategoryId
group by Exercise.ExerciseId, Unit.UnitId, ExerciseCategory.ExerciseCategoryId
order by ExerciseCategory.Name, Exercise.Name, Unit.Name;
-- [done]

-- Select the minimum and maximum age in years for
-- each Level.
-- To calculate age in years, use the MySQL function DATEDIFF.
-- 4 rows
--------------------
select
	Level.Name Level,
	min(datediff(curdate(), Client.BirthDate) / 365) MinAge,
    max(datediff(curdate(), Client.BirthDate) / 365) MaxAge
from Level
left outer join Workout on Level.LevelId = Workout.LevelId
left outer join ClientWorkout on Workout.WorkoutId = ClientWorkout.WorkoutId
left outer join Client on ClientWorkout.ClientId = Client.ClientId
group by Level.Name;
-- [done]

-- Stretch Goal!
-- Count logins by email extension (.com, .net, .org, etc...).
-- Research SQL functions to isolate a very specific part of a string value.
-- 27 rows (27 unique email extensions)
--------------------
select distinct
	substring_index(EmailAddress, '.', -1) as EmailExtension,
	count(EmailAddress) as NumEmailExtension
from Login
group by EmailExtension;
-- [done]

-- Stretch Goal!
-- Match client goals to workout goals.
-- Select Client FirstName and LastName and Workout.Name for
-- all workouts that match at least 2 of a client's goals.
-- Order by the client's last name, then first name.
-- 139 rows
--------------------
select
	Client.FirstName,
    Client.LastName,
    Workout.Name WorkoutName
from Workout
left outer join WorkoutGoal on Workout.WorkoutId = WorkoutGoal.WorkoutId
left outer join Goal on WorkoutGoal.GoalId = Goal.GoalId
left outer join ClientGoal on Goal.GoalId = ClientGoal.GoalId
left outer join Client on ClientGoal.ClientId = Client.ClientId
group by Client.FirstName, Client.LastName, Workout.Name
having count(WorkoutGoal.GoalId = ClientGoal.GoalId) >= 2
order by Client.LastName, Client.FirstName;
-- [done]
