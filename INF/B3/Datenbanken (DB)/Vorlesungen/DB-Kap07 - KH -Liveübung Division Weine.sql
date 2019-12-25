-- simulation der division in SQL

create table wein_empfehlung (
	wein varchar(30),
	kritiker varchar(20),
	primary key (wein, kritiker)
);

create table guides1 (
	kritiker varchar(20) primary key
);

create table guides2 ( 
	kritiker varchar(20) primary key
);

create table guides3 ( 
	kritiker varchar(20) primary key
);

insert into guides1 (kritiker) values 
	('Parker'),
	('Clarke');

insert into guides2 (kritiker) values 
	('Parker'),
	('Gault');

insert into wein_empfehlung values 
	('La Rose GrandCru', 'Parker'),
	('Pinot Noir', 'Parker'),
	('Riesling Reserve', 'Parker'),
	('La Rose GrandCru', 'Clarke'),
	('Pinot Noir', 'Clarke'),
	('Riesling Reserve', 'Gault');

-- direkte umsetzung der Definition
SELECT wein
FROM wein_empfehlung
	EXCEPT
SELECT w1.wein 
FROM (
		SELECT	weine.wein AS wein, guides2.kritiker AS kritiker 
		FROM	(
					SELECT wein 
					FROM wein_empfehlung
				) AS weine, guides2
			EXCEPT 
		SELECT * FROM wein_empfehlung
	) AS w1

-- jetzt nehmen wir die einzelnen Schritte Auseinander:
-- Wir wollen berechnen wein_empfehlung / guides2
	-- 1) wir nehmen alle Weine
SELECT wein
FROM wein_empfehlung
	-- außer (
SELECT w.wein, g.kritiker 
FROM wein_empfehlung as w, guides1 as g
	-- abzüglich der Empfehlungen
select	wein, kritiker
from wein_empfehlung
	-- )

-- Alternative: Mit Negation
select distinct Wein
from   WEIN_EMPFEHLUNG w1
where  not exists (
    select * from GUIDES2 g
        where not exists (
			select * from WEIN_EMPFEHLUNG w2
			where w1.Wein = w2.Wein and g.Kritiker = w2.Kritiker))

-- Alternative: Eine andere Umsetzung aus dem Netz
-- Hier haben die Autoren allerdings ein Problem 
-- bei Division mit 0 (!)
-- http://gregorulm.com/relational-division-in-sql-the-easy-way/ 
SELECT		Wein 
FROM		WEIN_EMPFEHLUNG w1 
WHERE		w1.Kritiker IN (
			SELECT Kritiker 
			FROM GUIDES2) 
GROUP BY	Wein 
HAVING		count(*) = (SELECT count(*) FROM GUIDES2)

/*
drop table wein_empfehlung;
drop table guides1;
drop table guides2;
drop table guides3;
*/
