package sockets.java.practicarParaExamen.udp.Eje1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientePracUdpEje1 {

    private static final int PUERTO = 5100;

    public static void main(String[] args) {

        try {
            DatagramSocket cliente = new DatagramSocket();
            InetAddress host = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.println("Introduce el pedido (ID;PRODUCTO;CANTIDAD;PRECIO):");
                String pedido = sc.nextLine();

                byte [] mensajeByte = pedido.getBytes();

                DatagramPacket peticion = new DatagramPacket(mensajeByte, mensajeByte.length,host,PUERTO);
                cliente.send(peticion);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
