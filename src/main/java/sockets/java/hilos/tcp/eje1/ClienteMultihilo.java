package sockets.java.hilos.tcp.eje1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMultihilo {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 20064);
            System.out.println("Ingrese texto, para finalizar presione CTRL+C");

            Scanner sc = new Scanner(System.in);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            while (true) {
                salida.writeUTF(sc.nextLine());
                salida.flush();

                String mensajeEntrada = entrada.readUTF();
                System.out.println("Mensaje del servidor: " + mensajeEntrada);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
