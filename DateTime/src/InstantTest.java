import java.time.Duration;
import java.time.Instant;
/**
 * @author Yang
 * @since 1.8
 */

public class InstantTest {
    public static void main(String[] args) {
        instantTest();
    }
    /**
     * instant、duration类使用
     * instant实现Temporal、TemporalAdjuster
     * duration实现TemporalAmount
     * 时刻，自1970.01.01起算
     */
    public static void instantTest() {
//        时刻
        Instant epoch = Instant.EPOCH;
        Instant now = Instant.now();
        Instant next = now.plusSeconds(120);
        long epochMilli = next.toEpochMilli();
        System.out.println(epochMilli);
//       时间间隔
        Duration duration = Duration.between(now, next);
        long seconds = duration.getSeconds();
        System.out.println(seconds);
    }

}
