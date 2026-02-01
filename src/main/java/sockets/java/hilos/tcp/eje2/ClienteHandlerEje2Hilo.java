package sockets.java.hilos.tcp.eje2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClienteHandlerEje2Hilo extends Thread {

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String nombre;
    private List<ClienteHandlerEje2Hilo> clientes;


    public ClienteHandlerEje2Hilo(Socket socket, List<ClienteHandlerEje2Hilo> clientes) {
        this.socket = socket;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            String nombreUsuario = entrada.readUTF();
            System.out.println("New user connected: " + nombreUsuario);
            while (true) {
                String mensajes = entrada.readUTF();
                System.out.println("[" + nombreUsuario + "]:" + mensajes);
                synchronized (clientes) {
                    for (ClienteHandlerEje2Hilo cli : clientes) {
                        if (cli != this) {
                            try {
                                cli.salida.writeUTF("[" + nombre + "]: " + mensajes);
                                cli.salida.flush();
                            } catch (IOException io) {
                                io.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}
