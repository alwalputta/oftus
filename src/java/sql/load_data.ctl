LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/gender.txt'
INTO TABLE gender
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(gender_id, gender_code, gender_name);

LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/state.txt'
INTO TABLE state
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(state_id, state_code, state_name);

LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/category.txt'
INTO TABLE category
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(category_id, category_name);

LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/bookmark.txt'
INTO TABLE bookmark
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(bookmark_id, bookmark_name, hiper_link);

LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/categorybookmark.txt'
INTO TABLE categorybookmark
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(category_id, bookmark_id);

LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/usercategory.txt'
INTO TABLE usercategory
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(user_id, category_id);




