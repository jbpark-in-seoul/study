# @ComponentScan
- 개념
  + @Component 어노테이션이 붙은 클래스들을 스캔하여 등록해주는 어노테이션
  + @Component 포함 어노테이션
     1. @Component
     2. @Controller
     3. @Service
     4. @Repository
     5. @Configuration
  + 관례적으로 프로젝트 최상단에 설정 파일을 위치 시킨다.
  + 빈 등록 우선순위
    * 이름이 같은 빈이 있는 경우, `ConflictBeanDefinitionException` 발생
    * 자동 빈, 수동 빈이 있는 경우, 수동 빈으로 오버라이딩
    * 스프링 부트에서는 기본적으로 `bean-definition-overriding=false`
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

- 옵션
  + basePackages
    * 미지정시, @ComponentScan의 하위부터 탐색
  + excludeFilters
  + includeFilters