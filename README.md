# Accounting Approval Status
Accounting Approval Status  application for Emlakjet assignment project.
Contains REST api's to process bill and get approved and denied bills.

Technologies and Tools Used:
 - Java 11
 - Spring Boot 2.4.3
 - Lombok for lots of useful annotations
 - DevTools for Live Reload
 - Maven for dependency management
 - MySql as database

Requirements:
 - JDK 11
 - Maven
 - Mysql Server

## Compile and Run:
Change application yml file(db settings and server port) for your environment first. 

Settings file; src/main/resources/application.yml

### To compile;
mvn clean install

After successful build, you will find jar file with the path; target/accountingapproval-1.0.0-SNAPSHOT.jar

### To run;
java -jar target/accountingapproval-1.0.0-SNAPSHOT.jar