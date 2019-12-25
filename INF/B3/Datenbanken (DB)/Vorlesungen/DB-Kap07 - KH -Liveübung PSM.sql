--use Vorlesung_DB;
drop table bestellung;
drop table kunde;

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
	('2018-04-21','Marx Straße 4',1,1),
	('2018-03-11','Lauterweg 12',1,2),
	('2018-04-21','Marx Straße 8',1,10),
	('2018-05-11','Bananengasse 189',2,2),
	('2018-06-03','Lauterweg 12',2,7),
	('2018-07-04','Wie auch immer Straße 9',5,1),
	('2018-02-05','Marx Straße 4',5,1),
	('2018-02-05','Marx Straße 4',5,10),
	('2018-02-05','Marx Straße 4',6,1),
	('2018-02-05','Marx Straße 4',6,1),
	('2018-02-05','Marx Straße 4',7,1),
	('2018-02-05','Marx Straße 4',7,15),
	('2018-02-05','Marx Straße 4',3,1),
	('2018-02-05','Marx Straße 4',4,1),
	('2018-03-06','Marx Straße 4',null,0);

-- In der Vorlesung kam die Frage nach kumulierten Werten. 
-- Beispiel mit with, Kunden die	80% vom Umsatz machen (A Kunden) 
--									95% vom Umsatz machen (B Kunden)
--									Der Rest (C-Kunden)
with 
	ABC_Punkte(A,B,C) as(
		select	sum(bestellwert)*0.8, 
				sum(bestellwert)*0.95,
				sum(bestellwert)
		from bestellung),

	Kundenumsatz(kunde, id, umsatz) as(
		select	kunde.name, 
				kunde.id, 
				isnull(sum(bestellung.bestellwert),0) as umsatz 
		from	kunde left join bestellung on (kunde.id=bestellung.kundennummer)
		group by kunde.name, kunde.id
	),

	ABC_Kunden (kunde, umsatz, kumuliert, typ) as(
		select	K.kunde, 
				K.umsatz, 
				(Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz),
				case 
					when (Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz)<=(select A from ABC_Punkte) then 'A'
					when (Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz)<=(select B from ABC_Punkte) then 'B'
					when (Select sum(V.umsatz) from Kundenumsatz as V where V.umsatz>=K.umsatz)<=(select C from ABC_Punkte) then 'C'
				end
		from	Kundenumsatz as K
	)

select * from ABC_Kunden order by umsatz desc


-- jetzt nutzen wir die Funktionalität von PSM
-- Functions und Procedures, um den zuvor gebastelten Query etwas
-- besser zu gestalten.


-- zuerst die ABS Klassifikation
select dbo.getABClevel('B')


/*
drop function dbo.getABClevel;
create function getABClevel (@value varchar(1))
returns int
as
begin
	declare @Alevel int;
	declare @Blevel int;
	declare @Clevel int;
	declare @returnvalue int;
	select	@Alevel=sum(bestellwert)*0.8, 
			@Blevel=sum(bestellwert)*0.95,
			@Clevel=sum(bestellwert)
	from bestellung;
	set @returnvalue = case 
		when @value = 'A'	then @Alevel
		when @value = 'B'	then @Blevel
		when @value = 'C'	then @Clevel
		else						0
	end;
	return @returnvalue;
end;


*/

-- dann der Umsatz eines Kunden als Funktion
/*
drop function dbo.getKundenumsatz;
create function getKundenumsatz (@kundenID int)
returns int
as
begin
	declare @returnvalue int;
	select		@returnvalue=isnull(sum(bestellung.bestellwert),0) 
	from		kunde 
				left join bestellung on (kunde.id=bestellung.kundennummer)
	where		kunde.id=@kundenID;
	return @returnvalue;
end;
*/

-- Dann der kumulierte Umsatz aller Kunden die mehr 
-- Umsatz gemacht haben als der angegebene Kunde
/*
drop function dbo.getKumulierterUmsatz;
create function getKumulierterUmsatz (@kundenID int)
returns int
as
begin
	declare @returnvalue int;
	select	@returnvalue=sum(dbo.getKundenumsatz(id))
	from	kunde
	where	dbo.getKundenumsatz(id) >= dbo.getKundenumsatz(@kundenID)
	return @returnvalue;
end;
*/

