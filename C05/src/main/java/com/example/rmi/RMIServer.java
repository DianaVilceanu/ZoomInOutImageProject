package com.example.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class RMIServer extends UnicastRemoteObject implements RMIInterface {

    protected RMIServer() throws RemoteException {
        super();
    }

    @Override
    public byte[] processImage(byte[] imageData, int zoomFactor) throws RemoteException {
        System.out.println("Received image with size: " + imageData.length + " bytes. Zoom factor: " + zoomFactor);
        
        // Simulare procesare imagine 
        byte[] processedImage = Arrays.copyOf(imageData, imageData.length); 
        System.out.println("Processing complete, returning processed image.");
        
        return processedImage;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); 
            RMIServer server = new RMIServer();
            Naming.rebind("rmi://localhost:1099/RMIServer2", server);
            System.out.println("RMI Server 2 is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
