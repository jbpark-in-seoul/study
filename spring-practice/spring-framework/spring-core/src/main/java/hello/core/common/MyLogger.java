package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * 1.해당 Logger 는 http 요청 당 하나씩 생성 (스프링 컨테이너 요청 시점)
 * 2.Scope 가 request 일 때, http 요청이 오지 않으면 빈 생성 X
 *   (1) 해결 방법
 *     - ObjectProvider 메서드
 *       + 빈의 생성 시점 지연
 *     - @Scope 속성 추가: proxyMode = ScopedProxyMode.TARGET_CLASS or INTERFACES
 *       + CGLIB를 이용한 가짜 프록시 객체 생성 주입
 *       + 가짜 프록시 객체는 실제 http 요청이 오면 실제 빈을 등록하는 위임 로직이 들어있다.
 * 3.하나의 http 요청일 경우 Controller, Service 에서 각각 호출해도 동일한 빈 반환
 * 4.특징
 *    (1) 프록시 객체 덕분에 클라이언트는 싱글톤 빈을 사용하듯 편리하게 request.scope를 사용할 수 있다.
 *    (2) Provider, proxy 모두의 핵심 아이디어는 실제 객체 조회를 꼭 필요한 시점까지 "지연 처리"한다는 점이다.
 *    (3) 웹 스코프가 아니어도 프록시는 사용 가능하다
 *    (4) 어노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체 가능하다.
 *        -> 다형성과 DI 컨테이너가 가진 장점
 * 5.주의점
 *    (1) 싱글톤과는 다르게 동작하기 때문에 주의 해야 한다.
 *    (2) 꼭 필요한 곳에 최소한으로만 사용해야 한다: 유지보수
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
