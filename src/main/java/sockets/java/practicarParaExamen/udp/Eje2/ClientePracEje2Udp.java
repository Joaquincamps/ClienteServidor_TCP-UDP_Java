package sockets.java.practicarParaExamen.udp.Eje2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientePracEje2Udp {

    private static final int PUERTO = 5555;

    public static void main(String[] args) {

        try {
            DatagramSocket cliente = new DatagramSocket();
            System.out.println("Cliente UDP iniciado");
            InetAddress host = InetAddress.getByName("localhost");
            System.out.println("Servidor:" + host.getHostName() + ":" + PUERTO);
            System.out.println("Comandos: ECO <texto> | MAYUS <texto> | LONG <texto> " +
                    "| SALIR\n" +
                    "----------------------------------------------------------\n");
            Scanner sc = new Scanner(System.in);
            while (true) {
                String mensajeSalida = sc.nextLine();
                byte[] bufferSalida = mensajeSalida.getBytes();
                DatagramPacket salida = new DatagramPacket(bufferSalida, bufferSalida.length,
                        host, PUERTO);
                cliente.send(salida);

                byte[] bufferEntrada = new byte[10000];
                DatagramPacket entrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                cliente.receive(entrada);
                System.out.println("Respuesta del servidor: " + new String(entrada.getData(), 0, entrada.getLength()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
