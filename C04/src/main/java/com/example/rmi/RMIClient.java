package com.example.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // conectare la RMI Server pe localhost, port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // cautare serviciu exact cu numele "RMIService"
            RMIInterface stub = (RMIInterface) registry.lookup("RMIService");

            // apelare metoda de pe server
            String response = stub.sayHello();
            System.out.println("✅ Răspuns de la server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
