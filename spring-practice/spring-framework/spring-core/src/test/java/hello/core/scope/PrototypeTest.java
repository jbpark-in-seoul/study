package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ## 초기화(init) 시점
 *  - 프로토타입 빈: 스프링 컨테이너에 빈 조회 시
 *
 * ## 빈 생명주기
 *  - 스프링 컨테이너 생성 → 스프링 빈 생성 → 의존관계 주입 → 초기화 콜백 → 사용 → 소멸 전 콜백 → 스프링 종료
 * ## 싱글톤 vs 프로토 타입
 *  # 싱글톤
 *    - 스프링 컨테이너 생성 시, 빈 생성
 *      + 스프링 컨테이너에 요청 마다 동일한 자원 사용
 *    - 스프링 컨테이너가 자원 관리(초기화 및 소멸)
 *  # 프로토 타입
 *    - 스프링 빈 조회 시, 빈 생성
 *      + 스프링 컨테이너에 요청 마다 새로운 자원 생성
 *    - 스프링 컨테이너가 빈 생성~초기화까지만 관리 -> 클라이언트에 위임(직접 소멸 처리)
 */
public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        assertThat(bean1).isNotSameAs(bean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
