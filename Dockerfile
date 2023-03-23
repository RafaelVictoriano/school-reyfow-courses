FROM openjdk:19-oracle
WORKDIR /app
ADD target/school-reyfow-courses*.jar app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app"]