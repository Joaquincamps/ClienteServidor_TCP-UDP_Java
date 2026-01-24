package sockets.java.tpc_monohilo.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private final static int PUERTO = 4000;

    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream entrada;
        DataOutputStream salida;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Iniciando servidor");

            while (true) {
                sc = servidor.accept();
                System.out.println("Cliente conectado.");
                entrada = new DataInputStream(sc.getInputStream());
                salida = new DataOutputStream(sc.getOutputStream());

                //se queda a la espera de leer el mensaje que le mande
                //el cliente
                String mensaje = entrada.readUTF();

                System.out.println(mensaje);

                salida.writeUTF("Hola mundo desde el cliente.");

                //cerramos al cliente
                sc.close();

                System.out.println("Cliente desconectado.");
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar el servidor." + e.getMessage());
        }
    }
}
