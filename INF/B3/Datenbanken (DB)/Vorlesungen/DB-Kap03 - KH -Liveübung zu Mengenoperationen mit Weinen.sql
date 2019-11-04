create table erzeuger(
	weingut varchar(30) primary key,
	anbaugebiet varchar(30),
	region varchar(30)
);

create table weine(
	weinid int primary key,
	name varchar(30),
	farbe varchar(6),
	jahrgang int,
	weingut varchar(30)
);

insert into erzeuger (weingut, anbaugebiet, region) values 
('Creek','Barossa Valley','South Afrika'),
('Helena','Napa Valley','Kalifornien'),
('Chateau La Rose','Saint-Emilion','Bordeaux'),
('Chateau la Pointe','Pomerol','Bordeaux'),
('Müller','Rheingau','Hessen'),
('Bighorn','Napa Valley','Kaifornien');

insert into weine (weinid,name,farbe,jahrgang,weingut) values 
(1042,'La Rose Grand Cru','rot',1998,'Chateau La Rose'),
(2168,'Creek Shiraz','rot',2003,'Creek'),
(3456,'Zinfandel','rot',2004,'Helena'),
(2171,'Pinot Noir','rot',2001,'Creek'),
(3478,'Pinot Noir','rot',1999,'Helena'),
(4711,'Riesling Reserve','weiß',1999,'Müller'),
(4961,'Chardonnay','weiß',2002,'Bighorn'),
(3423,'Donnerkeil','weiß',1820,'Chateau La Rose');


-- Der älteste Wein
select	*
from	WEINE
where	Jahrgang <= all (    
			select	Jahrgang     
			from	WEINE
			)

-- Alle Weingüter die Rotweine produzieren
select	*
from	ERZEUGER
where	Weingut = any (    
			select	Weingut     
			from	WEINE    
			where	Farbe = 'rot')

-- Die ältesten Weine von den Weingütern die Rotwein produzieren
select	*
from	WEINE as W
where	Jahrgang <= all (    
			select	W2.Jahrgang     
			from	WEINE W2
			where	W2.weingut =w.weingut
			) and weingut in (
						select	Weingut     
						from	WEINE    
						where	Farbe = 'rot')
	
drop table erzeuger;
drop table weine;