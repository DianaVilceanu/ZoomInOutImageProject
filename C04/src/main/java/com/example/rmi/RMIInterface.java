package com.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterface extends Remote {
    String sayHello() throws RemoteException;  
}
