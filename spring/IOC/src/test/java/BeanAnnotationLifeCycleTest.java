import annotationLifeCycle.impl.UserServiceImplAnnotationLifeCycle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext3.xml"})
public class BeanAnnotationLifeCycleTest {
    @Autowired
    private UserServiceImplAnnotationLifeCycle u;
    @Test
    public void test() {
        u.getAllInfo();
    }
}
