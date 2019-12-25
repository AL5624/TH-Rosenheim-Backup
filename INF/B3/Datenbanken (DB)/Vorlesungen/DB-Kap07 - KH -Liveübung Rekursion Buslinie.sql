--use Vorlesung_DB;

create table buslinie(
	abfahrt varchar(30),
	ankunft varchar (30),
	linie varchar(30),
	dauer int,
	typ varchar (10),
	abfahrtszeit time
);


insert into buslinie (abfahrt,ankunft,linie,dauer,typ,abfahrtszeit) values
	('RO Stadtmitte',		'Wasserburg am Inn',	'RVO9193',			30,		'DBbus',	'10:31'),
	('RO Stadtmitte',		'Kolbermoor Bahnhof',	'RVO9199',			2,		'DBbus',	'10:42'),
	('Kolbermoor Bahnhof',	'Großkarolinenfeld',	'M4321',			18,		'Meridian', '10:48'),
	('Kolbermoor Bahnhof',	'Bad Aibling',			'M1234',			22,		'Meridian', '11:04'),
	('Bad Aibling',			'Raubling',				'M4562',			40,		'Meridian', '11:34'),
	('RO Stadtmitte',		'RO Bahnhof',			'Linie12',			2,		'SVRbus',	'10:22'),
	('RO Stadtmitte',		'RO Bahnhof',			'Linie6',			2,		'SVRbus',	'10:21'),
	('RO Stadtmitte',		'RO Bahnhof',			'Linie10',			2,		'SVRbus',	'10:22'),
	('RO Stadtmitte',		'RO Bahnhof',			'Linie3',			2,		'SVRbus',	'10:23'),
	('RO Stadtmitte',		'RO Bahnhof',			'Linie7',			2,		'SVRbus',	'10:24'),
	('RO Bahnhof',			'Siedlerplatz',			'Linie8',			12,		'SVRbus',	'10:31'),
	('RO Bahnhof',			'Artmeier',				'Linie9',			18,		'SVRbus',	'10:33'),
	('Artmeier',			'Königseestraße',		'RVO9496',			1,		'DBbus',	'11:02'),
	('RO Bahnhof',			'Königsseestraße',		'Linie9',			22,		'SVRbus',	'11:48'),
	('RO Bahnhof',			'Ostermünchen',			'M5632',			8,		'Meridian', '11:31'),
	('RO Bahnhof',			'Ostbahnhof',			'M55423',			27,		'Meridian', '11:28'),
	('Ostbahnhof',			'Neuperlach Süd',		'U5',				8,		'Ubahn',	'12:05'),
	('THRO',				'RO Bahnhof',			'DB Bummelzug',		3,		'DB',		'10:25'),
	('THRO',				'RO Stadtmitte',		'Linie4',			6,		'SVRbus',	'10:12');

--insert into buslinie (abfahrt,ankunft,linie,dauer,typ) values ('Raubling','RH Bahnhof','M4561',40,'bahn','11:35') -- Ein Kreis, Achtung!

--Beispiel mit with
with 
	Verbindung(Abfahrt,Ankunft) as (	
		Select	Abfahrt, Ankunft
		from	buslinie 
		where	Abfahrt='THRO')

select Abfahrt, Ankunft from Verbindung;

-- Das ganze mal ohne Rekursion bis zu einer Tiefe von 3 
select	Abfahrt, Ankunft
from	BUSLINIE
where	Abfahrt = 'THRO'
	union --all 
select	B1.Abfahrt, B2.Ankunft
from	BUSLINIE B1, BUSLINIE B2
where	B1.Abfahrt = 'THRO' and B1.Ankunft = B2.Abfahrt
	union --all
select	B1.Abfahrt, B3.Ankunft
from	BUSLINIE B1, BUSLINIE B2, BUSLINIE B3
where	B1.Abfahrt = 'THRO' and B1.Ankunft = B2.Abfahrt and B2.Ankunft = B3.Abfahrt;

