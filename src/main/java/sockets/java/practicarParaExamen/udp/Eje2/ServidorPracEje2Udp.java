package sockets.java.practicarParaExamen.udp.Eje2;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorPracEje2Udp {

    private static final int PUERTO = 5555;

    public static void main(String[] args) {

        try {
            DatagramSocket servidor = new DatagramSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                byte[] bufferEntrada = new byte[10000];
                DatagramPacket entrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
                servidor.receive(entrada);
                String mensajeRecibido = new String(entrada.getData(), 0, entrada.getLength());
                System.out.println("Recibido de " + entrada.getAddress() + ":" + entrada.getPort() + "->" +
                        mensajeRecibido);

                String[] array = mensajeRecibido.split(" ");

                if (mensajeRecibido.contains("ECO")) {
                    byte[] bufferSalida = array[1].getBytes();
                    DatagramPacket salida = new DatagramPacket(bufferSalida, bufferSalida.length,
                            entrada.getAddress(), entrada.getPort());
                    servidor.send(salida);
                }

                if (mensajeRecibido.contains("MAYUS")) {
                    byte[] bufferSalida = array[1].toUpperCase().getBytes();
                    DatagramPacket salida = new DatagramPacket(bufferSalida, bufferSalida.length,
                            entrada.getAddress(), entrada.getPort());
                    servidor.send(salida);
                }

                if (mensajeRecibido.contains("LONG")) {
                    int lon = array[1].length();
                    String valorLon = String.valueOf(lon);
                    byte[] bufferSalida = valorLon.getBytes();
                    DatagramPacket salida = new DatagramPacket(bufferSalida, bufferSalida.length,
                            entrada.getAddress(), entrada.getPort());
                    servidor.send(salida);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
