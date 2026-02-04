package sockets.java.practicarParaExamen.tcp.eje1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorExPracEje2 {

    private static final int PUERTO = 6000;

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor TPC inciado en el puerto "+PUERTO);
            System.out.println("Esperando conexión ...");
            Socket socket = servidor.accept();
            InetAddress ip = socket.getInetAddress();
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            System.out.println("Cliente conectado desde "+ip);
            String nombreRecibido = entrada.readUTF();
            System.out.println("Nombre del usuaurio recibido:"+nombreRecibido);
            while (true){
                String mensajeRecibido = entrada.readUTF();
                if(mensajeRecibido.equalsIgnoreCase("FIN")){
                    System.out.println("Cerrando conexión en el cliente");
                    break;
                }
                System.out.println("["+nombreRecibido+"] Mensaje recibido: "+mensajeRecibido);
                salida.writeUTF(mensajeRecibido.toUpperCase() + " Caracteres: "+mensajeRecibido.length());
                System.out.println("Respuesta enviada al cliente");
                salida.flush();
            }
            System.out.println("Servidor detenido");
            socket.close();
            entrada.close();
            salida.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
