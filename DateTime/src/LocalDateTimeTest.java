import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
/**
 * @author Yang
 * @since 1.8
 */

public class LocalDateTimeTest {
    public static void main(String[] args) {
        localDateTimeTest();
    }
    /**
     * LocalDate、LocalTime、LocalDateTime;实现 Temporal、TemporalAdjuster
     * period使用实现TemporalAmount
     */
    public static void localDateTimeTest() {
//        localDate
        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.of(2000, Month.OCTOBER, 10);
        int dayOfMonth = now.getDayOfMonth();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
//        加减月份
        LocalDate plusMonths = now.plusMonths(1);
        LocalDate date1 = now.withMonth(Month.JANUARY.getValue());
//        时间间隔
        Period between = Period.between(birthday, now);
//
//        LocalTime
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(24, 0, 0);
        long until = localTime.until(localTime1, ChronoUnit.HOURS);
        System.out.println(until);
//
//        类型转换  java.sql.date  java.sql.time  java.sql.timestamp
        Date date = Date.valueOf(now);
        Time time = Time.valueOf(localTime);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        date.toLocalDate();
        time.toLocalTime();
        timestamp.toLocalDateTime();
    }

}
