import javax.crypto.*;
import java.security.*;

/**
 * @author Yang
 * @since 1.8
 */

public class RSATest {
    public static void main(String[] args) {
        try {
            rsaTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 非对称算法rsa加密解密
     */
    public static void rsaTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException {
//        被加密对象，aes密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance("aes");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
//        生成键对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");
        keyPairGenerator.initialize(512, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey aPublic = keyPair.getPublic();
        PrivateKey aPrivate = keyPair.getPrivate();
//        加密,公钥
        Cipher rsa = Cipher.getInstance("rsa");
        rsa.init(Cipher.WRAP_MODE, aPublic);
        byte[] wrapedKey = rsa.wrap(secretKey);

//        解密，私钥
        Cipher rsa2 = Cipher.getInstance("rsa");
        rsa2.init(Cipher.UNWRAP_MODE, aPrivate);
        Key aesKey = rsa2.unwrap(wrapedKey, "aes", Cipher.SECRET_KEY);
        System.out.println(aesKey.equals(secretKey));

    }
}
