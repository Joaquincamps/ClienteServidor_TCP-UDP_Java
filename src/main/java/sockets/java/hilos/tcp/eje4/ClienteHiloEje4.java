package sockets.java.hilos.tcp.eje4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteHiloEje4 {

    private static final int PUERTO = 40000;

    public static void main(String[] args) {

        try {
            Socket cliente = new Socket("localhost", PUERTO);
            System.out.println("Conectado al servidor");
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce tu nombre:");
            String nombre = sc.nextLine();
            salida.writeUTF(nombre);
            salida.flush();

            //HILO PARA LEER EL MENSAJE
            new Thread(() -> {
                while (true) {
                    try {
                        String mensajeEntrada = entrada.readUTF();
                        System.out.println(mensajeEntrada);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            //HILO PARA ENVIAR MENSAJE
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("Escribe un mensaje (salir para desconectar):\n");
                        String mensajeSalida = sc.nextLine();
                        if (mensajeSalida.equalsIgnoreCase("salir")) {
                            System.out.println("Conexión cerrada");
                            break;
                        }
                        salida.writeUTF(mensajeSalida);
                        salida.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            cliente.close();
            salida.close();
            entrada.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
