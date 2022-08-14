package hello.core.policy;

import org.springframework.stereotype.Component;

@Component
public class SamplePolicy2 implements SamplePolicy {
    @Override
    public void print() {
        System.out.println("second sample policy");
    }
}
