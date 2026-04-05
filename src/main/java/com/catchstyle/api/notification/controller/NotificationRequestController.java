//Controller:프엔의 HTTP 요청을 받아 검증하고,비즈니스 로직(Service)로 토스한 뒤
//결과 상태 코드(HTTP Status)를 반환하는 역할
package com.catchstyle.api.notification.controller;

import com.catchstyle.api.notification.dto.NotificationRequestDto;
import com.catchstyle.api.notification.service.NotificationRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/notifications") //공통 URL 매핑
@RequiredArgsConstructor
public class NotificationRequestController {

    //Service 계층 의존성 주입(final 필수):Service의 기능 호출해야 하므로
    private final NotificationRequestService notificationRequestService;

    //POST /api/v1/notifications
    //프론트엔드에서 전화번호 수신(dto형태로 받음)
    @PostMapping
    public ResponseEntity<Void> registerNotification(
            //데이터 수신 및 검증. @Valid가 있어야 @NotBlack나 @Pattern이 작동함
            @Valid @RequestBody NotificationRequestDto notificationRequest
    ){
        //Service 호출하고 전화번호 넘김
        notificationRequestService.registerNotificationRequest(notificationRequest.phoneNumber());
        //성공 시 상태 코드 200(ok) 반환
        return ResponseEntity.ok().build();
    }


}
