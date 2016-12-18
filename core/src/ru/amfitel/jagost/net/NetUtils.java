package ru.amfitel.jagost.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by st_ni on 18.12.2016.
 */
public class NetUtils {

    public static String getOwnIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface ni : Collections.list(interfaces)) {
                for (InetAddress address : Collections.list(ni.getInetAddresses())) {
                    if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
                        return (address.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            //do nothing
        }
        return "Error in get own ip";
    }
}
