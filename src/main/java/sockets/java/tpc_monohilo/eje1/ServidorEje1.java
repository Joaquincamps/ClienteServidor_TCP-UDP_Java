package sockets.java.tpc_monohilo.eje1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEje1 {

    private final static int PUERTO = 5000;

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            Socket sc = null;
            DataInputStream entrada;
            DataOutputStream salida;

            System.out.println("[Servidor]:Esperando conexión.");

            while (true) {
                sc = servidor.accept();
                System.out.println("[Servidor] Cliente conectado desde: " +
                        sc.getInetAddress().getHostAddress());
                entrada = new DataInputStream(sc.getInputStream());
                salida = new DataOutputStream(sc.getOutputStream());

                String mensaje = entrada.readUTF();
                System.out.println("[Servidor] Mensaje recibido: " + mensaje);

                salida.writeUTF("Mensaje recibido correctamente");
                salida.flush();
                sc.close();
                System.out.println("[Servidor] Conexion cerrada");

            }
        } catch (Exception e) {
            System.out.println("[SERVIDOR]:Error durante la conexión al servidor.");
        }
    }
}
