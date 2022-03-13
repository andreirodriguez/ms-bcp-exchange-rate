FROM openjdk:8-alpine
ADD target/msexchangerate-1.0.jar /usr/share/msexchangerate-1.0.jar
EXPOSE  8085
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/msexchangerate-1.0.jar"]