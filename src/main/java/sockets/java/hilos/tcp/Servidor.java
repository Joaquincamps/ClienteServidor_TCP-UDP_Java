package sockets.java.hilos.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(5000);
            Socket sc;
            System.out.println("Servidor iniciado");

            while (true) {
                sc = servidor.accept();

                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());

                out.writeUTF("Indica tu nombre");
                String nombreCliente = in.readUTF();

                ServidorHilo hilo = new ServidorHilo(in, out, nombreCliente);

                hilo.start();

                System.out.println("Creada la conexión con el cliente " + nombreCliente);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
