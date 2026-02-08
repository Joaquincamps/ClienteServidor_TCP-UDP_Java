package sockets.java.practicarParaExamen.tcp.eje3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientePracExEje3Tcp {

    private final static int PUERTO = 6000;
    private final static String HOST = "127.0.0.1";

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket(HOST, PUERTO);
            System.out.println("Conectado al servidor");
            Scanner sc = new Scanner(System.in);
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            while (true) {
                System.out.println("Escribe un mensaje ");
                String mensajeEnviado = sc.nextLine();
                salida.writeUTF(mensajeEnviado);
                salida.flush();

                if (mensajeEnviado.equalsIgnoreCase("exit")) {
                    System.out.println("Conexión cerrada");
                    break;
                }

                String mensajeRecibido = entrada.readUTF();
                System.out.println("Respuesta del servidor: " + mensajeRecibido);

            }
            cliente.close();
            entrada.close();
            salida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
