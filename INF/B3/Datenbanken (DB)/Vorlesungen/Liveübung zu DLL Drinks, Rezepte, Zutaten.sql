create table zutaten(
	name varchar(100),
	hersteller varchar (100),
	alk int,
	ean_code char(13),
	primary key (name,hersteller)
)

create table drinks(
	name varchar(100) primary key,
	typ varchar (100) null,	
)

create table rezepte(
	name varchar(100),
	hersteller varchar (100),
	menge decimal (3,0),
	drink varchar(100),
	foreign key (drink) references drinks(name),
	foreign key (name,hersteller) references zutaten(name,hersteller) 
)

insert into zutaten (name,hersteller,alk,ean_code) values('Tequilla','Sierra',10,'5463746353423')
insert into zutaten (name,hersteller,alk,ean_code) values('Tequilla','Sierra',100,'5463746353423')

select * from zutaten;

drop table rezepte;
drop table drinks;
drop table zutaten;
