package sockets.java.redes.eje1;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Ejercicio1 {

    public static void main(String[] args) {

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface intefaz = interfaces.nextElement();
                mostrarInformacionInterfaz(intefaz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarInformacionInterfaz(NetworkInterface intefaz)
            throws SocketException {
        System.out.println("Nombre: " + intefaz.getName());
        System.out.println("Nombre presentación: " + intefaz.getDisplayName());
        System.out.println("Esta activa: " + intefaz.isUp());
        System.out.println("Soporta interfaz " + intefaz.isUp());
        System.out.println("Direcciones MAC" + intefaz.getHardwareAddress());

        System.out.println("Lista direcciones");
        List<InterfaceAddress> direcciones = intefaz.getInterfaceAddresses();
        Iterator<InterfaceAddress> it = direcciones.iterator();
        while (it.hasNext()) {
            InterfaceAddress direccion = it.next();

            System.out.println("Direccion:" + direccion.getAddress());
            System.out.println("Direccion de broadcast:" + direccion.getBroadcast());
            System.out.println("Direccion de red" + direccion.getNetworkPrefixLength());


        }
    }

}
