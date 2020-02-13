import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AIO编程
 *
 * BIO为阻塞等待连接，再阻塞等待数据，最后操作 *
 * NIO为Selector多路复用，同步非阻塞；
 * 多个Channel注册到Selector上，selector同步获得任意accept连接；这些连接再注册到selector上，同步获得任意可读或可写连接
 * AIO则是提出数据需求，处理数据即可（等待连接，等待数据由操作系统底层实现完成）
 *
 */

public class AsynchronousServerSocketChannelTest {
    public static void main(String[] args) {
        try {
            asynchronousServerSocketChannelTest();
            Thread.sleep(100000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @throws IOException
     */
    public static void asynchronousServerSocketChannelTest() throws IOException {
        System.out.println("进入ServerSocket");
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8089));

//        连接完成后的处理函数，处理AsynchronousSocketChannel
        CompletionHandler<AsynchronousSocketChannel, Object> handler = new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
//               继续下一个监听，需在此处
                serverSocketChannel.accept(attachment, this);
                ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                byteBuffer.put("hello".getBytes());
                byteBuffer.flip();
//                通过CompletionHandler异步写
                result.write(byteBuffer, attachment, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result1, Object attachment) {
                        System.out.println(result1);
                        try {
                            result.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        System.out.println("连接出错"+exc.getMessage());
                    }
                });
                System.out.println("退出socket");
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("连接出错"+exc.getMessage());
            }
        };

//      会直接返回，此处的attachment会传递到handler中
       serverSocketChannel.accept(null,handler);
        System.out.println("退出ServerSocket");

    }
}
