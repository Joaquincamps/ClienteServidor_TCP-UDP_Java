package sockets.java.hilos.tcp.eje2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorHiloEje2 {

    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        List<ClienteHandlerEje2Hilo> clientes = Collections.synchronizedList(new ArrayList<>());
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Chat Server is listening on port " + PUERTO);

            ExecutorService pool = Executors.newFixedThreadPool(3);

            while (true) {
                ClienteHandlerEje2Hilo ch = new ClienteHandlerEje2Hilo(servidor.accept(), clientes);
                clientes.add(ch);
                pool.execute(ch);
                System.out.println("New user connected");
                ch.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
