import java.util.Properties;

/**
 * @author Yang
 * @since 1.8
 */
public class LangTest {
    public static void main(String[] args) {
        mathTest();
    }

    /**
     * System类
     */
    public static void systemTest() {
//        环境变量
        String path = System.getenv("Path");
        System.out.println(path);
//        系统属性
        Properties properties = System.getProperties();
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("path.separator"));

        System.lineSeparator();
//        当前时间
        System.currentTimeMillis();
//        调用第三方库
//        System.load();
    }

    /**
     * Integer Double String类
     */
    public static void langTest() {
        Integer.hashCode(10);
        Integer.toString(10);
        Integer.compare(1, 2);

        Integer.max(2, 3);
        Integer.valueOf("3");
        Integer.parseInt("3");

        double naN = Double.NaN;
        double maxValue = Double.MAX_VALUE;

        String s = String.valueOf(10);
        String s1 = new String(new byte[10]);
        String.format("%s", "hello");

        boolean aaa = s.equals("aaa");
        int i = s.hashCode();
        String substring = s.substring(1, s.length() - 1);
        int aaa1 = s.compareTo("aaa");
        char c = s.charAt(1);
        String s2 = s.toUpperCase();
//        字符串匹配
        s=s.replace("aa", "b");
        int ab = s.lastIndexOf("ab");
        boolean ab1 = s.contains("ab");
        int avc = s.indexOf("avc");
    }

    /**
     * Math类
     */
    public static void mathTest() {
//        幂运算和开方运算
        double pow = Math.pow(2, 3);
        double sqrt = Math.sqrt(2);
        double cbrt = Math.cbrt(8);
//        指数和对数
        double exp = Math.exp(2);
        double log = Math.log(10);
//        三角和反三角
        double sin = Math.sin(Math.PI / 2);
        double asin = Math.asin(1);
        double v = Math.toDegrees(asin);
//        绝对值
        int abs = Math.abs(-1);
//        地板 天花板
        double floor = Math.floor(3.4);
        double ceil = Math.ceil(3.4);
        System.out.println(floor);
        System.out.println(ceil);
//        四舍五入
        long round = Math.round(1.1);
//        0到1的随机数
        double random = Math.random();
    }
}
