LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/category.txt'
INTO TABLE category
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(category_id, category_name);

