package hello.core;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class SampleBean {
    private String name;
    private int age;

    public static void main(String[] args) {
        SampleBean bean = new SampleBean();

        bean.setName("sample");
        bean.setAge(1);

        System.out.println("bean = " + bean);
    }
}
