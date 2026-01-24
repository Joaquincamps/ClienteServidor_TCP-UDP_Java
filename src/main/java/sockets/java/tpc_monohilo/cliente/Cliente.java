package sockets.java.tpc_monohilo.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {

    private final static String HOST = "localhost";
    private final static int PUERTO = 4000;

    public static void main(String[] args) {
        DataInputStream entrada;
        DataOutputStream salida;

        try {
            Socket socket = new Socket(HOST, PUERTO);
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

            salida.writeUTF("Hola primera conexión");

            String mensaje = entrada.readUTF();

            System.out.println(mensaje);

            socket.close();
        } catch (Exception e) {
            System.out.println("Error con el cliente." + e.getMessage());
        }

    }
}
