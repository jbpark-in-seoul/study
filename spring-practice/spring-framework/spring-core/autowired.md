# @Autowired
- 개념
  + 스프링 컨테이너에 존재하는 빈을 찾아서 타입에 맞는 빈을 자동으로 의존성 주입

- 조회된 타입의 하위 타입이 2개 이상인 경우
  + 1: @Autowired 필드명 매칭
    - 기존 코드
      ~~~
      @Autowired
      private DiscountPolicy discountPolicy
      ~~~
    - 변경 코드 
      ~~~
      @Autowired
      private DiscountPolicy rateDiscountPolicy
      ~~~
  + 2: @Qualifier -> @Qualifier끼리 매칭 -> 빈 이름 매칭
    - 구분자를 추가해주는 방법. 빈 이름 변경 X
    - 빈 등록시 @Qualifier("") 입력
      ~~~
      // 등록 부분
      @Component
      @Qualifier("mainDiscountPolicy")
      public class RateDiscountPolicy implements DiscountPolicy {}
      ~~~
      ~~~
      // 주입 부분
      @Autowired
      public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
      }
      ~~~
    - 정리
      + @Qualifier 끼리 매칭
      + 빈 이름 매칭
      + NoSuchBeanDefinitionException 예외 발생
  + 3: @Primary 사용 -> 자주 사용되는 방법
    - 우선순위 지정
      ~~~
      @Component
      @Primary
      public class RateDiscountPolicy implements DiscountPolicy {}
      
      @Component
      public class FixDiscountPolicy implements DiscountPolicy {}
      ~~~
- @Qualifier의 문제점 및 해결 방안
  + @Qualifier("") 안의 문자열은 컴파일시 체크가 되지 않음.
  + 어노테이션 클래스를 생성하여 해결
    - MainDiscountPolicy 클래스 정의 및 사용
      ~~~
      @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
      @Retention(RetentionPolicy.RUNTIME)
      @Inherited
      @Documented
      @Qualifier("mainDiscountPolicy")
      public @interface MainDiscountPolicy {}
      
      // 사용 부분
      @Autowired
      public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
      }
      ~~~

