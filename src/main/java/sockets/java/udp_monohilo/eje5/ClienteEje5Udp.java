package sockets.java.udp_monohilo.eje5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteEje5Udp {
    /*
            Cliente conectado al servidor en puerto 6000
        Cliente: Pregunta recibida: ¿Capital de Francia?
        Introduce tu respuesta: París
        Cliente: Respuesta enviada
        Cliente: Pregunta recibida: ¿2 + 2?
        Introduce tu respuesta: 4
        Cliente: Respuesta enviada
        Cliente: Pregunta recibida: ¿Lenguaje de programación que usamos ahora?
        Introduce tu respuesta: Java
        Cliente: Respuesta enviada
        Cliente finalizado

     */
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try {
            DatagramSocket cliente = new DatagramSocket();
            System.out.println("Cliente conectado al servidor en el puerto " + PUERTO);
            InetAddress host = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            while (true) {
                byte[] bufferSalida = new byte[10000];
                DatagramPacket peticion = new DatagramPacket(bufferSalida, bufferSalida.length,
                        host, PUERTO);
                cliente.send(peticion);

                byte[] bufferEntrada = new byte[10000];
                DatagramPacket peticionEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                cliente.receive(peticionEntrada);
                System.out.println("Cliente: Pregunta recibida: " + new String(peticionEntrada.getData(), 0, peticionEntrada.getLength()));
                System.out.println("Introduce tu respuesta:");
                String enviarRespuesta = sc.nextLine();

                byte[] bufferRespuesta = enviarRespuesta.getBytes();
                DatagramPacket peticionSalida = new DatagramPacket(bufferRespuesta, bufferRespuesta.length,
                        peticionEntrada.getAddress(), peticionEntrada.getPort());
                cliente.send(peticionSalida);
                System.out.println("Cliente: Respuesta enviada");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
