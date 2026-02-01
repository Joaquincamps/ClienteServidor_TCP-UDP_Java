package sockets.java.hilos.tcp.eje2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHiloEje2 {

    private static final int PUERTO = 5001;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {

        try {
            Socket cliente = new Socket(HOST, PUERTO);
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your name:");
            String nombre = sc.nextLine();
            salida.writeUTF(nombre);
            salida.flush();
            //HILO PARA LEER MENSAJES
            new Thread(() -> {
                while (true) {
                    try {
                        String mensajeEntrada = entrada.readUTF();
                        System.out.println(mensajeEntrada);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            }).start();

            //HILO PARA ENVIAR MENSAJE
            new Thread(() -> {
                try {
                    while (true) {
                        String mensajeEnvio = sc.nextLine();
                        salida.writeUTF(mensajeEnvio);
                        salida.flush();
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }).start();


        } catch (Exception e) {
            System.out.println("Disconnected from server.");
        }

    }
}
