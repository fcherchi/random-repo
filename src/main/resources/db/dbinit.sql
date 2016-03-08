CREATE SCHEMA lunatest;

CREATE TABLE COUNTRY AS SELECT * FROM CSVREAD('src/main/resources/input-data/countries.csv');
CREATE TABLE AIRPORT AS SELECT * FROM CSVREAD('src/main/resources/input-data/airports.csv');
CREATE TABLE RUNWAY AS SELECT * FROM CSVREAD('src/main/resources/input-data/runways.csv');

CREATE INDEX IDX_COUNTRY_CODE ON COUNTRY(CODE);
CREATE INDEX IDX_COUNTRY_NAME ON COUNTRY(NAME);
CREATE INDEX IDX_AIRPORT_CODE ON AIRPORT(ISO_COUNTRY);
CREATE INDEX IDX_RUNWAY_AIRPORT ON RUNWAY(AIRPORT_REF);

CREATE VIEW VIEW_COUNTRY_MAX AS
	     SELECT COUNT (id) as air_count, iso_country FROM airport GROUP BY iso_country;
	  
	-- Type of runways (as indicated in "surface" column) per country

CREATE VIEW VIEW_SURFACE AS
	SELECT distinct r.surface, c.name FROM runway r 
		inner join AIRPORT a on r.airport_ref = a.id
		inner join COUNTRY c on c.code = a.iso_country
	ORDER BY c.name, r.surface
 
