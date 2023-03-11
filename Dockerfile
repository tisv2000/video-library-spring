FROM openjdk:17
COPY service/target/video-library-2.jar video-library-2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "video-library-2.jar"]
