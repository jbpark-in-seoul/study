package hello.core;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Qualifier
public class SampleServiceImpl implements SampleService {
    private final SampleRepository sampleRepository;
}
/*
 - @RequiredArgsConstructor
 - private final 필드 변수가 있거나 또는 @Nonnull인 필드값이 있을 경우 아래 생성자 코드를 주입시킴
 - DI의 네 가지 방법 중 생성자 주입이 권장된다
   + 생성자 주입, 수정자 주입, 필드 주입, 메서드 주입

 ~~~
 public SampleBean(SampleRepository sampleRepository) {
     this.sampleRepository = sampleRepository;
 }
 ~~~
 */