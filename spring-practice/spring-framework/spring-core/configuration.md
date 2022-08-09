# @Configuration
- 스프링 컨테이너는 싱글톤 패턴 사용
  + 스프링 컨테이너는 싱글톤 보장을 위하여 설정 파일 내에 등록된 @Bean 정보가 여러 번 호출될 경우에 바이트 코드를 조작한 클래스 파일인 Config@CGLIB를 생성한다.
  + Config@CGLIB 파일은 호출된 @Bean이 이미 등록되었는지 확인하여 객체의 생성을 판단한다.
    - 기등록: 기존 객체 호출
    - 미등록: 새로운 객체 생성


~~~
// 설정 파일
@Configuration
public class AppConfig {
  
  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }
  
  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
  
  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }
}
~~~

~~~
// 설정 파일 테스트
public class CoreApplicationTests {
    
  @Test
  void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
    
    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();
    
    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    
  }
}
~~~