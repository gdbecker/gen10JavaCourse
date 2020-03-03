use GuessTheNumberGame;

-- Add in some practice data into database to make sure it works
insert into Game (Answer, `Status`) values
	('2345', 'Finished'),
    ('2837', 'Finished'),
    ('9283', 'Finished'),
    ('5281', 'In Progress');
    
insert into Round (GameID, GuessValue, GuessTime, GuessResult) values
	(1, '2736', 'guessTime','guessResult'),
    (1, '9283', 'guessTime','guessResult'),
    (2, '0192', 'guessTime','guessResult'),
    (2, '5263', 'guessTime','guessResult'),
    (2, '2837', 'guessTime','guessResult'),
    (3, '4958', 'guessTime','guessResult'),
    (4, '7162', 'guessTime','guessResult'),
    (4, '9876', 'guessTime','guessResult');
    