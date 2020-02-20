import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang
 * @since 1.8
 */

public class FileChannelTest {
    public static void main(String[] args) {
        try {
            fileChannelTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * FileChannel、ByteBuffer使用示例
     * 包括了内存映射文件、文件加锁、文件的随机读写
     */
    public static void fileChannelTest() throws IOException {
        Path path = Paths.get("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\test.txt");
//        fileChannel, byteBuffer
        FileChannel fileChannel = FileChannel.open(path);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));

        byte[] bytes = new byte[200];
//        换读
        byteBuffer.flip();
//        若byte数组大小大于buffer的limit,直接使用get(bytes)方法会导致错误
        byteBuffer.get(bytes,0,byteBuffer.remaining());
        System.out.println(new String(bytes));

//       文件加锁
//        try(FileLock lock = fileChannel.lock()) {
//
//        }

//       内存映射文件,针对大文件
        /*
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        List<Byte> byteList = new ArrayList<>();
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            byteList.add(b);
        }
        System.out.println(new String(bytes));
        System.out.println(byteBuffer.position());
        */
    }
}
