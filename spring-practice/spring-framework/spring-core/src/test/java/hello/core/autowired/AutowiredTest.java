package hello.core.autowired;

import hello.core.SampleBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.lang.reflect.Member;
import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false) // default true, 수정자가 없으면 메서드 자체가 실행되지 않음.
        public void setNoBean1(SampleBean noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable SampleBean noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<SampleBean> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
