import java.util.Arrays;

/**
 * @author Yang
 * @version 1.8
 */
public class ArraysTest {
    /**
     * array类使用说明
     */
    public static void arraysTest() {
        int[] ints = new int[10];
        Arrays.fill(ints,3);
        int[] ints1 = Arrays.copyOf(ints, 2);

        System.out.println(Arrays.toString(ints));
        Arrays.hashCode(ints);
        Arrays.equals(ints,ints1);
//        Arrays.deepEquals();
//        Arrays.deepHashCode();
//        Arrays.deepToString();

        Arrays.sort(ints);
        Arrays.binarySearch(ints, 4);
//        视图
        Arrays.asList(ints);
    }
}
