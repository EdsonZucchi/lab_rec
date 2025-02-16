package io.github.edsonzuchi.client;

import io.github.edsonzuchi.server.core.RoomManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HotelClient {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("list <address>: liste o número disponível de quartos em cada  faixa de preço.");
            System.out.println("book <address> <type room> <name guest>: liste o número disponível de quartos em cada faixa de preço.");
            System.out.println("clients <address>: lista os nomes de todos os convidados registrados em cada tipo de quarto");
            return;
        }

        try {
            String address = args[1];
            String action = args[0];

            Registry registry = LocateRegistry.getRegistry(address);
            RoomManager manager = (RoomManager) registry.lookup("Manager");

            switch (action) {
                case "list":
                    for (String info : manager.listRooms()) {
                        System.out.println(info);
                    }
                    return;
                case "book":
                    if (args.length < 4) {
                        System.out.println("Uso: book <address> <type room> <name guest>");
                        return;
                    }
                    int type = Integer.parseInt(args[2]);
                    String name = args[3];
                    try {
                        if (manager.bookRoom(type, name)) {
                            System.out.println("Reserva realizada");
                        } else {
                            System.out.println("Reserva não realizada");
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    return;
                case "clients":
                    for (String info : manager.listGuests()) {
                        System.out.println(info);
                    }
                    return;
                default:
                    System.out.println("Comando inválido: " + action);
            }
        }catch (Exception e) {
            System.out.println("Comando inválido");
            e.printStackTrace();
        }
    }
}
