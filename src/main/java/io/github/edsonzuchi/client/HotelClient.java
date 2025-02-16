package io.github.edsonzuchi.client;

import io.github.edsonzuchi.server.core.RoomManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HotelClient {
    public static void main(String[] args) {

        /* Printa o help */
        if (args.length == 0) {
            System.out.println("list <address>: liste o numero disponivel de quartos em cada faixa de preco.");
            System.out.println("book <address> <type room> <name guest>: reserva um quarto do tipo especificado (se disponivel) e registra o nome do hospede");
            System.out.println("clients <address>: lista os nomes de todos os convidados registrados em cada tipo de quarto");
            return;
        }

        try {
            String address = args[1];
            String action = args[0];

            /* Busca o registro do RMI e busca a classe remota */
            Registry registry = LocateRegistry.getRegistry(address);
            RoomManager manager = (RoomManager) registry.lookup("Manager");

            switch (action) {
                case "list":
                    /* Printa o retorno da classe */
                    for (String info : manager.listRooms()) {
                        System.out.println(info);
                    }
                    return;
                case "book":
                    /* Verifica se foi enviados todos os argumentos */
                    if (args.length < 4) {
                        System.out.println("Uso: book <address> <type room> <name guest>");
                        return;
                    }
                    int type = Integer.parseInt(args[2]);
                    String name = args[3];
                    try {
                        /* Cadastra a reserva */
                        if (manager.bookRoom(type, name)) {
                            System.out.println("Reserva realizada");
                        } else {
                            System.out.println("Reserva nao realizada");
                        }
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    return;
                case "clients":
                    /* Printa o retorno da classe */
                    for (String info : manager.listGuests()) {
                        System.out.println(info);
                    }
                    return;
                default:
                    System.out.println("Comando invalido: " + action);
            }
        }catch (Exception e) {
            System.out.println("Comando invalido");
            e.printStackTrace();
        }
    }
}
