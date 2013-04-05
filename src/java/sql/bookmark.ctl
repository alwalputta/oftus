LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/bookmark.txt'
INTO TABLE bookmark
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(bookmark_id, bookmark_name, hiper_link);

