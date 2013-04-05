LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/gender.txt'
INTO TABLE gender
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(gender_id, gender_code, gender_name);

