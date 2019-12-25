drop table Student_in_Veranstaltung;
drop table Veranstaltungen;
drop table Dozenten;
drop table Studenten;

create table Studenten(
	Name varchar(30),
	Matrikel decimal(4,0),
	Geburtstag date,
	primary key (Matrikel),
	constraint matrikel_nicht_negativ check(Matrikel>=0)
);
create table Dozenten(
	Name varchar(30),
	Buero varchar(30) not null,
	Tel varchar(30),
	primary key(Name)
);
create table Veranstaltungen(
	Name varchar (30),
	Semester char(4),
	Raum varchar (8),
	Dozent varchar (30),
	primary key (Name, Semester),
	foreign key (Dozent) references Dozenten(Name)
);
create table Student_in_Veranstaltung(
	Student decimal(4,0),
	Veranstaltung varchar(30),
	Semester char(4),
	Note Decimal(2,1),
	foreign key (Student) references Studenten(Matrikel),
	foreign key (Veranstaltung, Semester) references Veranstaltungen(Name,Semester),
	constraint schulnote check(Note >= 1 and Note<=5),
	primary key (Student, Veranstaltung, Semester)
);

insert into Dozenten (Name, Tel, Buero) values ('Klaus', '123', 'C201');
insert into Veranstaltungen (Dozent, Name, Raum, Semester) values 
	('Klaus','Tanzgymnastik','D111','ss18'),
	('Klaus','Tanzgymnastik','D111','ws17'),
	('Klaus','Sackhüpfen',null,'ws17');

insert into Dozenten (Name, Buero) values ('Maria', 'D120');
insert into Veranstaltungen (Dozent, Name, Raum, Semester) values 
	('Maria','Drachenfliegen','Strand','ss17'),
	('Maria','Drachenfliegen','Strand','ss18'),
	('Maria','Beachvolleyball','Strand','ss17'),
	('Maria','Beachvolleyball','Strand','ss18');

insert into Dozenten (Name, Buero) values ('Sepp', 'D10');

insert into Studenten (Name, Matrikel, Geburtstag) values 
	('Eva',3333,'1990-03-01'),
	('Luise',3334,'1990-06-02'),
	('Daniel',3335,'1990-07-01'),
	('Sepp',3331,'1993-02-01'),
	('Dominik',3336,'1990-08-01');

insert into Student_in_Veranstaltung (Student, Veranstaltung, Semester, Note) values 
	(3333,'Beachvolleyball','ss18',1.0),
	(3334,'Beachvolleyball','ss18',2.2),
	(3335,'Beachvolleyball','ss18',2.4),
	(3333,'Drachenfliegen','ss17',4.0),
	(3336,'Drachenfliegen','ss17',4.0),
	(3334,'Tanzgymnastik','ws17',5),
	(3335,'Tanzgymnastik','ws17',2.2),
	(3334,'Beachvolleyball','ss17',1.2),
	(3335,'Beachvolleyball','ss17',1.3);
	
-- Aufgabe 10.1.a
-- Beste Note
select
	D.Name,
	V.Name,
	min(SinV.Note)
from 
	Dozenten as D
	inner join Veranstaltungen as V 
	on V.Dozent=D.Name
	inner join Student_in_Veranstaltung as SinV 
	on SinV.Veranstaltung = V.Name and SinV.Semester=V.Semester
group by D.Name,V.Name;

-- Aufgabe 10.1.b
-- schlechteste Note eines Dozenten
select
	D.Name,
	--V.Name,
	max(SinV.Note)
from 
	Dozenten as D
	inner join Veranstaltungen as V 
	on V.Dozent=D.Name
	inner join Student_in_Veranstaltung as SinV 
	on SinV.Veranstaltung = V.Name and SinV.Semester=V.Semester
group by D.Name--,V.Name;

-- Aufgabe 10.1.c
-- Anzahl studenten in jeder Veranstaltung
select
	D.Name,
	V.Name,
	count(*)
