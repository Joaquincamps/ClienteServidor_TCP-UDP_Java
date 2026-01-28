package sockets.java.tpc_monohilo.eje2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEje2 {

    private static final int PUERTO = 5400;

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            Socket socket = servidor.accept();

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            System.out.println("Servidor iniciado...");
            System.out.println("Cliente conectado");

            while (true) {
                String mensaje = entrada.readUTF();
                System.out.println("Mensaje recibido: " + mensaje);
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Servidor cerrado");
                    break;
                }
                salida.writeUTF(mensaje.toUpperCase());
                salida.flush();
            }

            entrada.close();
            salida.close();
            servidor.close();

        } catch (Exception e) {
            System.out.println("Error al inciar el servidor" + e.getMessage());
        }
    }
}
