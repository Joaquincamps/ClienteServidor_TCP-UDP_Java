package sockets.java.tpc_monohilo.eje2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEje2 {

    private static final String HOST = "127.0.0.1";
    private static final int PUERTO = 5400;

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket(HOST, PUERTO);
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Escriba un mensaje:");
                String mensaje = sc.nextLine();
                salida.writeUTF(mensaje);
                salida.flush();

                String mensajeEntrada = entrada.readUTF();

                System.out.println("Respuesta del servidor: Servidor dice :" + mensajeEntrada);
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Conexión cerrada");
                    break;
                }

            }
            cliente.close();
            sc.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
