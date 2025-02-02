package com.example.javalin;

import io.javalin.Javalin;
import io.javalin.http.UploadedFile;
import io.javalin.http.staticfiles.Location;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class App {
    private static final String JMS_BROKER_URL = "tcp://c02-jms-broker:61616";
    private static final String JMS_TOPIC_NAME = "imageTopic";

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
        	config.staticFiles.add("/app/public", Location.EXTERNAL);
        }).start(8080);

        app.get("/", ctx -> ctx.result("Server is running!"));
        app.post("/upload", ctx -> {
            UploadedFile uploadedFile = ctx.uploadedFile("image");
            if (uploadedFile == null) {
                ctx.status(400).result("No file uploaded");
                return;
            }
            Path tempFile = Files.createTempFile("uploaded", ".bmp");
            try (InputStream input = uploadedFile.content()) {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
                publishImage(tempFile);
                ctx.result("File uploaded and sent to processing queue.");
            } catch (IOException e) {
                ctx.status(500).result("Error saving file");
            }
        });
    }

    private static void publishImage(Path filePath) {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(JMS_BROKER_URL);
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(JMS_TOPIC_NAME);
            MessageProducer producer = session.createProducer(topic);
            BytesMessage message = session.createBytesMessage();
            message.writeBytes(Files.readAllBytes(filePath));
            producer.send(message);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
