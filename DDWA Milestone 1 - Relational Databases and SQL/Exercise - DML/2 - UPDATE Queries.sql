use MovieCatalogue;

update Movie set
	Title = 'Ghostbusters (1984)',
    ReleaseDate = '1984-06-08'
where MovieID = 3;

update Genre set
	GenreName = 'Action/Adventure'
where GenreID = 1;