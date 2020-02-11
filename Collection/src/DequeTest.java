import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Yang
 * @version 1.8
 */
public class DequeTest {
    public static void main(String[] args) {
        dequeTest();
    }

    /**
     *  双端队列deque使用示例，实现类ArrayDeque,LinkedList
     *  实现stack操作，先入后出
     */
    public static void dequeTest() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(4);
        deque.push(5);
        deque.push(3);

        System.out.println(deque.pop());
        System.out.println(deque.peek());

    }
}
