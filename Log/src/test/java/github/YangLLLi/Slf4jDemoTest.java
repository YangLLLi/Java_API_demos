package github.YangLLLi;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Junit使用示例
 * @Before @Test @After
 * 好的单元测试覆盖率需要达到80%
 */

public class Slf4jDemoTest {
    private static Slf4jDemo slf4jDemo;
    @BeforeClass
    public static void before() {
        slf4jDemo = new Slf4jDemo();
    }

    @Test
    public void add() {
        assertEquals(5,slf4jDemo.add(1,4));
    }
}