-- jetzt mit with
with 
	Verbindung(Abfahrt,Ankunft) as ( -- Verbindung	
		Select	Abfahrt, Ankunft
		from	buslinie 
		where	Abfahrt='THRO'),

	zweiteVerbindung(Abfahrt,Ankunft) as ( -- zweiteVerbindung
		Select	V.Abfahrt, B.Ankunft 
		from	Verbindung as V, buslinie as B  -- wir benutzen Verbindung
		where	V.Ankunft=B.Abfahrt),

	dritteVerbindung(Abfahrt, Ankunft) as(-- dritte Verbindung
		Select 	V.Abfahrt, B.Ankunft 
		from	zweiteVerbindung as V, buslinie as B -- wir benutzen zweiteVerbindung
		where	V.Ankunft=B.Abfahrt)

select * from Verbindung 
	union 
select * from zweiteVerbindung
	union
select * from dritteVerbindung;


-- jetzt mit Rekursion
with 
	Reise(Abfahrt, Ankunft) as (    
		select Abfahrt, Ankunft
		from   Buslinie    
		where  Abfahrt = 'THRO'      
			union all    
		select R.Abfahrt, B.Ankunft
		from   Reise R, Buslinie B    
		where  R.Ankunft = B.Abfahrt)
		
select distinct * from Reise;

-- jetzt mit Rekursion, wir schreiben uns den Weg auf
with 
	Reise(Abfahrt, Ankunft, Weg) as (    
		select Abfahrt, Ankunft, cast('THRO -> '+ Ankunft  as varchar(100)) 
		from   BUSLINIE    
		where  Abfahrt = 'THRO'      
			union all    
		select R.Abfahrt, B.Ankunft, cast( R.Weg + '->' + B.Ankunft as varchar (100)) 
		from   Reise R, Buslinie B    
		where  R.Ankunft = B.Abfahrt)
		
select distinct * from Reise;

-- jetzt mit Rekursion, wir zählen
with 
	Reise(Abfahrt, Ankunft, Dauer) as (    
		select Abfahrt, Ankunft, Dauer    
		from   BUSLINIE    
		where  Abfahrt = 'THRO'      
			union all    
		select R.Abfahrt, B.Ankunft, R.dauer+B.dauer    
		from   Reise R, Buslinie B    
		where  R.Ankunft = B.Abfahrt)
		
select * from Reise;

-- Rekursion, diesmal schauen wir ob wir dort hin gelangen wo wir schon waren (Kreis)
-- Dazu müssen wir erst mal einen Kreis einbauen, damit wir das testen können!
with 
	Reise(Abfahrt, Ankunft, BisherigerWeg) as (    
		select Abfahrt, Ankunft, cast(Abfahrt+'#'+Ankunft+'#' as varchar (1000))   
		from   Buslinie    
		where  Abfahrt = 'THRO'      
			union all    
		select R.Abfahrt, B.Ankunft, cast(BisherigerWeg+B.Ankunft+'#' as varchar (1000))    
		from   Reise R, Buslinie B    
		where  R.Ankunft = B.Abfahrt
		  and  R.BisherigerWeg not like '%'+B.ankunft+'#%'			
		)	
select * from Reise;


-- Jetzt mal alles zusammen
with 
	Reise(Abfahrt, Ankunft, Weg, Reine_Fahrzeit) as (    
		select Abfahrt, Ankunft, cast('THRO -> '+ Ankunft  as varchar(100)), Dauer
		from   BUSLINIE    
		where  Abfahrt = 'THRO'      
			union all    
		select R.Abfahrt, B.Ankunft, cast( R.Weg + '->' + B.Ankunft as varchar (100)), R.Reine_Fahrzeit+B.Dauer
		from   Reise R, Buslinie B    
		where  R.Ankunft = B.Abfahrt)
		
select distinct * from Reise;

--drop table buslinie;
