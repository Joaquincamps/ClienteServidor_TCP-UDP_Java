package sockets.java.udp_monohilo.eje1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor_1UDP {

    private final static int PUERTO = 5000;

    public static void main(String[] args) {

        try {
            System.out.println("Servidor inicado");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO);
            byte[] bufer = new byte[10000];

            while (true) {
                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);

                socketUDP.receive(peticion);

                DatagramPacket mensaje = new DatagramPacket(
                        peticion.getData(), peticion.getLength(),
                        peticion.getAddress(), peticion.getPort()
                );

                socketUDP.send(mensaje);

                System.out.println("datos: " + new String(
                        peticion.getData(), 0, peticion.getLength()));
                System.out.println("Puerto del cliente:" +
                        peticion.getPort());
                System.out.println("Tamaño de paquete"+
                        peticion.getLength());
            }


        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
