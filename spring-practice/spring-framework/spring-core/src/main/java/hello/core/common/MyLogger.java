package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * - 해당 Logger 는 http 요청 당 하나씩 생성 (스프링 컨테이너 요청 시점)
 * - Controller 에서는 Scope 가 request 일 때, ObjectProvider 를 이용하여 빈의 생성 시점 지연 필요
 * - 같은 http 요청이면, Controller, Service 에서 각각 호출해도 동일한 빈 반환
 */
@Component
@Scope(value = "request")
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
