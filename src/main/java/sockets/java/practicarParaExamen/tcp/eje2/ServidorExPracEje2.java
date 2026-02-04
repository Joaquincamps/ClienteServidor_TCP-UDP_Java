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
            System.out.println("Servidor TCP iniciado en el puerto " + PUERTO);
            System.out.println("Esperando conexión ...");
            Socket socket = servidor.accept();
            InetAddress ip = socket.getInetAddress();
            System.out.println("Cliente conectado desde " + ip);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            System.out.println("Menú enviado al cliente");
            boolean verdad = true;
            while (verdad) {
                String menu =
                        "--- MENÚ ---\n" +
                                "1. Invertir texto\n" +
                                "2. Contar palabras\n" +
                                "3. Par o impar\n" +
                                "4. Salir";

                salida.writeUTF(menu);
                salida.flush();

                String opSeleccionada = entrada.readUTF();
                System.out.println("Opción seleccionada :" + opSeleccionada);
                String mensajeRecibido;
                switch (opSeleccionada) {
                    case "1":
                        mensajeRecibido = entrada.readUTF();
                        System.out.println("Texto recibido: " + mensajeRecibido);
                        String mensajeAlReves = new StringBuilder(mensajeRecibido).reverse().toString();
                        salida.writeUTF(mensajeAlReves);
                        salida.flush();
                        System.out.println("Resultado enviado: " + mensajeAlReves);
                        break;
                    case "2":
                        mensajeRecibido = entrada.readUTF();
                        System.out.println("Texto recibido: " + mensajeRecibido);
                        String[] array = mensajeRecibido.split(" ");
                        salida.writeUTF(String.valueOf(array.length));
                        salida.flush();
                        System.out.println("Resultado enviado: " + String.valueOf(array.length) + " palabras");
                        break;
                    case "3":
                        mensajeRecibido = entrada.readUTF();
                        System.out.println("Número recibido: " + mensajeRecibido);
                        int numeroReceived = Integer.parseInt(mensajeRecibido);
                        if (numeroReceived % 2 == 0) {
                            salida.writeUTF("par");
                            salida.flush();
                            System.out.println("Resultado enviado: par");
                        } else {
                            salida.writeUTF("impar");
                            salida.flush();
                            System.out.println("Resultado enviado: impar");
                        }
                        break;
                    case "4":
                        System.out.println("Cerrando conexión con el cliente");
                        verdad = false;
                        break;
                }
                socket.close();
                entrada.close();
                salida.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
