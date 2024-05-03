-- Task 1
-- Write SQL statements in this file to create the brewery database and 
-- populate the database with the given SQL files
drop database if exists pafAssessment;

CREATE database pafAssessment;

use pafAssessment;

source beers.sql

source breweries.sql

source categories.sql

source geocodes.sql

source styles.sql

grant all privileges on pafAssessment.* to 'fred'@'localhost';
flush privileges;