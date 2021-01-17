FROM maven
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.1:go-offline
COPY . .
RUN mvn -B -e -o -T 1C verify
RUN mvn clean install spring-boot:repackage

FROM openjdk:11
WORKDIR /target
COPY --from=0 /usr/src/app/target/*.jar ./
EXPOSE 8080
CMD java -jar joyC-0.0.1-SNAPSHOT.jar
