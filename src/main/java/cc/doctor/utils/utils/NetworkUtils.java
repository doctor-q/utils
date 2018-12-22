package cc.doctor.utils.utils;

import cc.doctor.framework.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Enumeration;
import java.util.List;

/**
 * network utils
 */
public class NetworkUtils {
    private static final Logger log = LoggerFactory.getLogger(NetworkUtils.class);

    private NetworkUtils() {
    }

    @Nullable
    public static InetAddress getOneUnLoopHost() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!networkInterface.isLoopback() && !networkInterface.isPointToPoint() && !networkInterface.isVirtual() && networkInterface.isUp()) {
                    List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
                    for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                        if (interfaceAddress != null && interfaceAddress.getAddress() instanceof Inet4Address) {
                            return interfaceAddress.getAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            log.error("", e);
        }
        return null;
    }
}
