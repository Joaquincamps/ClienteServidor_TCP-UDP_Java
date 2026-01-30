package sockets.java.udp_monohilo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

    private final static int PUERTO = 5600;

    public static void main(String[] args) {
        try {
            InetAddress direccion = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();

            String mensaje = "Hola mundo desde el cliente";
            byte[] buffer = mensaje.getBytes();

            DatagramPacket pregunta =
                    new DatagramPacket(buffer, buffer.length, direccion, PUERTO);
            socketUDP.send(pregunta);

            byte[] bufferRespuesta = new byte[1024];
            DatagramPacket peticion =
                    new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

            socketUDP.receive(peticion);

            mensaje = new String(
                    peticion.getData(),
                    0,
                    peticion.getLength()
            );

            System.out.println(mensaje);
            socketUDP.close();

        } catch (Exception e) {
            System.out.println("Error con el cliente: " + e.getMessage());
        }
    }

}
