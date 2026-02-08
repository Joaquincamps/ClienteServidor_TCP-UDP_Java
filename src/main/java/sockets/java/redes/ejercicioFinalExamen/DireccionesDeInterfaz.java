package sockets.java.redes.ejercicioFinalExamen;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class DireccionesDeInterfaz {

    public static void main(String[] args) {

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface interfaz = interfaces.nextElement();
                System.out.println("Interfaz:" + interfaz.getName());
                System.out.println("Activa:" + interfaz.isUp());
                System.out.println("Loopback:" + interfaz.isLoopback());
                System.out.println("IP: " + obtenerIp(interfaz));
                System.out.println("MAC: " + interfaz.getHardwareAddress());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String obtenerIp(NetworkInterface interfaz) {
        Enumeration<InetAddress> direccionesIP = interfaz.getInetAddresses();
        while (direccionesIP.hasMoreElements()) {
            InetAddress direccionIp = direccionesIP.nextElement();
            if (direccionIp instanceof Inet4Address) {
                return direccionIp.getHostAddress();
            }
        }
        return "";
    }
}
