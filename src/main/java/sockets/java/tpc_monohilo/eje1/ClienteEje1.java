package sockets.java.tpc_monohilo.eje1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEje1 {

    private final static String HOST = "127.0.0.1";
    private final static int PUERTO = 5000;

    public static void main(String[] args) {

        try {
            Socket cliente = new Socket(HOST, PUERTO);
            DataInputStream entrada;
            DataOutputStream salida;
            Scanner sc = new Scanner(System.in);

            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            System.out.println("[Cliente] Conectado al servidor.");
            System.out.println("[Cliente] Escribe un mensaje:");
            String mensaje = sc.nextLine();

            salida.writeUTF(mensaje);
            salida.flush();

            String respuesta = entrada.readUTF();

            System.out.println("[Cliente] Respuesta del servidor: "+respuesta);
            cliente.close();
            System.out.println("[Cliente] Conexión cerrada.");

        } catch (Exception e) {
            System.out.println("Error con el cliente" + e.getMessage());
        }
    }
}
