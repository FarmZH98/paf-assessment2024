-- Task 1
-- Write SQL statements in this file to create the brewery database and 
-- populate the database with the given SQL files
drop database if exists pafAssessment;

CREATE database pafAssessment;

use pafAssessment;

@@beers.sql

@@breweries.sql

@@categories.sql

@@geocodes.sql

@@styles.sql

grant all privileges on pafAssessment.* to 'fred'@'localhost';
flush privileges;