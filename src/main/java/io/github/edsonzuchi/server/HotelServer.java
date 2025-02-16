package io.github.edsonzuchi.server;

import io.github.edsonzuchi.server.core.RoomManager;
import io.github.edsonzuchi.server.core.impl.RoomManagerImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HotelServer {

    public static void main(String[] args) {
        try {
            /* Exporta a classe para a remota do RMI do JAVA */
            RoomManagerImpl managerimpl = new RoomManagerImpl();
            RoomManager manager = (RoomManager) UnicastRemoteObject.exportObject(managerimpl, 0);

            /* Pega o registro do RMI aberto por comando, como não solicitado a porta pega da porta 1099 */
            Registry registry = LocateRegistry.getRegistry();

            /* Registra a classe no RMI */
            registry.bind("Manager", manager);

            System.out.println("Hotel Server started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
