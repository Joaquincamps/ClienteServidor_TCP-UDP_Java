package sockets.java.practicarParaExamen.tcp.eje2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorExPracEje2 {

    private static final int PUERTO = 7000;

    public static void main(String[] args) {
        /*

        Servidor TCP iniciado en el puerto 7000
Esperando conexión...

Cliente conectado desde 192.168.1.15
Menú enviado al cliente

Opción seleccionada: 1
Texto recibido: programación
Resultado enviado: nóicamargorp

Opción seleccionada: 2
Texto recibido: sockets tcp java
Resultado enviado: 3 palabras

Opción seleccionada: 3
Número recibido: 17
Resultado enviado: impar

Opción seleccionada: 4
Cerrando conexión con el cliente...
Servidor finalizado
         */
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor TCP iniciado en el puerto "+PUERTO);
            System.out.println("Esperando conexión ...");
            Socket socket = servidor.accept();
            InetAddress ip = socket.getInetAddress();
            System.out.println("Cliente conectado desde "+ip);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            System.out.println("Menú enviado al cliente");
            while (true){
                String menu =
                        "--- MENÚ ---\n" +
                                "1. Invertir texto\n" +
                                "2. Contar palabras\n" +
                                "3. Par o impar\n" +
                                "4. Salir";

                salida.writeUTF(menu);
                salida.flush();

                String opSeleccionada = entrada.readUTF();
                System.out.println("Opción seleccionada :"+opSeleccionada);

            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
