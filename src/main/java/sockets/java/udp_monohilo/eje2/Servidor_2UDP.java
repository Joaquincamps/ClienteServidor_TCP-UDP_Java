package sockets.java.udp_monohilo.eje2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor_2UDP {

    private static final int PUERTO = 5555;

    public static void main(String[] args) {

        try {
            DatagramSocket servidor = new DatagramSocket(PUERTO);
            System.out.println("Servidor UPD iniciado en el puerto " + PUERTO);
            byte[] buffer = new byte[10000];

            while (true) {
                DatagramPacket peticion = new DatagramPacket(
                        buffer, buffer.length
                );
                servidor.receive(peticion);
                String mensajeEnTexto = new String(peticion.getData(), 0, peticion.getLength());


                if (!mensajeEnTexto.contains("ECO") && !mensajeEnTexto.contains("MAYUS")
                        && !mensajeEnTexto.contains("LONG")) {
                    break;
                }

                System.out.println("Recibido de " + peticion.getAddress() + ":"
                        + peticion.getPort() + " -> " + mensajeEnTexto);

                String[] partes = mensajeEnTexto.split(" ");

                DatagramPacket mensaje;
                byte[] bufferRespuesta;

                if (mensajeEnTexto.contains("ECO")) {
                    bufferRespuesta = partes[1].getBytes();

                    mensaje = new DatagramPacket(bufferRespuesta, bufferRespuesta.length,
                            peticion.getAddress(), peticion.getPort());
                    servidor.send(mensaje);
                }
                if (mensajeEnTexto.contains("MAYUS")) {
                    String mensajeEco = partes[1].toUpperCase();
                    bufferRespuesta = mensajeEco.getBytes();

                    mensaje = new DatagramPacket(bufferRespuesta, bufferRespuesta.length,
                            peticion.getAddress(), peticion.getPort());
                    servidor.send(mensaje);
                }
                if (mensajeEnTexto.contains("LONG")) {
                    String mensajeEco = String.valueOf(partes[1].length());
                    bufferRespuesta = mensajeEco.getBytes();

                    mensaje = new DatagramPacket(bufferRespuesta, bufferRespuesta.length,
                            peticion.getAddress(), peticion.getPort());
                    servidor.send(mensaje);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al innciar el servidor UDP" + e.getMessage());
        }
    }
}
