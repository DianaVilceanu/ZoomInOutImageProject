package com.example.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements RMIInterface {

    protected RMIServer() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, client!";
    }

    public static void main(String[] args) {
        try {
            
            Registry registry = LocateRegistry.createRegistry(1099);

          
            RMIServer server = new RMIServer();
            registry.rebind("RMIService", server);

            System.out.println("RMI Server is running on port 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
