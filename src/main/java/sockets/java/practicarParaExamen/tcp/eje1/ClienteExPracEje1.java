package sockets.java.practicarParaExamen.tcp.eje1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Scanner;

public class ClienteExPracEje1 {

    private static final int PUERTO = 6000;

    public static void main(String[] args) {

        try {
            InetAddress ip = InetAddress.getLocalHost();
            Socket cliente = new Socket(ip,PUERTO);
            System.out.println("Conectado al servidor "+ip +":"+PUERTO);
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salidad = new DataOutputStream(cliente.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce tu nombre de usuario:");
            String nombreCliente = sc.nextLine();
            salidad.writeUTF(nombreCliente);
            while (true){
                System.out.println("Escribe un mensaje");
                String mensajeSalida = sc.nextLine();
                if(mensajeSalida.equalsIgnoreCase("FIN")){
                    break;
                }
                salidad.writeUTF(mensajeSalida);
                salidad.flush();
                System.out.println("Respuesta del servidor:");
                String mensajeRecibido = entrada.readUTF();
                System.out.println(mensajeRecibido);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
