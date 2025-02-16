package io.github.edsonzuchi.server;

import io.github.edsonzuchi.server.core.RoomManager;
import io.github.edsonzuchi.server.core.impl.RoomManagerImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HotelServer {

    public static void main(String[] args) {
        try {
            RoomManagerImpl managerimpl = new RoomManagerImpl();
            RoomManager manager = (RoomManager) UnicastRemoteObject.exportObject(managerimpl, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Manager", manager);

            System.out.println("Hotel Server started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
