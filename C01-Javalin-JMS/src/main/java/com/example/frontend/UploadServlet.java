package com.example.frontend;

import java.io.IOException;
import java.io.InputStream;
import javax.jms.*;
import javax.naming.InitialContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;


@MultipartConfig
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Part filePart = request.getPart("image");
        InputStream fileContent = filePart.getInputStream();

        try {
            InitialContext ctx = new InitialContext();
            ConnectionFactory factory = (ConnectionFactory) ctx.lookup("java:comp/env/jms/ConnectionFactory");
            Destination destination = (Destination) ctx.lookup("java:comp/env/jms/ImageTopic");

            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);

            BytesMessage message = session.createBytesMessage();
            byte[] imageData = fileContent.readAllBytes();
            message.writeBytes(imageData);

            producer.send(message);

            session.close();
            connection.close();

            response.getWriter().write("{\"message\":\"Image sent to processing\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
