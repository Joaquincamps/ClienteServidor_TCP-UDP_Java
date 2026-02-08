package sockets.java.hilos.tcp.eje3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class ClienteEje3Handler extends Thread {

    private Socket socket;
    private List<ClienteEje3Handler> clientes;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String nombre;

    public ClienteEje3Handler(Socket socket, List<ClienteEje3Handler> clientes) {
        this.socket = socket;
        this.clientes = clientes;

    }

    public ClienteEje3Handler() {
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            nombre = entrada.readUTF();
            System.out.println("Conectado al chat");
            while (true) {
                String mensajeEnvio = entrada.readUTF();
                broadcast(mensajeEnvio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcast(String mensaje) {
        synchronized (clientes) {
            for (ClienteEje3Handler clienteEje3Handler : clientes) {
                try {
                    clienteEje3Handler.salida.writeUTF(nombre + ":" + mensaje);
                    clienteEje3Handler.salida.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
