package sockets.java.redes.eje1;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class Eje2 {

    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface interfaz = interfaces.nextElement();
                Enumeration<InetAddress> direccionesIp = interfaz.getInetAddresses();

                while (direccionesIp.hasMoreElements()) {
                    InetAddress direccion = direccionesIp.nextElement();
                    if (direccion instanceof Inet4Address) {
                        System.out.println(direccion);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
