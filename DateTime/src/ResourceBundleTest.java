import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
/**
 * @author Yang
 * @since 1.8
 */
public class ResourceBundleTest {
    public static void main(String[] args) {
        try {
            resourceBundleTest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * resourceBundle使用示例，注意文件编码方式转换ISO-8859-1    utf-8
     * @throws UnsupportedEncodingException
     */
    public static void resourceBundleTest() throws UnsupportedEncodingException {
        ResourceBundle bundle = ResourceBundle.getBundle("test");
        System.out.println(bundle.getLocale());

        byte[] nameBytes = bundle.getString("name").getBytes("ISO-8859-1");
        String name = new String(nameBytes, "utf-8");
        System.out.println(name);
    }
}
