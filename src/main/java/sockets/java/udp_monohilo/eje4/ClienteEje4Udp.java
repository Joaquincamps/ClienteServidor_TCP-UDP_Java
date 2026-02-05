package sockets.java.udp_monohilo.eje4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteEje4Udp {

    private static final int PUERTO = 7000;

    public static void main(String[] args) {

        try {
            DatagramSocket cliente = new DatagramSocket();
            InetAddress host = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Introduce un mensaje");
                String mensajeSalida = sc.nextLine();
                byte[] bufferSalida = mensajeSalida.getBytes();
                DatagramPacket peticion = new DatagramPacket(bufferSalida, bufferSalida.length,host,
                        PUERTO);
                cliente.send(peticion);

                System.out.println("Respuesta del servidor:");
                byte[] bufferEntrada = new byte[10000];
                DatagramPacket peticionEntrada = new DatagramPacket(
                        bufferEntrada, bufferEntrada.length
                );
                cliente.receive(peticionEntrada);
                System.out.println(new String(peticionEntrada.getData(), 0, peticionEntrada.getLength()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
