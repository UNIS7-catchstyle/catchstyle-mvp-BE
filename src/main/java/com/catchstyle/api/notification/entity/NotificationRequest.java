package com.catchstyle.api.notification.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter//서비스 계층이나 dto로 데이터에 접근하려면 필수로 붙여야함
@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED) //JPA 필수 기본 생성자를 protected로 자동 생성
public class NotificationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //long은 초기값이 0이고 Long은 null

    //전화번호 xxx-xxxx-xxxx 10글자
    //프론트엔드(dto)로부터 전달받아 채워넣는 값
    @Column(length=20)
    private String phoneNumber;

    //객체가 생성될 때 서버의 현재 시간을 자동 기록
    @Column(nullable=false,updatable=false)
    private LocalDateTime createdAt;

    //비즈니스 로직(Service)에서 dto에 담긴 값을 꺼내, 안전하게 객체를 생성하기 위한 전용 생성자는 직접 명시
    //객체가 메모리에 생성되는 그 순간(생성자 호출 시점)에 필수 데이터를 무조건 넘겨받도록 강제해서 데이터 무결성 보장
    public NotificationRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
    }


}
