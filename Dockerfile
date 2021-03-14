FROM openjdk:11
ADD target/accountingapproval-1.0.0-SNAPSHOT.jar accountingapproval-1.0.0-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "accountingapproval-1.0.0-SNAPSHOT.jar"]