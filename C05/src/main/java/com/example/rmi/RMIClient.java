package com.example.rmi;

import com.example.rmi.RMIInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIClient {
    public static void main(String[] args) {
        try {
            
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);


            RMIInterface stub = (RMIInterface) registry.lookup("RMIService");

          
            byte[] image = new byte[0]; 
            int zoomFactor = 2; 
            byte[] processedImage = stub.processImage(image, zoomFactor);
            System.out.println("✅ Imagine procesată: " + processedImage.length + " bytes");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
