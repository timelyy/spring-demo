package spring;

import com.example.demo.controller.UserController;
import com.example.demo.controller.dispatchar.DisPatCherController;
import com.example.demo.spring.core.container.BeanContainer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IocTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    public static void init(){
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("测试装载ioc")
    @Test
    @Order(1)
    public void setIoc() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean isload = beanContainer.isload();
        Assertions.assertEquals(false,isload);
        Set<Class<?>> classes = beanContainer.setIocContainerInit("com.example.demo");
        boolean isloadnew = beanContainer.isload();
        Assertions.assertEquals(true,isloadnew);
    }

    @DisplayName("测试工具类")
    @Test
    @Order(2)
    public void TestIocMethod() throws IllegalAccessException {
        beanContainer.dependencyInjection();
        UserController byClass = (UserController) beanContainer.getByClass(UserController.class);
        System.out.println(byClass);
        byClass.testservice();
    }
}
