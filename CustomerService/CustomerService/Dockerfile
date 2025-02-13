FROM openjdk:17-jdk-slim

LABEL "org.opencontainers.image.authors"="Online Food Order App"

COPY target/CustomerService-0.0.1-SNAPSHOT.jar CustomerService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "CustomerService-0.0.1-SNAPSHOT.jar"]