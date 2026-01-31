package sockets.java.hilos.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");

            Socket socket = new Socket("localhost", 5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String mensaje = in.readUTF();
            System.out.println(mensaje);

            String nombre = sc.next();
            out.writeUTF(nombre);

            ClienteHilo clienteHilo = new ClienteHilo(in, out);
            clienteHilo.start();
            clienteHilo.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
