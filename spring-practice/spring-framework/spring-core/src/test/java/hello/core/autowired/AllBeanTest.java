package hello.core.autowired;


import hello.core.policy.SamplePolicy;
import hello.core.AutoAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, SampleService.class);

        SampleService SampleService = ac.getBean(SampleService.class);
        SampleService.printService("samplePolicy2");
    }

    static class SampleService {
        private final Map<String, SamplePolicy> policyMap;
        private final List<SamplePolicy> policies;

        @Autowired
        public SampleService (Map<String, SamplePolicy> policyMap, List<SamplePolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public void printService(String serviceCode) {
            policyMap.get(serviceCode).print();
        }
    }
}
