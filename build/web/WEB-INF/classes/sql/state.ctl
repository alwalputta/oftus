LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/country.txt'
INTO TABLE country
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(country_id, country_code, country_name);

