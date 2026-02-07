package sockets.java.udp_monohilo.eje5;

import java.net.DatagramSocket;

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
            //cliente.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
