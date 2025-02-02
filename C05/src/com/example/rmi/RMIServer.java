package com.example.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements RMIInterface {

    protected RMIServer() throws RemoteException {
        super();
    }

    @Override
    public void processImage(byte[] imageData) throws RemoteException {
        System.out.println("C05: Received image for processing.");
        // Logică specifică de procesare (simulare)
        System.out.println("C05: Processing completed.");
    }

    public static void main(String[] args) {
        try {
            RMIServer server = new RMIServer();
            Naming.rebind("//0.0.0.0:1099/RMIServer2", server);
            System.out.println("C05: RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
