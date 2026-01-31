package sockets.java.udp_monohilo.eje1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente_1UDP {

    private final static int PUERTO = 5000;

    public static void main(String[] args) {

        try {
            System.out.println("Cliente conectado");
            DatagramSocket socketUDP = new DatagramSocket();
            InetAddress host = InetAddress.getByName("localhost");

            BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));

            String cad;
            cad = br.readLine();

            byte[] mensajeByte = cad.getBytes();
            DatagramPacket peticion = new DatagramPacket(
                    mensajeByte, cad.length(), host, PUERTO
            );

            socketUDP.send(peticion);

        } catch (Exception e) {
            System.out.println("Error al uniciar servidor" + e.getMessage());
        }
    }
}
