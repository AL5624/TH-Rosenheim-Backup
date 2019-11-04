create table zutaten(
	name varchar(100),
	hersteller varchar (100),
	alk int,
	ean_code char(13),
	primary key (name,hersteller) -- primary key aus mehreren Attributen
)

create table drinks(
	name varchar(100) primary key, -- abkürzend direkt dahinter bei nur einem primary key Attribut
	typ varchar (100) null,
	preis decimal (4,2)	
)

create table rezepte(
	name varchar(100),
	hersteller varchar (100),
	menge decimal (3,0),
	drink varchar(100) foreign key references drinks(name), --foreign key mit nur einem Attribut
	foreign key (name,hersteller) references zutaten(name,hersteller) -- foreign key mit mehereren Attributen
)

-- Einige INSERT-Operationen so wie es sein soll...
insert into zutaten (name, hersteller, alk, ean_code) values 
	('Vodka','Jelzin',30.0,'1123565287326'), 
	('Orangensaft','Albi',1,'1102977514231'), 
	('Das rote Zeug','Zuckerfabrik 1A',0,'9853281753425'), 
	('Tequilla','Sierra',32,null);

insert into drinks (name, typ, preis) values 
	('Vodka Orange','Longrink',8.00), 
	('Tequilla Sunrise','Longrink',6.99);

insert into rezepte (name, hersteller, menge, drink) values 
	('Vodka','Jelzin',20,'Vodka Orange'), 
	('Orangensaft','Albi',130,'Vodka Orange'), 
	('Tequilla','Sierra',30,'Tequilla Sunrise'),
	('Das rote Zeug','Zuckerfabrik 1A',10,'Tequilla Sunrise'),
	('Orangensaft','Albi',170,'Tequilla Sunrise');

-- INSERT-Operationen die zwar funktionieren, aber nicht so schön sind
insert into zutaten values ('Gin','Mare',30.0,null);
insert into zutaten (name, hersteller, alk)	values ('Fanta','Albi',0);

-- UPDATE-Operationen
-- nur der Vodka Orange bekommt mehr Vodka
update	rezepte 
set		menge=30 
where	name='Vodka' and 
		hersteller='Jelzin' and 
		drink='Vodka Orange'; 
-- alle Drinks mit Tequilla im Namen bekommen 10% mehr Inhalt
update	rezepte 
set		menge=menge*1.1
where	drink like '%Tequilla%'

-- DELETE Operationen
-- alle Fanta kommt weg.
delete from zutaten
where zutaten.name ='Fanta';

-- SFW Statements

-- Einfache Selekts
select * from zutaten;
select distinct hersteller from zutaten;
select Z.hersteller as HST from zutaten as Z;
select hersteller,name from zutaten where alk>0;

-- Kreuzprodukt
select * from zutaten, drinks;
select * from zutaten as z1, zutaten as z2, zutaten as z3, drinks as d1, drinks as d2;
select z1.* from zutaten as z1, zutaten as z2, zutaten as z3, drinks as d1, drinks as d2;
select distinct z1.* from zutaten as z1, zutaten as z2, zutaten as z3, drinks as d1, drinks as d2;

-- Natürlicher Verbund
select * from drinks, rezepte where drinks.name = rezepte.drink;
select * from drinks join rezepte on (drinks.name=rezepte.drink);
select * from drinks inner join rezepte on (drinks.name=rezepte.drink);

-- Natürlcher Verbund über meherere Tebellen
select	d.name,  
		d.preis,
		r.name,
		r.hersteller,
		r.menge,
		z.alk
from	drinks as d
		join rezepte as r on (d.name=r.drink)
		join zutaten as z on (r.hersteller=z.hersteller and r.name=z.name);
-- oder
select * 
from	drinks as d, rezepte as r, zutaten as z
where
		d.name=r.drink and	


		r.hersteller=z.hersteller and 
		r.name=z.name;


drop table rezepte;
drop table drinks;
drop table zutaten;
