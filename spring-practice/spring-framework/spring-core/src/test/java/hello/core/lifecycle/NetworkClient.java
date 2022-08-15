package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * # 빈 생명주기 콜백
 * ## 스프링 빈 생명주기
 *  - 스프링 컨테이너 생성 → 스프링 빈 생성 → 의존관계 주입 → 초기화 콜백 → 사용 → 소멸 전 콜백 → 스프링 종료
 *
 * ### 객체의 생성과 초기화 분리
 *  > SRP와 관련하여 객체 생성 단계에서는 단순한 값 초기화 작업 등이 아닌 무거운 작업에 대해서는 따로 구분하여 만드는 것이 유지 관리하기 좋다
 *
 * ### 스프링 빈 생명주기 지원 콜백
 *  - 인터페이스(InitializingBean, DisposableBean)
 *  - 설정 정보에 초기화 메서드, 종료 메서드 지정
 *  - @PostConstruct, @PreDestory 애노테이션 지원
 */
public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connecct: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    public void disconnect() {
        System.out.println("close: " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
