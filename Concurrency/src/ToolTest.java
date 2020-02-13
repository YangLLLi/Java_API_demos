import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 线程间协作工具类
 *
 * @author Yang
 * @since 1.8
 */
public class ToolTest {
    public static void main(String[] args) {
        ToolTest toolTest = new ToolTest();
//        toolTest.semaphoreTest();
//        toolTest.countDownLatchTest();
        toolTest.cycleBarrierTest();
    }

    private static Semaphore semaphore = new Semaphore(2);
    private static CountDownLatch latch = new CountDownLatch(2);
//    数目减少为0时运行runnable内容
    private static CyclicBarrier barrier = new CyclicBarrier(2, () -> {
        System.out.println("完成cycle");
    });

    /**
     * 信号量Semaphore使用示例
     * acquire release
     */
    public void semaphoreTest() {
        Runnable runnable = () -> {
            try {
//                等待permit
                semaphore.acquire();
                System.out.println("获得信号");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(semaphore.availablePermits());
                System.out.println("释放信号");
                semaphore.release();
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }

    /**
     * CountDownLatch使用示例
     * countDown await
     */
    public void countDownLatchTest() {
        Runnable runnable = () -> {
            System.out.println("任务");
            latch.countDown();
            System.out.println(latch.getCount());
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        System.out.println("main");
        try {
//            等待countDownLatch变为0
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成");
    }

    /**
     * CycleBarrier使用示例
     * await  减1并等待
     */
    public void cycleBarrierTest() {
        Runnable runnable=()->{
            System.out.println("开始");
            try {
//                数目减1,并在此等待
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(barrier.getNumberWaiting());
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }

}
