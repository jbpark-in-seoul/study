package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
/*
    JPA 스펙상 엔티티나 임베디드 타입(`@Embeddable`)은 자바 기본 생성자를 `public` 또는 `protected`로 설정해야 한다.
    가능하면 'protected'로 두는 것이 안전하다.

    JPA가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플렉션, 프록시 같은 기술을 사용할 수 있도록 지원해야 하기 때문이다.
 */
