# @ComponentScan
- 개념
  + @Component 어노테이션이 붙은 클래스들을 스캔하여 등록해주는 어노테이션
  + @Component 포함 어노테이션
     1. @Controller
     2. @Service
     3. @Component
     4. @Repository
- 사용 방법
  + ~~~
    @Configuration
    @ComponentScan(basePackages = "hello.core")
    public class AutoAppConfig {
      ...
    }
    ~~~
- 동작 과정
  + ConfigurationClassParser가 @Configuration 클래스를 파싱
  + ComponentScanAnnotationParser가 @ComponentScan 설정을 파싱
  + base-package 기준의 컴포넌트 클래스 로딩
  + ClassLoader가 로딩된 클래스들을 BeanDefinition으로 정의
  + Bean 생성