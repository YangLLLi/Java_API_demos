import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Executors ExecutorService使用
 *
 * @author Yang
 * @since 1.8
 */

public class ExecutorTest {
    //    ThreadPoolExecutor的实现
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    private static int[] account = new int[]{200, 100};

    /**
     * 线程池执行任务
     */
    public static void executorTest() {
        ExecutorTest obj = new ExecutorTest();
        Callable<Integer> callable = () -> {
//            线程为timed_waiting
            Thread.sleep(10);
//            若有其他线程先获得锁，则此处为blocked状态
            obj.transform(0, 1, 50);
            return obj.get();
        };
//        提交任务，线程为new runnable态
        Future<Integer> future = pool.submit(callable);
        try {
//            当前线程waiting 类似Thread中的join
            Integer integer = future.get();

        } catch (InterruptedException e) {
            System.out.println("中断");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        通过中断方式取消
        future.cancel(true);

        pool.shutdown();
    }

    /**
     * 任务集合
     */
    public static void executorCompletionServiceTest() {
        Callable<Integer> callable = () -> 5;
        //        任务集 不好的用法
        List<Callable<Integer>> list = new ArrayList<Callable<Integer>>() {{
            add(callable);
        }};
        try {
            List<Future<Integer>> futures = pool.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        任务集，好的做法
        ExecutorCompletionService<Integer> ecs = new ExecutorCompletionService(pool);
        for (Callable<Integer> c : list) {
            ecs.submit(c);
        }
        for (int i = 0; i < list.size(); i++) {
            try {
//                阻塞直到有任意一个任务完成
                int res = ecs.take().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void transform(int from, int to, int j) throws InterruptedException {
        while (account[from] < j) {
//            此处线程状态为waiting,可中断
            wait();
//            timed_waiting
//            wait(100);
        }
        account[from] -= j;
        account[to] += j;
        notifyAll();
    }

    public synchronized int get() {
        return account[0];
    }

}

/**
 * Thread类使用示例
 */
class ThreadTest {
    private static int[] account = new int[]{200, 100};

    /**
     * 主要涉及线程状态new runnable blocked waiting timed_waiting terminated
     */
    public static void threadStatusTest() {
        ThreadTest obj = new ThreadTest();
        int j = 6;
        Runnable runnable = () -> {
//            若有其他线程先获得锁，则此处为blocked状态
            try {
                obj.transform(0, 1, 50);
            } catch (InterruptedException e) {
                System.out.println("中断");
            }
            System.out.println(obj.get());
        };
        Thread t = new Thread(runnable);
//        线程为new状态，可转换为runnable状态
        t.start();
        try {
//            当前线程waiting状态，等t运行完毕
            t.join();
        } catch (InterruptedException e) {
            System.out.println("中断");
        }

        try {
//           当前线程 timed_waiting
            Thread.sleep(100);
            t.join(100);
        } catch (InterruptedException e) {
            System.out.println("中断");
        }

//        设置中断，可让waiting和timed_waiting状态的线程中断
        t.interrupt();
    }

    /**
     * Thread类方法
     */
    public static void threadMethod() {
        Thread.currentThread();
        Thread.dumpStack();
        ;
//        设置当前线程未捕获异常的处理器
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });

        Thread t = new Thread();
//        设置为后台进程
        t.setDaemon(true);
//        设置优先级，用处不大
        t.setPriority(Thread.MIN_PRIORITY);
    }

    public synchronized void transform(int from, int to, int j) throws InterruptedException {
        while (account[from] < j) {
//            此处线程状态为waiting,可中断
            wait();
//            timed_waiting
//            wait(100);
        }
        account[from] -= j;
        account[to] += j;
        notifyAll();
    }

    public synchronized int get() {
        return account[0];
    }
}
