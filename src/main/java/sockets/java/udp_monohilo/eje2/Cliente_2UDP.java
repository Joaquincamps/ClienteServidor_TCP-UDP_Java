package sockets.java.udp_monohilo.eje2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente_2UDP {

    private static final int PUERTO = 5555;

    public static void main(String[] args) {

        try {
            DatagramSocket cliente = new DatagramSocket();
            System.out.println("Cliente UDP inciado");
            InetAddress host = InetAddress.getByName("localhost");
            System.out.println("Servidor " + host.getHostName() + ":" + PUERTO);
            System.out.println("Comandos: ECO <texto> | MAYUS <texto> | LONG <texto> | SALIR");
            System.out.println("----------------------------------------------------------");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String cad = br.readLine();
                byte[] mensajeByte = cad.getBytes();

                DatagramPacket peticion = new DatagramPacket(mensajeByte, mensajeByte.length,
                        host, PUERTO);

                cliente.send(peticion);

                byte[] bufer = new byte[10000];
                DatagramPacket mensaje = new DatagramPacket(
                        peticion.getData(), peticion.getLength()
                );
                cliente.receive(mensaje);
                System.out.println("Respuesta del servidor: " + new String(
                        mensaje.getData(), 0, mensaje.getLength()
                ));

            }
        } catch (Exception e) {
            System.out.println("Erro con el usuario" + e.getMessage());
        }


    }
}
