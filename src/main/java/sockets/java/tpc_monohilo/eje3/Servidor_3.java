package sockets.java.tpc_monohilo.eje3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor_3 {

    private final static int PUERTO = 5000;

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            Socket sc = servidor.accept();
            System.out.println("Servidor iniciado. Esperando conexiones...");
            DataInputStream entrada = new DataInputStream(sc.getInputStream());
            DataOutputStream salida = new DataOutputStream(sc.getOutputStream());
            System.out.println("Cliente conectado desde /"+sc.getLocalSocketAddress());
            while (true){
                String numEntrada = entrada.readUTF();
                System.out.println("Número recibido:"+numEntrada);
                int numValorNumerico = Integer.parseInt(numEntrada);
                if(numValorNumerico % 2 ==0){
                    String mensajeEnviado = numValorNumerico +" es par";
                    salida.writeUTF(mensajeEnviado);
                    salida.flush();
                    System.out.println("Enviado al cliente "+mensajeEnviado);
                }else{
                    String mensajeEnviado = numValorNumerico +" es impar";
                    salida.writeUTF(mensajeEnviado);
                    salida.flush();
                    System.out.println("Enviado al cliente "+mensajeEnviado);
                }
                if(numValorNumerico<0){
                    sc.close();
                    break;
                }
            }
            sc.close();
            entrada.close();
            salida.close();
            System.out.println("Cliente desconectado");
            System.out.println("Esperando nuevas conexiones...");
        }catch (Exception e){
            System.out.println("Error con el servidor."+e.getMessage());
        }
    }
}
