package sockets.java.practicarParaExamen.udp.Eje1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorPracUdpEje1 {

    private static final int PUERTO = 5100;
    private static final String PEDDIDO_OK = "OK";
    private static final String ERROR_PEDDIDO = "ERROR;Pedido no válido";
    public static void main(String[] args) {

        try {
            DatagramSocket servidor = new DatagramSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto 5000 ...");
            byte[] buffer = new byte[10000];

            while (true){
                System.out.println("Esperando pedidos ... ");
                DatagramPacket peticion = new DatagramPacket(buffer,buffer.length);
                servidor.receive(peticion);

                String pedidoRecibido = new String(peticion.getData(),0,peticion.getLength());

                String[] arrayPartesPedido = pedidoRecibido.split(";");
                byte[] bufferRespuesta;
                DatagramPacket peticionSalida = new DatagramPacket(buffer, buffer.length);
                if(isNumeric(arrayPartesPedido[1])){
                    System.out.println(ERROR_PEDDIDO);
                    bufferRespuesta = ERROR_PEDDIDO.getBytes();
                    peticionSalida = new DatagramPacket(bufferRespuesta, bufferRespuesta.length,peticion.getAddress(),peticion.getPort());
                    servidor.send(peticionSalida);

                }

                System.out.println("Pedido recibido:");
                System.out.println(pedidoRecibido);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        }catch (NumberFormatException nfe){
            return false;
        }
    }
}
