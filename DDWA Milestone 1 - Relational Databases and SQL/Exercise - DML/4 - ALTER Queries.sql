use MovieCatalogue;

alter table Actor
	add column 
		(DateOfDeath date null);

update Actor set
	DateOfDeath = '1994-03-04'
where ActorID = 3;