LOAD DATA LOCAL INFILE '/Users/palwal/Documents/NetBeansProjects/myapp/src/java/sql/state.txt'
INTO TABLE state
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
(state_id, state_code, state_name);

