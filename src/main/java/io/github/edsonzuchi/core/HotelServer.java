package io.github.edsonzuchi.core;

import io.github.edsonzuchi.core.impl.RoomManagerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HotelServer {
    public static void main(String[] args) throws RemoteException {
        try {
            RoomManager manager = new RoomManagerImpl();
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.rebind("HotelManager", manager);
            System.out.println("Hotel Server started");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
