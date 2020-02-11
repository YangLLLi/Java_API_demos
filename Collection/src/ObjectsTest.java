import java.util.Comparator;
import java.util.Objects;

/**
 * @author Yang
 * @version 1.8
 */
public class ObjectsTest {
    /**
     * Objects类使用示例
     */
    public static void objectsTest() {
        String a = "aa";
        String b = "bb";
        Objects.equals(a, b);
        Objects.hashCode(a);
        Objects.toString(a);
        Objects.deepEquals(a, b);
        Objects.compare(a, b, Comparator.comparingInt(String::length));

        Objects.requireNonNull(a);
        Objects.isNull(a);
    }
}
