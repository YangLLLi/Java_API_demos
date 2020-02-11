import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
/**
 * @author Yang
 * @since 1.8
 */

public class LocaleTest {
    public static void main(String[] args) {
//        localeTest();
        formatterTest();
    }

    /**
     * 本地化测试，locale的使用
     */
    public static void localeTest() {
        Locale locale1 = Locale.forLanguageTag("zh-CN");
        Locale china = Locale.CHINA;
        System.out.println(locale1.equals(china));
        System.out.println(china.getCountry());
        System.out.println(china.getDisplayCountry());
        System.out.println(china.getDisplayLanguage(Locale.FRANCE));
    }

    /**
     * 本地格式化输出，NumberFormat,DateTimeFormatter,MessageFormat
     */
    public static void formatterTest() {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.JAPAN);
        String number = numberInstance.format(1234554321);
        System.out.println(number);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withLocale(Locale.GERMANY);
        String date = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(date);

        String format = MessageFormat.format("{0} is {1}", "苹果", "水果");
        System.out.println(format);

    }
}
