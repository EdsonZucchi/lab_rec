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

    /* Na abertura da classe abre as listas e cria dos quartos*/
    public RoomManagerImpl() {
        rooms = new ArrayList<>();
        reserves = new ArrayList<>();

        rooms.add(new Room(0, 10, 55.0));
        rooms.add(new Room(1, 20, 75.0));
        rooms.add(new Room(2, 5, 80.0));
        rooms.add(new Room(3, 3, 150.0));
        rooms.add(new Room(4, 2, 230.0));
    }

    /*
    * Pega de lista de quarto e printa as informações, deixado a mensagem pronta no metodo toString do room
    * */
    @Override
    public List<String> listRooms() {
        List<String> list = new ArrayList<>();

        for (Room room : rooms) {
            list.add(room.toString());
        }

        return list;
    }

    /*
    * Realiza a reserva do quarto
    * */
    @Override
    public boolean bookRoom(int type, String nameGuest) throws RemoteException {
        /*Busca o endereço do quarto dentro da list */
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

        /* Diminui a quantidade de quartos disponivel */
        room.decreaseQuantity(1);

        /* Cria a reserva e salva na list */
        reserves.add(new Reserve(type, nameGuest));

        return true;
    }

    /*
    * Lista as reservas
    * */
    @Override
    public List<String> listGuests() {
        List<String> list = new ArrayList<>();
        Integer beforeType = null;

        /* Ordena a lista com base no tipo para realizar a quebra na listagem */
        reserves.sort(Comparator.comparingInt(Reserve::getType));

        list.add("Nomes dos hospedes:");
        for (Reserve reserve : reserves) {
            if (beforeType == null || reserve.getType() != beforeType) {
                /* Caso for a primeira vez ou troque de tipo prita o topo */
                list.add("  Quarto tipo "+reserve.getType() + ":");
                beforeType = reserve.getType();
            }
            list.add("      -"+reserve.getName());
        }

        return list;
    }
}
