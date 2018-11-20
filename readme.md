#Lucrez Ceva

## Setup IntelliJ

* Install *Lombok plugin* (https://projectlombok.org/): 
  * Go to *File* > *Settings...* > *Plugins*
  * Click on *Browse repositories...*
  * Search for Lombok Plugin
  * Click on *Install plugin*
  
* Enable Annotation processing:
  * Go to *File* > *Settings...* > *Build, Execution, Deployment* > *Compiler* > *Annotation Processors*
  * Check the checkbox *Enable annotation processing*
  * Click *OK*
  
##!!! INSTALL MARIADB !!!
  
* Set the database:
    1. Replace "spring.jpa.hibernate.ddl-auto=update" =>
       "spring.jpa.hibernate.ddl-auto=create-drop"
    2. Press ctrl + tab + D
    3. Delete existing connection
    4. Create a new one for MariaDB
    5. Create test schema
    6. Respect application.properties
    7. Test connection
    8. open /utils/init.sql and run it!
    
    