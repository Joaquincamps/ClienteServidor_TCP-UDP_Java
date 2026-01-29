package sockets.java.tpc_monohilo.eje3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente_3 {

    private final static String HOST = "127.0.0.1";
    private final static int PUERTO = 5000;

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket(HOST,PUERTO);
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            System.out.println("Conectado al servidor en localhost:"+PUERTO);
            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.print("Enviame número:");
                String numeroSalida = sc.nextLine();
                salida.writeUTF(numeroSalida);
                salida.flush();

                int numeroValor = Integer.parseInt(numeroSalida);
                if(numeroValor<0){
                    System.out.println("Desconectamos del servidor");
                    break;
                }
                String mensajaEntrada = entrada.readUTF();
                System.out.println("Respuesta del servidor:"+mensajaEntrada);
            }

        }catch (Exception e){
            System.out.println("Error al iniciar el cliente."+e.getMessage());
        }
    }
}
