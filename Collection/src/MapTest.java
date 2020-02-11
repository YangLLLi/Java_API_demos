import java.util.*;

/**
 * @author Yang
 * @version 1.8
 */
public class MapTest {
    public static void main(String[] args) {
//        hashMapTest();
//        treeMapTest();
//        enumMapTest();
        bitSetTest();
    }

    /**
     * hashMap实现，哈希表，需要key元素实现hashcode方法
     * linkedHashMap可进一步保存插入顺序
     */
    public static void hashMapTest() {
        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = new LinkedHashMap<>();

//        增查
        map.put("aa", "qqq");
        map.put("bb", "zzz");
        System.out.println(map.get("aa"));
//        改
        map.put("aa", "qq");
        map.replace("aa", "qp");
        System.out.println(map.get("aa"));
//        删
        map.remove("aa");
        System.out.println(map.get("aa"));
    }

    /**
     * treeMap实现，红黑树，需要key元素实现Comparable接口
     */
    public static void treeMapTest() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("aa", 1);
        map.put("bb", 2);

        Set<String> keySet = map.keySet();
        Collection<Integer> values = map.values();
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
//        更新map
        for (String s : map.keySet()) {
//            a为value,b为get(s)
            map.merge(s, 1, (a, b) -> a + b);
//            a为key,b为get(a)
            map.compute(s, (a, b) -> b);
        }
        System.out.println(values);
    }

    /**
     * key为enum类型
     */
    public static void enumMapTest() {

        EnumMap<Season, Integer> map = new EnumMap<Season, Integer>(Season.class);
        map.put(Season.SPRING, 1);
        System.out.println(map.get(Season.SPRING));
    }

    /**
     * 增加一个BitSet的示例
     */
    public static void bitSetTest() {
        BitSet bitSet = new BitSet();
        bitSet.set(3);
        bitSet.set(5);
        boolean b = bitSet.get(3);
        System.out.println(b);
        System.out.println(bitSet.get(4));
    }


}

enum Season{
    SPRING,SUMMER,AUTUMN,WINTER;
}
