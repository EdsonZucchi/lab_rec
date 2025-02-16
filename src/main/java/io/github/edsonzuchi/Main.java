package io.github.edsonzuchi;

import io.github.edsonzuchi.server.core.RoomManager;
import io.github.edsonzuchi.server.core.impl.RoomManagerImpl;

import java.rmi.RemoteException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws RemoteException {
        System.out.println("Hello world!");

        RoomManager manager = new RoomManagerImpl();

        List<String> list;

        list = manager.listRooms();

        for (String line : list) {
            System.out.println(line);
        }

        manager.bookRoom(3, "Edson L Zucchi");
        manager.bookRoom(3, "Edson L Zucchi");
        manager.bookRoom(3, "Edson L Zucchi");
        manager.bookRoom(2, "Edson L Zucchi");
        manager.bookRoom(1, "Edson L Zucchi");
        manager.bookRoom(4, "Edson L Zucchi");
        manager.bookRoom(2, "Edson L Zucchi");

        list = manager.listGuests();

        for (String line : list) {
            System.out.println(line);
        }

        list = manager.listRooms();

        for (String line : list) {
            System.out.println(line);
        }
    }
}