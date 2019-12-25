create table produkte(
p_id int,
name varchar(20)
)

create table produkt_eigenschaften (
p_id int,
e_id int
);

create table gesuchte_eigenschaften (
e_id int);

insert into produkte (name,p_id) values ('Computer',1), ('Maus',2), ('Kabel',3);
insert into produkt_eigenschaften (p_id,e_id) values (1,2),(1,3),(1,4),(2,1),(2,3),(3,3),(3,2);
insert into gesuchte_eigenschaften (e_id) values (2),(3);

--
-- Hier die Division Produkteigenschaften geteilt durch gesuchte Eigenschaften
--
select p_id from produkt_eigenschaften -- alle Produkte

except -- außer die Produkte die die Eigenschaften nicht erfüllen

select eig_nicht_erfuellt.p_id
from (								
		select  alle_produkte.p_id, gesuchte_eigenschaften.e_id											-- Alle Kombinationen 
		from	(												-- Alle möglichen Kombinationen			-- von Produkten
					select p_id from produkt_eigenschaften		-- Von Eigenschaften und Produkten		-- und Eigenschaften
				) as alle_produkte, gesuchte_eigenschaften		-- die es geben kann					-- die es 
		except																							-- nicht
		select p_id, e_id from produkt_eigenschaften													-- gibt
	 ) as eig_nicht_erfuellt;

-- Jetzt mal mit WITH, was das ganze etwas übersichtlicher macht.
with 
alle_produkte(p_id) as (
	select p_id 
	from produkte
),
alle_moeglichen_kombinationen(p_id,e_id) as(
	select  alle_produkte.p_id, gesuchte_eigenschaften.e_id									
	from	alle_produkte, gesuchte_eigenschaften	
),
die_eigenschaften(p_id,e_id) as (
	select p_id, e_id 
	from produkt_eigenschaften
)
select * from alle_produkte 
except
select p_id
from (	
		select * from alle_moeglichen_kombinationen
		except 
		select * from die_eigenschaften
	 ) as e;

/*
drop table produkt_eigenschaften;
drop table gesuchte_eigenschaften;
drop table produkte;
*/
