package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}

/**
 * # Entity vs DTO
 *  - Entity 는 비즈니스 로직 관련한 코드 권장, API 전달 X
 *  - DTO 는 화면 관련 바인딩 코드 권장
 */