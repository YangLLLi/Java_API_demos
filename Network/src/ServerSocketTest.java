import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author Yang
 * @since 1.8
 */

public class ServerSocketTest {
    public static void main(String[] args) {
        try {
            serverSocketTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务器serverSocket使用，包括连接响应，输出
     * 多线程处理
     * @throws IOException
     */
    public static void serverSocketTest() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8089)) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            while (true) {
                //阻塞直到有连接
//                不能使用try,否则可能在output未完成前socket自动关闭
                Socket accept = serverSocket.accept();
                Runnable runnable = () -> {
                    try (OutputStream outputStream = accept.getOutputStream()) {
                        PrintWriter out = new PrintWriter(outputStream);
                        out.println("connected");
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
                executorService.submit(runnable);
            }
        }
    }
}


