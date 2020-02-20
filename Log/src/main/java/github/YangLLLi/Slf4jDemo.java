package github.YangLLLi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Slf4j使用示例
 * LogBack为实现类 可以在配置文件中配置
 * @author Yang
 */
public class Slf4jDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jDemo.class);

    public static void main(String[] args) {

        demo();
    }

    /**
     * Logger
     */
    public static void demo() {
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
        LOGGER.debug("debug");
        LOGGER.info("ssss {} dddf","haha");
    }

    /**
     * Junit测试用
     * @param i
     * @param j
     * @return
     */
    public int add(int i, int j) {
        return i + j;
    }
}
