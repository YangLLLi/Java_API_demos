import java.io.*;
import java.util.Arrays;

/**
 * @author Yang
 * @since 1.8
 */

public class BinaryIOTest {
    public static void main(String[] args) {
        try {
            binaryIOTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 二进制文件的读写
     * DataInputStream
     * DataOutputStream
     */
    public static void binaryIOTest() throws IOException {
        try (OutputStream outputStream = new FileOutputStream("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\t2.dat")) {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(outputStream));
            out.writeInt(10);
            out.write("hello".getBytes());
            out.flush();
        }

        try (InputStream inputStream = new FileInputStream("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\t2.dat")) {
            DataInputStream in = new DataInputStream(new BufferedInputStream(inputStream));
            int i = in.readInt();
            byte[] bytes = new byte[20];
            in.read(bytes);
            System.out.println(i);
            System.out.println(Arrays.toString(bytes));
            System.out.println(new String(bytes));
        }

    }
}
