package sockets.java.hilos.tcp.eje4;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorHiloEje4 {

    private final static int PUERTO = 40000;

    public static void main(String[] args) {

        List<ClienteHandlerHiloEje4> listaHilos = Collections.synchronizedList(new ArrayList<>());
        ExecutorService pool = Executors.newFixedThreadPool(4);
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            while (true) {
                System.out.println("Esperando clientes ...");
                ClienteHandlerHiloEje4 clienteHandlerHiloEje4 = new ClienteHandlerHiloEje4(servidor.accept(), listaHilos);
                listaHilos.add(clienteHandlerHiloEje4);
                pool.execute(clienteHandlerHiloEje4);
                System.out.println("Cliente conectado desde " + clienteHandlerHiloEje4.getSocket().getInetAddress());
            }

        } catch (Exception e) {

        }
    }
}
