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
                byte[] bufferSalida = arrayPreguntas[aleatorio].getBytes();
                DatagramPacket peticionSalida = new DatagramPacket(bufferSalida, bufferSalida.length);
                servidor.send(peticionSalida);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
