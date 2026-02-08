package sockets.java.practicarParaExamen.tcp.eje3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPracExEje3Tcp {

    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado desde " + socket.getInetAddress());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String mensajeRecibido = entrada.readUTF();
                System.out.println("Mensaje recibido " + mensajeRecibido);


                if (mensajeRecibido.equalsIgnoreCase("hola")) {
                    salida.writeUTF("¡HOLA, CLIENTE!");
                    salida.flush();
                } else {
                    String mensajeEnviado = mensajeRecibido.toUpperCase();
                    salida.writeUTF(mensajeEnviado);
                    salida.flush();
                }
                if (mensajeRecibido.equalsIgnoreCase("exit")) {
                    System.out.println("Conexión finalizada");
                    break;
                }
            }
            System.out.println("Servidor apagado");
            servidor.close();
            entrada.close();
            salida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
