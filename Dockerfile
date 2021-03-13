FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/my-application.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


COPY ./target/accountingapproval-1.0.0.-SNAPSHOT.jar accountingapproval-1.0.0.-SNAPSHOT.jar
CMD ["java","-jar","accountingapproval-1.0.0.-SNAPSHOT.jar"]