FROM critoma/linux-u20-dev-security-ism

WORKDIR /app


RUN apt update && apt install -y openjdk-21-jdk

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

COPY target/C05-0.0.1-SNAPSHOT.jar /app/C05-RMI-Server.jar

EXPOSE 1099

CMD ["java", "-jar", "/app/C05-RMI-Server.jar"]
