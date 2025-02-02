FROM critoma/linux-u20-dev-security-ism

WORKDIR /usr/local


RUN apt update && apt install -y openjdk-21-jdk wget unzip \
    && wget https://dlcdn.apache.org/tomee/tomee-10.0.0/apache-tomee-10.0.0-microprofile.zip -O tomee.zip \
    && unzip tomee.zip \
    && mv apache-tomee-* tomee \
    && rm tomee.zip

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app


COPY target/C03-0.0.1-SNAPSHOT.war /usr/local/tomee/webapps/C03.war

RUN chmod +x /usr/local/tomee/bin/catalina.sh

EXPOSE 8080 1099


CMD ["/usr/local/tomee/bin/catalina.sh", "run"]
