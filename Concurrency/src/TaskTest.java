import java.util.concurrent.*;

/**
 * @author Yang
 * @version 1.8
 */
public class TaskTest {
    private static ConcurrentMap<String, Integer> map;
    private static ExecutorService pool;

    public TaskTest() {
        map = new ConcurrentHashMap<>();
        pool = Executors.newCachedThreadPool();
    }

    /**
     * Runnable接口使用
     *
     * @param s key
     * @param i value
     */
    public void runnableTest(String s, int i) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                map.put(s, i);
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }

    /**
     * Callable接口使用
     *
     * @param s key
     * @param i value
     * @return callable返回的值
     */
    public int callableTest(String s, int i) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            map.put(s, i);
            return i * i;
        };
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread t = new Thread(task);
        t.start();
//        阻塞直到get返回值
        Integer integer = task.get();
        return integer;
    }


    /**
     * Future接口示例，表示异步结果
     *
     * @param s key
     * @param i value
     * @return 异步结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Future<Integer> futureTest(String s, int i) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            map.put(s, i);
            return i * i;
        };
        Future<Integer> result = pool.submit(callable);
//        阻塞直到get完成
        result.get();
//        取消
//        result.cancel(true);
        return result;
    }
}

