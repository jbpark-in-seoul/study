package hello.core.policy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SamplePolicy1 implements SamplePolicy {
    @Override
    public void print() {
        System.out.println("first sample policy");
    }
}
