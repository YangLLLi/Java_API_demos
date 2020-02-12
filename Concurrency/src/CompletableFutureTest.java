import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

/**
 * @author Yang
 * @since 1.8
 */

public class CompletableFutureTest {
    private static ConcurrentMap<String, Integer> map=new ConcurrentHashMap<>();

    /**
     * 实现真正的异步编程，Future中获取结果为同步
     * CompletableFuture可在结果完成后异步进行下一步运算，默认ForkJoinPool.commonPool() 线程池
     *
     * @param s key
     * @param i value
     * @return 异步结果
     */
    public CompletableFuture<Integer> completableFutureTest(String s, int i) {
//        Runnable runnable = () -> {
//            map.put(s, i);
//        };
//        CompletableFuture.runAsync(runnable);
//        CompletableFuture.runAsync(runnable, pool);
        Callable<Integer> callable = () -> {
            map.put(s, i);
            return i * i;
        };
//        运行异步任务
        CompletableFuture<Integer> completableFuture1
                = CompletableFuture.<Integer>supplyAsync((Supplier<Integer>) callable);
//        异步任务接下来的运算
        CompletableFuture<Double> applyTask = completableFuture1.<Double>thenApply((a) -> a + 1.2);
//        applyTask.cancel(true);
//        applyTask.get();
//        applyTask.complete();
//      异步任务处理结果及异常
        CompletableFuture<Integer> handle = completableFuture1.handle((a, e) -> {
            e.printStackTrace();
            return a + 1;
        });

        CompletableFuture<Integer> completableFuture2
                = CompletableFuture.<Integer>supplyAsync(() -> {
            map.put(s, i);
            return i + i;
        });
//        两个异步任务完成之后进行计算，返回异步结果
        CompletableFuture<Integer> combineTask = completableFuture1.thenCombine(completableFuture2, Integer::sum);
//        completableFuture1.applyToEither();
        return combineTask;
    }
}