from 
	Dozenten as D
	inner join Veranstaltungen as V 
	on V.Dozent=D.Name
	inner join Student_in_Veranstaltung as SinV 
	on SinV.Veranstaltung = V.Name and SinV.Semester=V.Semester
group by D.Name,V.Name;

-- Aufgabe 10.1.d
-- Durchscnittliche Anzahl der Studenten in den Veranstaltungen eines Dozenten
select
	anz.dozenten_name,
	avg(anz.anzahl_studenten)
from 
	(	select
			D.Name as dozenten_name,
			V.Name,
			count(*) as anzahl_studenten
		from 
			Dozenten as D
			inner join Veranstaltungen as V 
			on V.Dozent=D.Name
			inner join Student_in_Veranstaltung as SinV 
			on SinV.Veranstaltung = V.Name and SinV.Semester=V.Semester
		group by D.Name,V.Name
	) as anz
group by dozenten_name;

-- Aufgabe 10.2.a
-- Alle Veranstaltungen eines Dozenten für die sich noch kein Student registriert hat
select * 
from	Dozenten left join Veranstaltungen on Veranstaltungen.Dozent=Dozenten.Name
		left join Student_in_Veranstaltung on 
				Veranstaltungen.Name=Student_in_Veranstaltung.Veranstaltung and 
				Veranstaltungen.Semester=Student_in_Veranstaltung.Semester
where	Veranstaltungen.Name is not null and 
		Veranstaltungen.Semester is not null and
		Student_in_Veranstaltung.Semester is null and
		Student_in_Veranstaltung.Veranstaltung is null and
		Student_in_Veranstaltung.Student is null

-- Aufgabe 10.2.b
-- Liste aller Studenten, wenn möglich mit besuchten Veranstaltungen
Select * 
from	Studenten 
		left join Student_in_Veranstaltung on Student_in_Veranstaltung.Student=Studenten.Matrikel 

-- Aufgabe 10.2.c
-- Right join 
Select * 
from	Student_in_Veranstaltung
		right join Studenten  on Student_in_Veranstaltung.Student=Studenten.Matrikel;

-- Aufgabe 10.3 Testdaten
create table Ahnen 
(
	Name varchar(50) not null,
	Vater varchar(50),
	Mutter varchar(50),
	primary key (Name),
	foreign key (Vater) references Ahnen(Name),
	foreign key (Mutter) references Ahnen(Name)
)

insert into Ahnen (Name, Vater, Mutter) values 
	('Adam',null,null), 
	('Eva',null,null) ,
	('Kain','Adam','Eva') ,
	('Abel','Adam','Eva') ,
	('Seth','Adam','Eva') ,
	('Tharah','Seth',null),
	('Abraham','Tharah',null),
	('Nahor','Tharah',null),
	('Haran','Tharah',null),
	('Ismael','Abraham',null),
	('Isaak','Abraham',null),
	('Simran','Abraham',null),
	('Joksan','Abraham',null),
	('Medan','Abraham',null),
	('Midian','Abraham',null),
	('Jesbak','Abraham',null),
	('Suah','Abraham',null),
	('Jakob','Isaak',null),
	('Josef','Jakob',null) ,
	('Maria',null,null) ,
	('Jesus','Josef','Maria');

-- Aufgabe 10.3.a
-- Alle direkten Ahnen zu eiener Person
select Name as Name, Vater as Ahne
from Ahnen 
	union 
select Name as Name, Mutter as Ahne
from Ahnen;

