package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery") // 1:1일 때는, FK를 어디 둬도 상관 없으나, 장단이 있음 -> Access가 잦은 곳에 둠
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // default ORDINAL, 숫자: 중간에 새로운 유형 추가가 되면 문제가 생김 -> 반드시 STRING으로 사용
    private DeliveryStatus status; //READY, COMP
}
