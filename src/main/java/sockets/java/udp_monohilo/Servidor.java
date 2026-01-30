package sockets.java.udp_monohilo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

    private final static int PUERTO = 5600;

    public static void main(String[] args) {
        byte[] buffer = new byte[1024];

        try {
            System.out.println("Iniciador servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(peticion);

            String mensaje = new String(
                    peticion.getData(),
                    0,
                    peticion.getLength()
            );
            System.out.println("Cliente dice: " + mensaje);

            int puertoCliente = peticion.getPort();
            InetAddress direccion = peticion.getAddress();

            mensaje = "Hola mundo desde el servidor";
            buffer = mensaje.getBytes();

            DatagramPacket respuesta =
                    new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);

            System.out.println("Envio paquete al cliente");
            socketUDP.send(respuesta);

            socketUDP.close();

        } catch (Exception e) {
            System.out.println("Error al levantar el servidor: " + e.getMessage());
        }
    }

}
