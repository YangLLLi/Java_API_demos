import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Yang
 * @since 1.8
 */

public class CollectionsTest {
    /**
     * 包装类函数，返回集合的视图
     */
    public static void collectionViewTest() {
        List<Integer> list = new ArrayList<>();
//        同步视图
        List<Integer> synchronizedList = Collections.synchronizedList(list);
//        检查视图
        List<Integer> list1 = Collections.checkedList(list, Integer.class);
//        不可变视图
        List<Integer> list2 = Collections.unmodifiableList(list);
//        空、单例
        List<Object> emptyList = Collections.emptyList();
        List<Integer> singletonList = Collections.singletonList(new Integer(4));
    }

    /**
     * Collections类涉及的其他函数
     * 排序、查找等
     */
    public static void collectionsTest() {
        List<Integer> list = new ArrayList<>();
        List<Integer> listC = new ArrayList<>();
        Collections.shuffle(list);
        Collections.sort(list, Comparator.reverseOrder());
        Collections.binarySearch(list, 4);

        Collections.fill(list, 3);
        Collections.copy(list,listC);

        Collections.frequency(list, 3);
        Collections.max(list);
        Collections.min(list);
    }

}
