package com.example.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

import com.example.rmi.RMIInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.nio.file.Files;
import java.nio.file.Path;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "imageTopic")
})
public class ImageProcessingMDB implements MessageListener {
    public void onMessage(Message message) {
        if (message instanceof BytesMessage) {
            try {
                BytesMessage bytesMessage = (BytesMessage) message;
                byte[] imageData = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(imageData);

                Path tempFile = Files.createTempFile("processed", ".bmp");
                Files.write(tempFile, imageData);

                // Trimitem la C04 și C05 pentru procesare
                RMIInterface server1 = (RMIInterface) Naming.lookup("rmi://c04-rmi-server/ZoomService");
                RMIInterface server2 = (RMIInterface) Naming.lookup("rmi://c05-rmi-server/ZoomService");

                byte[] zoomedData = server1.processImage(imageData);  // Apel RMI
                Files.write(tempFile, zoomedData);

                // Inserăm în MySQL (C06)
                saveToDatabase(zoomedData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToDatabase(byte[] imageData) {
        // Implementare MySQL insert BLOB (C06)
    }
}
