FROM azul/zulu-openjdk-alpine:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} studenthousing-0.2.0.jar
ENTRYPOINT ["java","-jar","/studenthousing-0.2.0.jar"]