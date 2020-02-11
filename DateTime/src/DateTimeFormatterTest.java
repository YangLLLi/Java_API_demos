import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
/**
 * @author Yang
 * @since 1.8
 */

public class DateTimeFormatterTest {
    public static void main(String[] args) {
        dateTimeFormatterTest();
    }
    /**
     * DateTimeFormatter使用示例
     */
    public static void dateTimeFormatterTest() {
//        iso格式
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ISO_LOCAL_DATE;
        String format = formatter.format(now);
        System.out.println(format);
//        本地格式
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        System.out.println(formatter1.format(now));
//        自定义格式
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(formatter2.format(dateTime));
    }
}
