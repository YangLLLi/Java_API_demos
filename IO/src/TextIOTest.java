import java.io.*;
import java.util.Scanner;

/**
 * 传统IO类使用示例，包括inputStream、outputStream
 * @author Yang
 * @since 1.8
 */
public class TextIOTest {
    public static void main(String[] args) {
        try {
            textIOTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文本文件的输入输出
     * Scanner
     * PrintWriter
     * @throws IOException
     */
    public static void textIOTest() throws IOException {
        try (InputStream inputStream = new FileInputStream("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\test.txt")) {
            Scanner scanner = new Scanner(new BufferedInputStream(inputStream));
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                sb.append(System.lineSeparator());
            }
            System.out.println(sb.toString());
        }

        try (OutputStream outputStream = new FileOutputStream("D:\\Yang\\Projects\\IdeaProjects\\JavaAPI\\IO\\src\\test_out.txt")) {
            PrintWriter out = new PrintWriter(new BufferedOutputStream(outputStream), true);
            out.println("hello 1st");
            out.println("2nd");
        }

    }
}
