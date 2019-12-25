create table kunde(
	id int identity(1,1),
	name varchar(200),
	email varchar(200),
	primary key (id asc)
);

create table bestellung(
	id int identity(1,1),
	datum date,
	lieferadresse varchar(200),
	kundennummer int,
	bestellwert int,
	foreign key (kundennummer) references kunde(id),
	primary key (id asc)
);


insert into kunde(name,email) values 
	('Michaela','123@456.de'),
	('Test','123@456.de'),
	('Deike','123er@456.de'),
	('Klaus','12w3@456.de'),
	('Matze','12sss3@456.de'),
	('Herbert','1wwdc23@456.de'),
	('Carolin','1wewd23@456.de');

insert into bestellung(datum,lieferadresse, kundennummer, bestellwert) values
	('2018-04-21','Marx Stra�e 4',1,1),
	('2018-03-11','Lauterweg 12',1,2),
	('2018-04-21','Marx Stra�e 8',1,10),
	('2018-05-11','Bananengasse 189',2,2),
	('2018-06-03','Lauterweg 12',2,7),
	('2018-07-04','Wie auch immer Stra�e 9',5,1),
	('2018-02-05','Marx Stra�e 4',5,1),
	('2018-02-05','Marx Stra�e 4',5,10),
	('2018-02-05','Marx Stra�e 4',6,1),
	('2018-02-05','Marx Stra�e 4',6,1),
	('2018-02-05','Marx Stra�e 4',7,1),
	('2018-02-05','Marx Stra�e 4',7,15),
	('2018-02-05','Marx Stra�e 4',3,1),
	('2018-02-05','Marx Stra�e 4',4,1),
	('2018-03-06','Marx Stra�e 4',null,0);



-- alle joins einmal durch
select * from kunde inner join bestellung on bestellung.kundennummer=kunde.id;
select * from kunde left join bestellung on bestellung.kundennummer=kunde.id;
select * from kunde left join bestellung on bestellung.kundennummer=kunde.id where bestellung.id is null;
select * from kunde right join bestellung on bestellung.kundennummer=kunde.id;
select * from kunde right join bestellung on bestellung.kundennummer=kunde.id where kunde.id is null;
select * from kunde full outer join bestellung on kunde.id=bestellung.kundennummer;

-- wir bauen uns einen Full Outer Join zusammen
SELECT	    kunde.name, bestellung.id, bestellung.datum  
FROM 	    kundeLEFT JOIN    bestellung 
			ON kunde.id = bestellung.kundennummer
WHERE 	    bestellung.kundennummer IS NULL

UNION all 

SELECT	    kunde.name, bestellung.id, bestellung.datum  
FROM 	    kundeINNER JOIN  bestellung 
			ON kunde.id = bestellung.kundennummer

UNION all

SELECT	    kunde.name, bestellung.id, bestellung.datum  
			FROM 	      kundeRIGHT JOIN    bestellung 
			ON kunde.id = bestellung.kundennummer
WHERE 	      bestellung.kundennummer IS NULL;

-- jeder left join ist ein right join
select kunde.*, bestellung.* from kunde left join bestellung on bestellung.kundennummer=kunde.id
except
select kunde.*, bestellung.* from bestellung right join kunde on bestellung.kundennummer=kunde.id;

-- Order by ausprobiert
select * from kunde order by email ASC, name desc;


-- Aufgabe mit der ABC Analyse
with 
	-- Als erstes bauen wir uns die Werte f�r 80%, 95% und 100% des Umsatzes zusammen
	ABC_Punkte(A,B,C) as(
		select	sum(bestellwert)*0.8, 
				sum(bestellwert)*0.95,
				sum(bestellwert)
		from bestellung
	),
	-- Dann m�ssen wir wissen, wieviel Umsatz jeder Kunde f�r sich generiert hat
	Kundenumsatz(kunde, id, umsatz) as(
		select	kunde.name, 
				kunde.id, 
				isnull(sum(bestellung.bestellwert),0) as umsatz 
		from	kunde left join bestellung on (kunde.id=bestellung.kundennummer)
		group by kunde.name, kunde.id
	),
	-- Jetzt k�nnen wir eine ABC Analyse machen
	ABC_Kunden (kunde, umsatz, kumuliert, typ) as(
		select	K.kunde, -- Wir nehemn den Kunden 
				K.umsatz, -- und seinen Umsatz
				(Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz), -- dann die Summe der Ums�tze von Kunden die mehr ausgegeben haben
				case 
					when (Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz)<=(select A from ABC_Punkte) then 'A' -- Ist diese Summe kleiner als 80% des Umastze, ist das ein A Kunde
					when (Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz)<=(select B from ABC_Punkte) then 'B' -- Ist sie kleiner als 95% des Umsatzes ist es ein B Kunde
					when (Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz)<=(select C from ABC_Punkte) then 'C' -- SOnst ist es ein C Kunde
				end
		from	Kundenumsatz as K
	)

select * from ABC_Kunden order by umsatz desc

drop table bestellung;
drop table kunde;

