FROM openjdk:8
ADD target/conichi-spring-boot.jar conichi-spring-boot.jar
VOLUME /tmp
EXPOSE 8081
ENTRYPOINT ["java","-jar","conichi-spring-boot.jar"]