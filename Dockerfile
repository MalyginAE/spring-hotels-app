#FROM maven:3.8.5-openjdk-17
#WORKDIR /usr/src/app
#COPY . .
#RUN mvn clean spring-boot:run
##RUN java -jar /usr/src/app/spring-hotels-app/target/spring-hotels-app-0.0.1-SNAPSHOT.jar
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/spring-hotels-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]