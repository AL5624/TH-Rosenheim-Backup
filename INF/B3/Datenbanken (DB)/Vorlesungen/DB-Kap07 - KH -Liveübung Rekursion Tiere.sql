create table tier_frisst(
	tier varchar(30),
	frisst varchar (30)
);

insert into tier_frisst (tier, frisst) values
	('Affe','Banane'),
	('Affe','Apfel'),
	('Affe','Frosch'),
	('Frosch','Fliege'),
	('Fliege','Apfel'),
	('Fliege','Zuckerwasser'),
	('Schakal','Affe'),
	('Wolf','Schakal'),
	('Wolf','Apfel');

with 
	wer_frisst_was(Tier,Frisst) as (

		-- Rekursionsanfang	
		Select	tier, frisst 
		from tier_frisst
		
		union all
		
		-- Rekursionschritt
		select wer_frisst_was.Tier, tier_frisst.frisst
		from wer_frisst_was, tier_frisst
		where wer_frisst_was.Frisst = tier_frisst.tier
		)

select distinct * from wer_frisst_was

drop table tier_frisst;