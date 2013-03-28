CREATE TABLE Profiles (
	P_ID 		integer,
	FirstName 	text NOT NULL,
	LastName 	text,
	UserPoint	integer,
	Email		text,
	Gender 		text,
 
	PRIMARY KEY(P_ID)
);

INSERT INTO Profiles VALUES(1, 'Louis', 'Hong', 0, 'siuol@sina.cn', 'Male');
INSERT INTO Profiles (P_ID, FirstName, LastName, UserPoint, Gender) VALUES(2, 'Sabrina', 'Xie', 0, "Femail");

