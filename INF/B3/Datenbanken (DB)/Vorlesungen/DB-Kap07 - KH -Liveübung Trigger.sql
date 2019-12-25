IF OBJECT_ID (N'alte_drinks', N'U') IS NOT NULL  
DROP TABLE alte_drinks;  
GO  
IF OBJECT_ID (N'rezepte', N'U') IS NOT NULL  
DROP TABLE rezepte;  
GO  
IF OBJECT_ID (N'zutaten', N'U') IS NOT NULL  
DROP TABLE zutaten;  
GO  
IF OBJECT_ID (N'drinks', N'U') IS NOT NULL  
DROP TABLE drinks;  
GO  

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

create table alte_drinks(
	name varchar(30),
	drink varchar(200),
	foreign key (drink) references drinks(name) on update cascade
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

-- jetzt nutzen wir einen Trigger, um einen gelöschten Drink in 
-- eine 'alte drinks' Tabelle zu schieben
if OBJECT_ID ('loescheDrink','TR') IS NOT NULL
   drop trigger loescheDrink;
go
create trigger loescheDrink
		on drinks
		after delete
		as begin
			insert into alte_drinks (name)
			SELECT name
			FROM deleted 
		end;
go

select * from drinks;
delete from drinks where name = 'Zombie'; -- geht nur wegen on cascade delete in der Rezepte Tabelle, siehe oben
select * from alte_drinks;

-- Wie in der Vorlesung gewünscht, habe ich hier mal versucht
-- mutwillig Blödsinnn zu betreiben. Jedes mal, wenn etwas gelöscht wird,
-- wird etwas eingefügt
if OBJECT_ID ('machKaputt1','TR') IS NOT NULL
   drop trigger machKaputt1;
go

create trigger machKaputt1
		on drinks
		after delete
		as begin
			insert into drinks (name) values ('IDDQD')
			 
		end;
go

--..und jedes Mal wenn etwas eingefügt wird, wird etwas gelöscht
if OBJECT_ID ('machKaputt2','TR') IS NOT NULL
   drop trigger machKaputt2;
go

create trigger machKaputt2
		on drinks
		after insert
		as begin
			delete from drinks where name = 'IDDQD'
		end;
go

-- Das erzeugt natürlich einen Loop, der wird aber irgendwann einfach abgebrochen.
-- delete from drinks where name = 'Zombie';








