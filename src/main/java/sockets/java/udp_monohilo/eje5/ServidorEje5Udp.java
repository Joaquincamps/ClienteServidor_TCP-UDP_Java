package sockets.java.udp_monohilo.eje5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class ServidorEje5Udp {
    /*
                Servidor iniciado en puerto 6000
            Servidor: Pregunta enviada: ¿Capital de Francia?
            Servidor: Respuesta recibida: París
            Servidor: Pregunta enviada: ¿2 + 2?
            Servidor: Respuesta recibida: 4
            Servidor: Pregunta enviada: ¿Lenguaje de programación que usamos ahora?
            Servidor: Respuesta recibida: Java

     */

    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try {
            Random random = new Random();
            DatagramSocket servidor = new DatagramSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                String[] arrayPreguntas = {"¿Capital de Francia?", "¿2 + 2?", "¿Lenguaje de programación que usamos ahora?"};
                int aleatorio = random.nextInt(arrayPreguntas.length);
                byte[] bufferEntrada = new byte[10000];
                DatagramPacket peticionEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                servidor.receive(peticionEntrada);

                byte[] bufferSalida = arrayPreguntas[aleatorio].getBytes();
                DatagramPacket peticionSalida = new DatagramPacket(bufferSalida, bufferSalida.length,
                        peticionEntrada.getAddress(), peticionEntrada.getPort());
                servidor.send(peticionSalida);
                System.out.println("Servidor: Pregunta enviada: " + new String(peticionSalida.getData(), 0, peticionSalida.getLength()));

                byte[] bufferEntradaRespuesta = new byte[10000];
                DatagramPacket peticionEntradaRespuesta = new DatagramPacket(bufferEntradaRespuesta, bufferEntradaRespuesta.length);
                servidor.receive(peticionEntradaRespuesta);
                System.out.println("Servidor: Respuesta recibida: " + new String(peticionEntradaRespuesta.getData(), 0, peticionEntradaRespuesta.getLength()));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
