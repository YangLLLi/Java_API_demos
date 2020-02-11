import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yang
 * @since 1.8
 */
public class ListTest {
    public static void main(String[] args) {
        list();
    }

    /**
     * List接口及实现使用，实现包括ArrayList和LinkedList
     * 处于效率考虑，一般使用ArrayList
     * 由于泛型擦除，故list内元素不能为基本数据类型，需使用包装类型Integer等
     */
    public static void list() {
        List<Integer> list = new ArrayList<>();
//        增删改查
        list.add(3);
        list.add(5);

        System.out.println(list.contains(5));
        System.out.println(list.get(1));
//        常量池技术保证了 4 和 new Integer(4)是同一个对象
        list.add(4);
        list.remove(new Integer(4));

        list.replaceAll((j) -> j + 1);
        for (int i : list) {
            System.out.println(i);
        }

        Integer[] integers = list.toArray(new Integer[]{});
        System.out.println(Arrays.toString(integers));

        List<String> list1 = new ArrayList<>();
        list1.add("hello");
//        常量池技术保证了 两个"hello"为一个对象
        System.out.println(list1.contains("hello"));
    }
}
