# Build a JAR File
FROM maven:3.6.3-jdk8-slim AS build
WORKDIR /home/app
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean package

# Create an Image
FROM openjdk:8-jdk-alpine
EXPOSE 5000
COPY --from=build /home/app/target/scs-java8-1-0.jar
ENTRYPOINT ["sh", "-c", "java -jar /scs-java8-1-0.jar"]