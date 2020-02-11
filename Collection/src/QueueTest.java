import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Yang
 * @since 1.8
 */
public class QueueTest {
    public static void main(String[] args) {
//        queue();
        priorityQueue();
    }

    /**
     * Queue接口及实现使用示例，实现包括ArrayDeque和LinkedList
     * 先入先出
     * 使用循环数组实现ArrayDeque即可
     */
    public static void queue() {
        Queue<Integer> queue = new ArrayDeque<>();
//        增删改查
        boolean suc = queue.offer(4);
        queue.offer(5);

        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    /**
     * 优先队列，堆实现
     * 元素需要实现Comparable接口
     */
    public static void priorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>() {{
            add(4);
            add(5);
            add(3);
        }};

        System.out.println(pq.poll());
        System.out.println(pq.poll());

        PriorityQueue<Integer> pq1= new PriorityQueue<>(Comparator.reverseOrder());
        pq1.offer(1);
        pq1.offer(4);
        pq1.offer(2);
        System.out.println(pq1.poll());
    }
}
