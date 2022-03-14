FROM openjdk:8-alpine
ADD target/msexchangerate-0.0.1-SNAPSHOT.jar /usr/share/msexchangerate-0.0.1-SNAPSHOT.jar
EXPOSE  8085
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/msexchangerate-0.0.1-SNAPSHOT.jar"]