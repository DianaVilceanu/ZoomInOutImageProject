package com.example.rmi;

import java.rmi.Naming;

public class RMIClient {
    public static void processImage(byte[] imageData) {
        try {
            RMIInterface rmiServer1 = (RMIInterface) Naming.lookup("//c04-container:1099/RMIServer1");
            RMIInterface rmiServer2 = (RMIInterface) Naming.lookup("//c05-container:1099/RMIServer2");

            System.out.println("Calling RMI Servers...");
            rmiServer1.processImage(imageData);
            rmiServer2.processImage(imageData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