-- Aufgabe 10.3.b
-- erste, zweite und dritte Verwandtschaftsverhältnisse
-- (Aufgabe 9.3.4 direkt mit Verwandschaftsgrad)
with 
	ersteVerwandtschaftsverhaeltnisse (Name,Ahne,Grad) as (
		select Name, Vater as Ahne, 1 as Grad
		from Ahnen 
			union 
		select Name, Mutter as Ahne, 1 as Grad
		from Ahnen 
	),

	zweiteVerwandtschaftsverhaeltnisse (Name,Ahne,Grad) as (
		select ersteVerwandtschaftsverhaeltnisse.Name as Name , Ahnen.Name as Ahne, ersteVerwandtschaftsverhaeltnisse.Grad+1
		from ersteVerwandtschaftsverhaeltnisse, Ahnen
		where
			Ahnen.Name in 
			(
				select naechsterAhne.Vater 
				from Ahnen as naechsterAhne 
				where ersteVerwandtschaftsverhaeltnisse.Ahne=naechsterAhne.Name
			) or 
			Ahnen.Name in 
			(
				select naechsterAhne.Mutter 
				from Ahnen as naechsterAhne 
				where ersteVerwandtschaftsverhaeltnisse.Ahne=naechsterAhne.Name
			)
	),

	dritteVerwandtschaftsverhaeltnisse (Name,Ahne,Grad) as (
		select zweiteVerwandtschaftsverhaeltnisse.Name as Name , Ahnen.Name as Ahne, zweiteVerwandtschaftsverhaeltnisse.Grad+1
		from zweiteVerwandtschaftsverhaeltnisse, Ahnen
		where
			Ahnen.Name in 
			(
				select naechsterAhne.Vater 
				from Ahnen as naechsterAhne 
				where zweiteVerwandtschaftsverhaeltnisse.Ahne=naechsterAhne.Name
			) or 
			Ahnen.Name in 
			(
				select naechsterAhne.Mutter 
				from Ahnen as naechsterAhne 
				where zweiteVerwandtschaftsverhaeltnisse.Ahne=naechsterAhne.Name
			)
	)

select * from ersteVerwandtschaftsverhaeltnisse
union
select * from zweiteVerwandtschaftsverhaeltnisse
union
select * from dritteVerwandtschaftsverhaeltnisse;

-- Aufgabe 10.3.c die Rekursion
-- (Aufgabe 10.3.d direkt mit Verwandschaftsgrad)
with Stammbaum(Name, Ahne, Grad) as
(
	select A.Name as Name, A.Vater as Ahne, 1 as Grad
	from Ahnen as A

	union all

	select A.Name as Name, A.Mutter as Ahne, 1 as Grad
	from Ahnen as A

	union all

	select S.Name as Name , A.Name as Ahne, Grad+1 as Grad 
	from Stammbaum as S, Ahnen as A 
	where
		A.Name in 
		(
			select naechsterAhne.Vater 
			from Ahnen as naechsterAhne 
			where S.Ahne=naechsterAhne.Name
		) or 
		A.Name in 
		(
			select naechsterAhne.Mutter 
			from Ahnen as naechsterAhne 
			where S.Ahne=naechsterAhne.Name
		)
)
select DISTINCT * from Stammbaum  
order by Name ASC, Grad DESC ;


-- Alternative Implementierung zu der Rekursion (auch mit Verwandschaftsgrad)
-- Zuerst erstellen wir eine Tabelle Combo, die alle
-- (Name, Eltern) Einträge enthält, dann machen wir auf dieser
-- Tabelle die Rekursion.
with 
Combo(Name, Ahne, Grad) as
(
	select A.Name as Name, A.Vater as Ahne, 1 as Grad
	from Ahnen as A

	union all

	select A.Name as Name, A.Mutter as Ahne, 1 as Grad
	from Ahnen as A	
),

Stammbaum2(Name, Ahne, Grad) as
(
	select * from Combo 

	union all

	select S.Name as Name , A.Name as Ahne, S.Grad+1 as Grad 
	from Stammbaum2 as S, Combo as A 
	where
		A.Name in 
		(
			select naechsterAhne.Ahne 
			from Combo as naechsterAhne 
			where S.Ahne=naechsterAhne.Name
		) 
)
select * from Stammbaum2;

