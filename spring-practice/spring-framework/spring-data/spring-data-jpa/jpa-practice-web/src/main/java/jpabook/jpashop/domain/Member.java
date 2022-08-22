package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // mappendBy: Order.member의 값이 변경되면 같이 변경
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList();
}
