package sockets.java.hilos.tcp.eje3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorEje3Multihilo {

    private static final int PUERTO = 6000;
    public static List<ClienteEje3Handler> listaHilos = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {


        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            ExecutorService pool = Executors.newFixedThreadPool(4);

            while (true) {
                ClienteEje3Handler clienteEje3Handler = new ClienteEje3Handler(servidor.accept(), listaHilos);
                listaHilos.add(clienteEje3Handler);
                pool.execute(clienteEje3Handler);
                System.out.println("Cliente conectado desde " + clienteEje3Handler.getSocket().getInetAddress());

            }

        } catch (Exception e) {

        }
    }
}
