package io.github.edsonzuchi.server.core.impl;

import io.github.edsonzuchi.server.core.RoomManager;
import io.github.edsonzuchi.server.core.entity.Reserve;
import io.github.edsonzuchi.server.core.entity.Room;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RoomManagerImpl implements RoomManager {

    private final List<Room> rooms;
    private final List<Reserve> reserves;

    public RoomManagerImpl() {
        rooms = new ArrayList<>();
        reserves = new ArrayList<>();

        rooms.add(new Room(0, 10, 55.0));
        rooms.add(new Room(1, 20, 75.0));
        rooms.add(new Room(2, 5, 80.0));
        rooms.add(new Room(3, 3, 150.0));
        rooms.add(new Room(4, 2, 230.0));
    }

    @Override
    public List<String> listRooms() {
        List<String> list = new ArrayList<>();

        for (Room room : rooms) {
            list.add(room.toString());
        }

        return list;
    }

    @Override
    public boolean bookRoom(int type, String nameGuest) throws RemoteException {
        Optional<Room> roomType = rooms.stream()
                .filter(room -> room.getType() == type)
                .findFirst();

        if (roomType.isEmpty()) {
            throw new RemoteException("Type room invalid");
        }
        if (nameGuest.isBlank()) {
            throw new RemoteException("Name guest is not blank");
        }

        Room room = roomType.get();
        if (room.getQuantity() <= 0) {
            throw new RemoteException("No rooms available for this type");
        }

        room.decreaseQuantity(1);
        reserves.add(new Reserve(type, nameGuest));

        return true;
    }

    @Override
    public List<String> listGuests() {
        List<String> list = new ArrayList<>();
        Integer beforeType = null;
        reserves.sort(Comparator.comparingInt(Reserve::getType));

        list.add("Nomes dos hóspedes:");
        for (Reserve reserve : reserves) {
            if (beforeType == null || reserve.getType() != beforeType) {
                list.add("  Quarto tipo "+reserve.getType() + ":");
                beforeType = reserve.getType();
            }
            list.add("      -"+reserve.getName());
        }

        return list;
    }
}
