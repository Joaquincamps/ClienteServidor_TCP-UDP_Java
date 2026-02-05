package sockets.java.udp_monohilo.eje3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorEje3Udp {

    private static final int PUERTO = 7000;

    public static void main(String[] args) {

        try {
            System.out.println("Servidor UDP iniciado en el `puerto " + PUERTO);
            DatagramSocket servidor = new DatagramSocket(PUERTO);
            byte[] buffer = new byte[10000];
            while (true) {
                System.out.println("Esperando mensaje ...");
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                servidor.receive(peticion);

                String mensajeRecibido = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("Mensaje recibido:");
                System.out.println(mensajeRecibido);

                byte[] bufferSalida = mensajeRecibido.toUpperCase().getBytes();
                DatagramPacket peticionSalida = new DatagramPacket(bufferSalida, bufferSalida.length, peticion.getAddress(),
                        peticion.getPort());
                servidor.send(peticionSalida);
                System.out.println("Mensaje enviado al cliente:");
                System.out.println(new String(peticionSalida.getData(), 0, peticionSalida.getLength()));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
