package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY) // JPQL 관련 이슈가 있으므로 XToOne은 fetch.FetchType.LAZY 로 변경 필요
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL) // 1:1일 때는, FK를 어디 둬도 상관 없으나, 장단이 있음 -> Access가 잦은 곳에 둠
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문 시간,java8,

    @Enumerated(EnumType.STRING) // default ORDINAL, 숫자: 중간에 새로운 유형 추가가 되면 문제가 생김 -> 반드시 STRING으로 사용
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    // 연관 관계 메서드 : 양방향
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
