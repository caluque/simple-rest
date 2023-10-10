FROM maven:3.9.4-eclipse-temurin-17
EXPOSE 8080
COPY . .
CMD [ "mvn", "spring-boot:run" ]