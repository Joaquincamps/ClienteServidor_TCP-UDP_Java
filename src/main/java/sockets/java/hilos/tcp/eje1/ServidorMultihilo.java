package sockets.java.hilos.tcp.eje1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorMultihilo {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(20064);
            System.out.println("Servidor multihilo se ha iniciado");

            ExecutorService pool = Executors.newFixedThreadPool(20);

            while (true) {
                pool.execute(new ServidorHandler(servidor.accept()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ServidorHandler implements Runnable {

        private Socket socket;

        public ServidorHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Conectado: " + socket);
            try {
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String mensajeEntrada = entrada.readUTF();
                    System.out.println(mensajeEntrada);

                    salida.writeUTF(mensajeEntrada.toUpperCase());
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Socket cerrado");
            }
        }
    }

}
