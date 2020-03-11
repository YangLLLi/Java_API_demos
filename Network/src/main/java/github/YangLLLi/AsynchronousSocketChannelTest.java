package github.YangLLLi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author Yang
 * @version 1.8
 */
public class AsynchronousSocketChannelTest {
    public static void main(String[] args) {
        try {
            asynchronousSocketChannelTest();
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void asynchronousSocketChannelTest() throws IOException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8089));
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
//        异步读
        socketChannel.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println(result);
                try {
                    System.out.println(new String(byteBuffer.array()));
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("失败");
            }
        });
        System.out.println("退出");
    }
}
