LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/usercategory.txt'
INTO TABLE usercategory
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(user_id, category_id);

