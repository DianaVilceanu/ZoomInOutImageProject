FROM critoma/linux-u20-dev-security-ism
WORKDIR /app
RUN apt-get update && apt-get install -y openjdk-21-jdk

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

COPY src/main/webapp /app/public

COPY target/C01-Javalin-JMS-0.0.1-SNAPSHOT.jar /app/C01-Javalin-JMS.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/C01-Javalin-JMS.jar"]
