
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
Change application.yml file(db settings and server port) for your environment first. You can also set maxAmountLimit.

    Settings file; src/main/resources/application.yml  

### To compile;
    mvn clean install  
After successful build, you will find jar file with the path; target/accountingapproval-1.0.0-SNAPSHOT.jar

### To run;
    java -jar target/accountingapproval-1.0.0-SNAPSHOT.jar

## Postman Collection Path:

    AccountingBillApprovalStatus.postman_collection.json

## Curl Commands:
### Add Bill Request:

    curl --location --request POST 'localhost:8081/' \  
    --header 'Content-Type: application/json' \  
    --data-raw '{  
        "firstName": "Muhammed",  
        "lastName": "Gultekin",  
        "email": "gultekinmsg@gmail.com",  
        "amount": "21",  
        "productName": "USB",  
        "billNo": "TR01"  
    }'  

### Get Denied Bills:

    curl --location --request GET 'localhost:8081?status=DENIED'  

### Get Approved Bills:

    curl --location --request GET 'localhost:8081?status=APPROVED'