-- Alternative Implementierung mit Cursor für
-- getKumulierterUmsatz
/*
drop function dbo.getKumulierterUmsatz;
create function getKumulierterUmsatz (@kundenID int)
returns int
as begin
	declare @returnvalue int;
	set @returnvalue=0;
	declare @tempvalue int;
	set @tempvalue=0;
	declare KundenCursor cursor for select dbo.getKundenumsatz(kunde.id) from Kunde where dbo.getKundenumsatz(kunde.id)>=dbo.getKundenumsatz(@kundenID);
	open KundenCursor;
	fetch next from KundenCursor into @tempvalue;
	while @@FETCH_STATUS = 0  
		begin
			set @returnvalue=@returnvalue+ isnull(@tempvalue,0);
			fetch next from KundenCursor into @tempvalue;
		end;
	close KundenCursor; 
	deallocate KundenCursor; 
	return @returnvalue;
end;
*/

-- Dann die ABS Klassifikation des Kunden
/*
drop function dbo.getCustomerClassification;
create function getCustomerClassification (@kundenID int)
returns char
as
begin
	declare @returnvalue char;
	set @returnvalue = 
	case
		when dbo.getKumulierterUmsatz(@kundenID)<=dbo.getABClevel('A') then 'A'
		when dbo.getKumulierterUmsatz(@kundenID)<=dbo.getABClevel('B') then 'B'
		when dbo.getKumulierterUmsatz(@kundenID)<=dbo.getABClevel('C') then 'C'
	end;
	return @returnvalue;
end;
select *, dbo.getCustomerClassification(id) from kunde
*/

-- Und jetzt zusammen mit ID
/*
drop procedure getCustomerClassificationAndDetails;
create procedure getCustomerClassificationAndDetails
	@kundenID int,
	@name varchar (30) output,
	@email varchar (30) output,
	@klassifikation char output
as
begin
	set @klassifikation = dbo.getCustomerClassification(@kundenID);
	select @name=name, @email=email from kunde where kunde.id=@kundenID;
end;

declare @kunde int;
declare @email varchar (30);
declare @name varchar(30);
declare @klassifikation char;


execute		getCustomerClassificationAndDetails
			1, 
			@email=@email output, 
			@name=@name output,
			@klassifikation = @klassifikation output;

select  @name,@email,@klassifikation;
*/

-- Kleine Einleitung zu Cursorn, zähle Kunden
/*
drop function zaehleAnzahlKunden;
create function zaehleAnzahlKunden()
returns varchar(2000)
as
begin
	declare @anzahl int;
	set @anzahl=0;
	declare @kundenID int;
	set @kundenID =0;
	declare @kundenName varchar(200);
	set @kundenName='';
	declare @returnString varchar(2000);
	set @returnString='';

	declare CursorKunden cursor for select id, name from Kunde 
	open CursorKunden;
	fetch next from CursorKunden into @kundenID, @kundenName;
	while @@FETCH_STATUS = 0  
		begin
			set @anzahl=@anzahl+1;
			set @returnString+=@kundenName+', '
			fetch next from CursorKunden into @kundenID, @kundenName;
		end;
	close CursorKunden; 
	deallocate CursorKunden; 
	return 'Es gibt '+cast(@anzahl as varchar)+' Kunden, und zwar: '+@returnString;
end;

select dbo.zaehleAnzahlKunden()
*/

-- Adventsübung Weihnachtspost
/*
drop function erzeugeWeihnachtspostFuerAKunden;
create function erzeugeWeihnachtspostFuerAKunden()
returns varchar(max)
as
begin
	declare @anzahl int;
	set @anzahl=0;
	declare @kundenId int;
	set @kundenId=0;
	declare @kundenEmail varchar(200);
	set @kundenEmail='';
	declare @kundenName varchar(200);
	set @kundenName='';
	declare @returnString varchar(2000);
	set @returnString='';

	declare CursorKunden cursor for select id, email, name from Kunde 
	open CursorKunden;
	fetch next from CursorKunden into @kundenId, @kundenEmail, @kundenName;
	while @@FETCH_STATUS = 0  
		begin
			if dbo.getCustomerClassification(@kundenId) = 'A'
			begin
				set @returnString+='Mailto:'+@kundenEmail+' Liebe '+@kundenName+',<br>Frohe Weihnachten und besten Dank! '
			end;
			fetch next from CursorKunden into @kundenID, @kundenEmail, @kundenName;
		end;
	close CursorKunden;
	deallocate CursorKunden; 
	return @returnString;
end;

select dbo.erzeugeWeihnachtspostFuerAKunden()
*/


