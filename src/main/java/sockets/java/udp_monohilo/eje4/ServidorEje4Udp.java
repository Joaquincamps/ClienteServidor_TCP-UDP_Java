package sockets.java.udp_monohilo.eje4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorEje4Udp {

    private static final int PUERTO = 7000;

    public static void main(String[] args) {

        try {
            DatagramSocket servidor = new DatagramSocket(PUERTO);
            System.out.println("Servidor UDP iniciado en el puerto 7000");
            byte[] bufferEntrada = new byte[10000];
            while (true) {
                DatagramPacket peticion = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                servidor.receive(peticion);
                System.out.println("Esperando mensaje ...");
                System.out.println("Mensaje recibido:");
                System.out.println(new String(peticion.getData(), 0, peticion.getLength()));
                System.out.println("IP del cliente: " + peticion.getAddress());
                System.out.println("Puerto del cliente: " + peticion.getPort());

                String mensajeSalida = new String(peticion.getData(), 0, peticion.getLength()).toUpperCase();
                byte[] bufferSalida = mensajeSalida.getBytes();
                DatagramPacket peticionSalidad = new DatagramPacket
                        (bufferSalida, bufferSalida.length,
                                peticion.getAddress(), peticion.getPort());
                servidor.send(peticionSalidad);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
