package sockets.java.hilos.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class ClienteHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Almacenar numero en un archivo");
            System.out.println("2. Numeros almacenados hasta el momento");
            System.out.println("3. Lista numeros almacenados");
            System.out.println("4. El número de de numeros almacenados");
            System.out.println("5. Archivo con numeros del cliente");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Has elegido almacenar un número en el archivo");
                    break;

                case 2:
                    System.out.println("Has elegido ver los números almacenados hasta el momento");
                    break;

                case 3:
                    System.out.println("Has elegido listar todos los números almacenados");
                    break;

                case 4:
                    System.out.println("Has elegido ver la cantidad de números almacenados");
                    break;

                case 5:
                    System.out.println("Has elegido mostrar el archivo con los números del cliente");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 1 al 5");
                    break;
            }
        }
    }
}
