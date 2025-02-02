package com.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    void processImage(byte[] imageData) throws RemoteException;
}
