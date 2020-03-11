package github.YangLLLi;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
/**
 * @author Yang
 * @since 1.8
 */

public class SocketTest {
    public static void main(String[] args) {
        try {
//            inetAddressTest();
            socketTest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * InetAddress类使用示例，请求dns服务器获得域名的ip地址
     *
     * @throws UnknownHostException
     */
    public static void inetAddressTest() throws UnknownHostException {
        InetAddress tencent = InetAddress.getByName("www.tencent.com");
        String hostName = tencent.getHostName();
        String hostAddress = tencent.getHostAddress();
        byte[] address = tencent.getAddress();
        System.out.println(hostName + "+" + hostAddress);
        System.out.println(Arrays.toString(address));
    }

    /**
     * 客户端socket使用，包括连接，输入
     *
     * @throws IOException
     */
    public static void socketTest() throws IOException {
        Socket socket = new Socket();
//            阻塞直到连接上
        socket.connect(new InetSocketAddress("localhost", 8089));
        try (InputStream inputStream = socket.getInputStream()) {
            Scanner in = new Scanner(inputStream);
//                阻塞直到有输入
            while (in.hasNext()) {
                System.out.println(in.next());
            }
        }

    }
}


