package sockets.java.hilos.tcp.eje4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class ClienteHandlerHiloEje4 extends Thread {

    private Socket socket;
    private String nombre;
    private List<ClienteHandlerHiloEje4> listaHilos;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public ClienteHandlerHiloEje4(Socket socket, List<ClienteHandlerHiloEje4> listaHilos) {
        this.socket = socket;
        this.listaHilos = listaHilos;
    }

    @Override
    public void run() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

            nombre = entrada.readUTF();
            System.out.println("Nombre del cliente :" + nombre);

            while (true) {
                String mensaje  = entrada.readUTF();

                if (mensaje .equalsIgnoreCase("salir")) {
                    System.out.println("Cliente " + nombre + " desconectado.");
                    listaHilos.remove(this);
                    socket.close();
                    break;
                }

                broadcast("[" + nombre + "]: " + mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcast(String mensajeEnvio) {
        synchronized (listaHilos) {
            for (ClienteHandlerHiloEje4 clienteHandlerHiloEje4 : listaHilos) {
                try {
                    salida = new DataOutputStream(socket.getOutputStream());
                    clienteHandlerHiloEje4.salida.writeUTF(mensajeEnvio);
                    clienteHandlerHiloEje4.salida.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public String getNombre() {
        return nombre;
    }
}
