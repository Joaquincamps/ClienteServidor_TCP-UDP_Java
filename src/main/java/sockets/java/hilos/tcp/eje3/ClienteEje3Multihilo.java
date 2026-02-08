package sockets.java.hilos.tcp.eje3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEje3Multihilo {

    private static final int PUERTO = 6000;

    public static void main(String[] args) {

        try {
            Socket cliente = new Socket("localhost", PUERTO);
            System.out.println("Conectado al chat");
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce tu nombre:");
            String mensajeNombre = sc.nextLine();
            salida.writeUTF(mensajeNombre);
            salida.flush();

            //HILO PARA LEER MENSAJES
            new Thread(() -> {
                while (true) {
                    try {
                        String mensajeLeer = entrada.readUTF();
                        System.out.println(mensajeLeer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            //HILO PARA ENVIAR MENSAJES
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("Escriba su mensaje:");
                        String mensajeEnvio = sc.nextLine();
                        salida.writeUTF(mensajeEnvio);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
