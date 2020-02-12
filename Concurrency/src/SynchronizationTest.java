import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Yang
 * @version 1.8
 */
public class SynchronizationTest {
    /**
     * 同步主要用于field变量
     * 对于会在多个线程中使用的类来说（线程逃逸），其field做同步保护是必要的，这样的类被称为线程安全的类
     * 主要有以下几种方式保证同步
     * 1. 声明为final,不可变的变量（对基本变量成立），注意类类型的final变量并非真的不可变，只是其引用不可变
     * 2. 声明为volatile变量，保证可见性，不保证原子性
     * <p>
     * 3. 使用原子类 atomicInteger等
     * 4. 使用并发集合类 ConcurrentHashMap、BlockingQueue等
     * 5. 使用ThreadLocal变量
     * <p>
     * 6.使用锁保证同步 Synchronized wait signalAll; ReentrantLock Condition
     */
    public static void main(String[] args) {
        AtomicTest at = new AtomicTest();
        at.m1();
    }
}

/**
 * final类
 */
class FinalTest {
    private final int i = 4;
    private final String s = "abc";
    //    不适用于多线程，只是引用不变
    private final List<Integer> list = new ArrayList<>();
}

/**
 * 使用volatile
 */
class VolatileTest {
    //    需保证 volatile变量在多线程的使用中不涉及非原子操作，如自增、先检查后执行等
    private volatile int i;
    private volatile List<Integer> list;

    public void m1() {
        i = 3;
//        非原子操作，不适用于多线程
        i++;
    }
}

/**
 * 使用atomic类
 */
class AtomicTest {
    //非阻塞同步
    private AtomicInteger ai;

    public AtomicTest() {
        this.ai = new AtomicInteger();
    }

    public void m1() {
        ai.set(1);
        int i = ai.incrementAndGet();
        System.out.println(i);
        int x = ai.addAndGet(4);
        System.out.println(x);
        int i1 = ai.updateAndGet((a) -> a * 2 + 1);
        System.out.println(i1);
    }
}

/**
 * 使用并发集合
 */
class ConcurrentCollectionTest {
    private List<Integer> list;
    private BlockingQueue<Integer> bq;
    private Deque<Integer> deque;
    private ConcurrentMap<String, Integer> concurrentMap;

    public ConcurrentCollectionTest() {
        this.list = new CopyOnWriteArrayList<>();
        this.bq = new ArrayBlockingQueue<Integer>(100);
//        优先队列
//        this.bq = new PriorityBlockingQueue<>();
        this.deque = new ConcurrentLinkedDeque<>();
        this.concurrentMap = new ConcurrentHashMap<>();
//        相当于treeMap的作用
//        this.concurrentMap = new ConcurrentSkipListMap<>();
    }

    public void m1() throws InterruptedException {
        list.add(3);
//        操作不成功时会发生阻塞
        bq.put(4);
        bq.take();
//
        deque.offer(4);
//
        concurrentMap.put("aa", 123);
        int oldValue, newValue;
        do {
            oldValue = concurrentMap.get("aa");
            newValue = oldValue + 4;
        } while (!concurrentMap.replace("aa", oldValue, newValue));
//        或者
        concurrentMap.merge("aa", 4, (a, b) -> a + b);
    }
}

/**
 * 使用ThreadLocal类
 */
class ThreadLocalTest {
    //    维护了一个ThreadLocalMap数据结构，key为Thread，value为值
    private ThreadLocal<Integer> localInt;

    public ThreadLocalTest() {
        this.localInt = new ThreadLocal<>();
    }

    public void m1(int i) {
        localInt.set(i);
    }

    public int m2() {
        return localInt.get();
    }
}

/**
 * 同步关键词使用示例
 * Synchronized wait notifyAll
 */
class SynchronizedTest {
    private int[] account = new int[]{200, 100};

//从账户from转到账户to,事务处理
    public synchronized void transfer(int from,int to,int i) throws InterruptedException {
        while (account[from] < i) {
            wait();
        }
        account[from] = account[from] - i;
        account[to] = account[to] + i;
        notifyAll();
    }
    public synchronized int get(int index) {
        return account[index];
    }
}

/**
 * 锁使用示例
 * ReentrantLock Condition
 */
class LockTest {
    private int[] account = new int[]{200, 100};
//    可重入锁和条件变量
    private Lock bankLock = new ReentrantLock();
    private Condition sCondition=bankLock.newCondition();

    public void transfer(int from, int to, int i) throws InterruptedException {
        try {
            bankLock.lock();
            while (account[from] < i) {
                sCondition.await();
            }
            account[from] -= i;
            account[to] += i;
            sCondition.signalAll();
        } finally {
//           必须手动unlock
            bankLock.unlock();
        }
    }

    public int get(int index) {
        try {
            bankLock.lock();
            return account[index];
        }finally {
            bankLock.unlock();
        }
    }
}



