create table zutaten(
	name varchar(200),
	hersteller varchar(200),
	alk decimal(3,0) default 100.0,
	ean_code char(13),
	primary key (name,hersteller),
	CONSTRAINT unique_index UNIQUE(name, ean_code),/* mal zum ausprobieren */
	CONSTRAINT alk_macht_sinn CHECK (alk<=100 and alk>0)
);

create table drinks(
	name varchar(200),
	typ varchar(200) null,
	erstellt timestamp,
	primary key (name)
);

create table rezepte(
	name varchar(200) not null,
	hersteller varchar(200) not null,
	menge decimal(3,0),
	drink varchar(200),
	/*
	foreign key (drink) references drinks(name),
	foreign key (name,hersteller) references zutaten(name,hersteller)*/

	/*	Wenn wir möchten, dass sich bei Änderung der Primary Keys 
		auch die Foreign Keys ändern: */
		foreign key (drink) references drinks(name) on update cascade,
		foreign key (name,hersteller) references zutaten(name,hersteller) on update cascade
);

alter table drinks add preis decimal(4,2);

insert into zutaten (name, hersteller, alk, ean_code) values 
	('vodka','jelzin',1,'453rfedafsteg'), 
	('vodka2','jelzin',30.9,'3637262728292'), 
	('Orangensaft','Albi',1,'3637262428292'), 
	('Das rote Zeug','Zuckerfabrik 1A',1,'3637262428292'), 
	('Das grüne Zeug','Zuckerfabrik 1A',1,'3637262418292');

delete from zutaten 
where name='vodka2' and hersteller='jelzin';

insert into drinks (name, typ) values 
	('Vodka Orange','Longrink'), 
	('Tequilla Sunrise','Longrink');

insert into rezepte (name, hersteller, menge, drink) values 
	('vodka','jelzin',20,'Vodka Orange'), 
	('Orangensaft','Albi',130,'Vodka Orange'), 
	('Das rote Zeug','Zuckerfabrik 1A',10,'Tequilla Sunrise');

insert into zutaten (name, hersteller, ean_code) 
select name, 'Selbstgemacht', '0000000000000' from drinks;

update zutaten set alk=15.0 where name='Vodka Orange';
update drinks set preis=10;
update drinks set preis=preis*1.15

/*	Geht nur mit "on update cascade":
	Da sonst die referenzielle Integrität verletzt ist*/
	update zutaten set name='juppi' where name='vodka' and hersteller='jelzin';

/*	Zwei Arten, einen join zu realisieren*/
select * from drinks, rezepte, zutaten 
where
	zutaten.hersteller=rezepte.hersteller and
	zutaten.name=rezepte.name and
	rezepte.drink =drinks.name;

select * from drinks d 
	inner join rezepte r on d.name=r.drink 
	inner join zutaten z on r.name=z.name and r.hersteller=z.hersteller;

select distinct hersteller from zutaten;

/*	Felder über die Projektionsliste berechnen*/
select 
	'Fachschaft' as Bar, 
	name as Name, 
	concat(replace(preis,'.',','), ' Euro') as Preis,
	case 
	when Name like '%Vodka%' then 'Vodka Mixgetränk' 
	when Name like '%Tequilla%' then 'Tequilla Mixgetränk'   
	else 'Sonstiges Getränk'  
	end as Typ 
from 
	drinks 
where 
	preis between 10 and 20 and
	Name like '%Orange' or Name like '%Sun%';

/*	ALL, ANY and SOME */
Select * from rezepte where menge <= all (select menge from rezepte);
Select * from rezepte where menge < any (select menge from rezepte);
Select * from rezepte where menge > some (select menge from rezepte);

/*	UNION (Vereinigung), INTERSECT (Schnitt), EXCEPT (Differenz) */
Select * from rezepte where menge < any (select menge from rezepte)
intersect
Select * from rezepte where menge > some (select menge from rezepte);

/* IN, NOT IN */
Select 
	Name, 
	Hersteller 
from 
	zutaten 
where 
	concat (name, hersteller) not in (select concat(name, hersteller) from rezepte)
	
drop table rezepte;
drop table zutaten;
drop table drinks;