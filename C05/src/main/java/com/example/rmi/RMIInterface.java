package com.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    byte[] processImage(byte[] imageData, int zoomFactor) throws RemoteException;
}
