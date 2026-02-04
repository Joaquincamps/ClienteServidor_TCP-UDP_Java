package sockets.java.practicarParaExamen.tcp.eje2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteExPracEje2 {

    private static final int PUERTO = 7000;

    public static void main(String[] args) {
        /*
        Conectado al servidor 192.168.1.20:7000

--- MENÚ ---
1. Invertir texto
2. Contar palabras
3. Par o impar
4. Salir
Selecciona una opción:
1
Introduce texto:
programación
Servidor responde: nóicamargorp

--- MENÚ ---
1. Invertir texto
2. Contar palabras
3. Par o impar
4. Salir
Selecciona una opción:
2
Introduce texto:
sockets tcp java
Servidor responde: 3 palabras

--- MENÚ ---
1. Invertir texto
2. Contar palabras
3. Par o impar
4. Salir
Selecciona una opción:
3
Introduce número:
17
Servidor responde: impar

--- MENÚ ---
1. Invertir texto
2. Contar palabras
3. Par o impar
4. Salir
Selecciona una opción:
4
Servidor: Conexión cerrada
Cliente desconectado

         */
        try {
            Socket cliente = new Socket("localhost",PUERTO);
            InetAddress ip = cliente.getInetAddress();
            System.out.println("Conectado al servidor "+ip);
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while (true){
                String menu = entrada.readUTF();
                System.out.println(menu);
                System.out.println("Seleccione una opción:");
                int op = sc.nextInt();
                salida.writeUTF(String.valueOf(op));
                String textEnviar;
                int numEnviar;
                switch (op){
                    case 1 :
                        System.out.println("Introduce texto:");
                        textEnviar= sc.nextLine();
                        salida.writeUTF(textEnviar);
                        break;
                    case 2 :
                        System.out.println("Introduce texto:");
                        textEnviar= sc.nextLine();
                        salida.writeUTF(textEnviar);
                        break;
                    case 3 :
                        System.out.println("Introduce número:");
                        numEnviar = sc.nextInt();
                        salida.writeUTF(String.valueOf(numEnviar));
                        cliente.close();
                        entrada.close();
                        salida.close();
                        break;
                    case 4 :
                        System.out.println("Cliente desconectado");
                        break;

                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
