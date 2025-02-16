package io.github.edsonzuchi.core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RoomManager extends Remote {

    List<String> listRooms() throws RemoteException;
    boolean bookRoom(int type, String nameGuest) throws RemoteException;
    List<String> listGuests() throws RemoteException;
}
