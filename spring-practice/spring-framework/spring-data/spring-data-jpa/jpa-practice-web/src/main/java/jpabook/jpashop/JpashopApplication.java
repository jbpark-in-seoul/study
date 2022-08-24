package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

}

/*
  @Id
    - 설정 프로퍼티가 테이블의 기본 키(Pk) 역할
    - getter에 설정 가능

  @GeneratedValue
    - 기본 키의 값을 자동으로 생성
    - 속성
      + generator: SequenceGenerator, TableGenerator에 명시된 기본 키 생성자를 재사용할 때 사용.
      + strategy : 기본 키 생성 전략으로 기본 값은 AUTO 이고, IDENTITY, SEQUENCE, TABLE 이 있다.
    - 예제
      @Id
      @GeneratedValue(strategy=GenerationType.AUTO)

  @Embedded, @Embeddable
    - 여러 데이터를 하나의 의미를 갖는 객체로 표현
    - JPA Entity안의 Column을 하나의 객체로 사용
    - 내장된 Column 객체에 @Embedded 표기
    - 해당 객체를 생성 후 @Embeddable 표기

  @Inheritance, @DiscriminatorValue
    - 상속관계 매핑
      + 객체는 상속 관계가 존재, 관계형 데이터베이스는 상속 관계 X
      + 슈퍼 타입 서브 타입 관계(모델링 기법)은 객체 상속과 유사
      + 객체의 상속 구조와 DB의 슈퍼타입 서브타입 관계 매핑
    - 물리 모델 구현법
      + 객체는 상속을 지원해서 모델링과 구현이 같다.
      + DB는 상속을 지원하지 않아서 물리 모델로 구현할 방법이 필요하다.
        a.@Inheritance(strategy=InheritanceType.XXX)
          XXX: JOINED(정규화), SINGLE_TABLE(단일 테이블), TABLE_PER_CLASS
        b.DiscriminatorColumn(name = "dtype"): 부모 클래스에 선언, 하위 클래스를 구분하려는 용도이며 기본 값 dtype이다.
        c.@DiscriminatorValue("XXX"): 하위 클래스에 선언, 엔티티를 저장할 때 슈퍼 타입의 구분 컬럼에 저장할 값을 지정한다.

  @OneToOne, @OneToMany, @ManyToOne, @ManyToMany
    - 방향
      + 단방향 관계: 두 엔티티가 관계를 맺을 떄, 한 쪽의 엔티티만 참조하는 것을 의미
      + 양방향 관계: 두 엔티티가 관계를 맺을 때, 서로 참조하는 것을 의미
    - 다중성
      + 1:1, 1:N, N:1, N:N
    - 연관 관계의 주인(Owner)
      + 두 테이블에 대한 외래키를 갖는 테이블이 연관 관계의 주인
      + 연관 관계 주인만이 외래 키 관리(등록, 수정, 삭제)가 가능하고, 주인이 아닌 엔티티는 읽기만 가능
    - 속성
      + fetch: @ManyToOne(fetch = Fectch.XXX)
        XXX: EAGER(즉시), LAZY(지연)
        XToOne 기본 값 -> EAGER, XToMany 기본 값 -> Lazy
      + cascade: @ManyToOne(fetch = Fectch.LAZY, cascade = CascadeType.XXX)
      	XXX: ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH
      	  a.ALL: 상위 엔티티에서 하위 엔티티로 모든 작업을 전파
      	  b.PERSIST: 하위 엔티티까지 영속성 전달 - Order Entity 저장 시, OrderItem Entity 도 저장
      	  c.MERGE: 하위 엔티티까지 병합 작업을 지속 - Order Entity, Person Entity 조회 후 업데이트
      	  d.REMOVE: 하위 엔티티까지 제거 작업을 지속 - 연결된 하위 엔티티까지 엔티티 제거
      	  e.REFRESH: DB 로부터 인스턴스 값 다시 읽어오기, 연결된 하위 엔티티 인스턴스도 새로고침
      	  f.DETACH: 영속성 컨텍스트에서 엔티티 제거, 연결된 하위 엔티티까지 영속성 제거
      	* 다중 연관 관계의 Entity 인 경우, 주의해서 사용

  @JoinColumn
    - 외래 키를 매핑할 때 사용: @JoinColumn(name = "item_id")

  @Enumerated(EnumType.XXX)
    - default ORDINAL, 숫자: 중간에 새로운 유형 추가가 되면 문제가 생김 -> 반드시 STRING으로 사용

  @PersistanceContext
    - EntityManager를 빈으로 주입할 때 사용
    - 스프링 부트에서는 @Autowired 로 주입 가능. (스프링도 차후 지원 예정)
    - 싱글톤 동시성 이슈 방지
      + 스프링 컨테이너가 초기화되면서 @PersistenceContext로 주입 받은 EntityManager를 Proxy로 감싼다.
      + EntityManager 호출 마다 Proxy를 통해 EntityManager를 생성하여 Thread-safe를 보장한다.

  @RequiredArgsConstructor
    - final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만듦
  @NoArgsConstructor
	- 파라미터가 없는 기본 생성자를 생성
  @AllArgsConstructor
    - 모든 필드 값을 파라미터로 받는 생성자를 만듦

 */