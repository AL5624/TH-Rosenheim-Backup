


create table zutaten(
	name varchar(200),
	hersteller varchar(200),
	alk decimal(3,0) default 100.0,
	ean_code char(13),
	preis decimal(5,2),
	gebinde decimal(4,0),
	primary key (name,hersteller),
	CONSTRAINT alk_macht_sinn CHECK (alk<=100 and alk>=0)
);

create table drinks(
	name varchar(200),
	typ varchar(200) null,
	preis decimal(4,2),
	primary key (name)
);

create table rezepte(
	name varchar(200) not null,
	hersteller varchar(200) not null,
	menge decimal(3,0),
	drink varchar(200),
	/*	Wenn wir möchten, dass sich bei Änderung der Primary Keys 
		auch die Foreign Keys ändern: */
		foreign key (drink) references drinks(name) on update cascade on delete cascade,
		foreign key (name,hersteller) references zutaten(name,hersteller) on update cascade on delete cascade
);

insert into zutaten (name, hersteller, alk, ean_code,preis,gebinde) values 
	('Vodka','Jelzin',30.0,'453rfedafsteg',12.00,1000), 
	('Vodka2','Jelzin',30.9,'3637262728292',11.00,1000), 
	('Orangensaft','Albi',1,'3637262428292',2.99,1000), 
	('Das rote Zeug','Zuckerfabrik 1A',0,'3637262428292',14.00,1000), 
	('Brauner Rum','Botucal',32,null,22.00,700),
	('Weißer Rum','Bacardi',36,null,18,800),
	('Tonic Water','Harrys',0,null,8,750),
	('Gin','Bombay',34.2,null,16,900),
	('Maracujasaft','Albi',0,null,3.99, 1000),
	('Kokussahne','Thaipeh',0,null,12,350),
	('Triple Sec','Zuckerfabrik 1A',12,null,14,650),
	('Cocktailkirsche','Zuckerfabrik 1A',0,null,12,1000),
	('Tequilla','Sierra',32,null,8.99,750),
	('Das grüne Zeug','Zuckerfabrik 1A',1,'3637262418292',12,1000);

insert into drinks (name, typ, preis) values 
	('Vodka Orange','Longrink',8.00), 
	('Zombie','Longrink',12.00), 
	('Gin Tonic','Longrink',8.00), 
	('Hurricane','Longrink',8.00), 
	('Tequilla Sunrise','Longrink',6.99);

insert into rezepte (name, hersteller, menge, drink) values 
	('Vodka','Jelzin',20,'Vodka Orange'), 
	('Orangensaft','Albi',130,'Vodka Orange'), 
	('Tequilla','Sierra',30,'Tequilla Sunrise'),
	('Das rote Zeug','Zuckerfabrik 1A',10,'Tequilla Sunrise'),
	('Orangensaft','Albi',170,'Tequilla Sunrise'),
	('Brauner Rum','Botucal',20,'Zombie'),
	('Weißer Rum','Bacardi',20,'Zombie'),
	('Maracujasaft','Albi',80,'Zombie'),
	('Orangensaft','Albi',80,'Zombie'),
	('Gin','Bombay',30,'Gin Tonic'),
	('Tonic Water','Harrys',170,'Gin Tonic'),
	('Triple Sec','Zuckerfabrik 1A',20,'Hurricane'),
	('Das grüne Zeug','Zuckerfabrik 1A',5,'Hurricane'),
	('Das rote Zeug','Zuckerfabrik 1A',5,'Hurricane'),
	('Weißer Rum','Bacardi',20,'Hurricane'),
	('Orangensaft','Albi',120,'Hurricane');


-- alles was drin ist
Select 
	drinks.name as Drink,
	drinks.preis as Preis,
	zutaten.name as Zutat,
	rezepte.menge as Menge,
	zutaten.alk as Alkoholgehalt,
	zutaten.preis as 'Preis pro Gebinde',
	zutaten.gebinde as Gebindegröße
from 
	drinks 
	inner join rezepte on rezepte.drink=drinks.name 
	inner join zutaten on zutaten.hersteller=rezepte.hersteller and zutaten.name = rezepte.name
order by 
	zutaten.name;

-- Anzahl der Zutaten die verwendet werden
Select 
	count(distinct rezepte.Name) as 'Verwendete Zutaten'
from 
	rezepte

-- Anzahl der Zutaten die pro drink verwendet werden
Select 
	rezepte.drink,
	count(*) as 'Anzahl Zutaten'
