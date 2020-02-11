import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Yang
 * @since 1.8
 */

public class MessageDigestTest {
    public static void main(String[] args) {
        try {
            digestTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 消息摘要MessageDigest示例
     */
    public static void digestTest() throws NoSuchAlgorithmException {
        String message = "hello";
        MessageDigest md5 = MessageDigest.getInstance("md5");
        byte[] digest = md5.digest(message.getBytes());
        System.out.println(Arrays.toString(digest));
    }
}
