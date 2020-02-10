import javax.crypto.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESTest {
    public static void main(String[] args) {
        try {
            aesTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 对称加密aes算法使用
     * 包括生成secretKey,使用aes算法进行加密
     */
    public static void aesTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
//        获得key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("aes");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
//        准备加密的数据
        byte[] input = "hello".getBytes();
//        进行加密
        Cipher aes = Cipher.getInstance("aes");
        aes.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptBytes = aes.doFinal(input);
//        base64编码传输,模拟数据传输过程
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(encryptBytes);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encode);
//        解密
        Cipher aes2 = Cipher.getInstance("aes");
        aes2.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytes = aes2.doFinal(decode);
        System.out.println(new String(bytes));
    }
}
