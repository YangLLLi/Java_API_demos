package github.YangLLLi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
/**
 * @author Yang
 * @since 1.8
 */

public class ServerSocketChannelTest {
    public static void main(String[] args) {
        try {
            serverSocketChannelTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ServerSocketChannel、Selector、SelectionKey使用
     *
     * @throws IOException
     */
    public static void serverSocketChannelTest() throws IOException {
//        打开serverSocketChannel,bind端口,设置非阻塞
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress(8089));
            serverSocket.configureBlocking(false);
//        注册到selector，代替原来的阻塞accept
            Selector selector = Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
//            看selector中有没有accept等 的channel
                if (selector.select() == 0) {
                    continue;
                }
//            selector中满足accept等的selectionKey
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
//                accept的channel注册到read和write上，代替原来的阻塞读写
                    if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel accept = channel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
//
                    } else if (key.isReadable()) {
//                      省略，参考写操作
                    } else if (key.isWritable()) {
//                      此处还可考虑另开线程处理
                        ByteBuffer writeBuffer = ByteBuffer.allocate(20);
                        writeBuffer.put("hello".getBytes());
                        writeBuffer.flip();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuffer);

                        socketChannel.close();
                    }
//                    必须要有，避免重复处理
                    iterator.remove();
                }
            }
        }

    }
}