from 
	rezepte
group by drink
having count(*) > 2
order by count(*) desc

-- jetzt mal die Größe der Drinks
Select 
	drinks.name as Drink,
	sum(rezepte.menge) as Inhalt
from 
	drinks 
	join rezepte on rezepte.drink=drinks.name 
	join zutaten on zutaten.hersteller=rezepte.hersteller and zutaten.name = rezepte.name
group by drinks.name;

-- wieviel von den zutaten steckt durchschnittlich in den Drinks?
Select 
	zutaten.name as Zutat,
	avg(rezepte.menge) as 'Durchschnittliche Menge'
from 
	drinks 
	join rezepte on rezepte.drink=drinks.name 
	join zutaten on zutaten.hersteller=rezepte.hersteller and zutaten.name = rezepte.name
group by zutaten.name;

-- höchster Alkoholgehalt
select 
	zutaten.name
from 
	zutaten
where alk=(
	select 
		max(zutaten.alk)
	from zutaten)

-- Alkoholgehalt der Cocktails und bester Deal
select
	drinks.name,
	drinks.preis,
	(100/sum(rezepte.menge))*sum(rezepte.menge*(zutaten.alk/100)) as Alkoholgehalt,
	sum(rezepte.menge*(zutaten.alk/100))/drinks.preis as 'ml Alk pro Euro',
	sum(rezepte.menge*(zutaten.preis/zutaten.gebinde)) as Materialkosten,
	drinks.preis-sum(rezepte.menge*(zutaten.preis/zutaten.gebinde)) as Deckungsbeitrag,
	((drinks.preis-sum(rezepte.menge*(zutaten.preis/zutaten.gebinde)))/drinks.preis) as Grenzdeckungsbeitrag
from 
	drinks 
	join rezepte on rezepte.drink=drinks.name 
	join zutaten on zutaten.hersteller=rezepte.hersteller and zutaten.name = rezepte.name
group by drinks.name, drinks.preis
order by sum(rezepte.menge*(zutaten.alk/100))/drinks.preis DESC;


-- Die besten drei Cocktails mit dem Top-K Pattern 
select
	drinks.name,
	drinks.preis,
	(100/sum(rezepte.menge))*sum(rezepte.menge*(zutaten.alk/100)) as Alkoholgehalt,
	sum(rezepte.menge*(zutaten.alk/100))/drinks.preis as 'ml Alk pro Euro',
	(	select count(*) as anz from 
		(	select d2.name as anz
			from drinks as d2
				join rezepte as r2 on r2.drink=d2.name 
				join zutaten as z2 on z2.hersteller=r2.hersteller and z2.name = r2.name
			group by d2.name, d2.preis
			having sum(r2.menge*(z2.alk/100))/d2.preis >= sum(rezepte.menge*(zutaten.alk/100))/drinks.preis
		) as Rangfunktion 
	) as 'Rang'
from 
	drinks 
	join rezepte on rezepte.drink=drinks.name 
	join zutaten on zutaten.hersteller=rezepte.hersteller and zutaten.name = rezepte.name
group by drinks.name, drinks.preis
having 
	(	select count(*) as anz from 
		(	select d2.name as anz
			from drinks as d2
				join rezepte as r2 on r2.drink=d2.name 
				join zutaten as z2 on z2.hersteller=r2.hersteller and z2.name = r2.name
			group by d2.name, d2.preis
			having sum(r2.menge*(z2.alk/100))/d2.preis >= sum(rezepte.menge*(zutaten.alk/100))/drinks.preis
		) as Rangfunktion 
	)<=3
order by sum(rezepte.menge*(zutaten.alk/100))/drinks.preis DESC;


-- Die besten drei Cocktails mit dem Top-K Ausdruck
select top(3)
	drinks.name,
	drinks.preis,
	(100/sum(rezepte.menge))*sum(rezepte.menge*(zutaten.alk/100)) as Alkoholgehalt,
	sum(rezepte.menge*(zutaten.alk/100))/drinks.preis as 'ml Alk pro Euro'
from 
	drinks 
	join rezepte on rezepte.drink=drinks.name 
	join zutaten on zutaten.hersteller=rezepte.hersteller and zutaten.name = rezepte.name
group by drinks.name, drinks.preis
order by sum(rezepte.menge*(zutaten.alk/100))/drinks.preis DESC;

/*
drop table rezepte;
drop table zutaten;
drop table drinks;
*/

