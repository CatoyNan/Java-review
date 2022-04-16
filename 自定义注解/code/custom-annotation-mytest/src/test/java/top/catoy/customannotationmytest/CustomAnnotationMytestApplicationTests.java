package top.catoy.customannotationmytest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.test.context.SpringBootTest;
import top.catoy.customannotationmytest.annotation.MyTest;

@SpringBootTest
class CustomAnnotationMytestApplicationTests {
    private static final Log logger = LogFactory.getLog(CustomAnnotationMytestApplicationTests.class);

    @MyTest
    public void test() {
        logger.info("test");
    }

}
