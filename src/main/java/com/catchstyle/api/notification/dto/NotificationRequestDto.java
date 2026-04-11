package com.catchstyle.api.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//클라이언트->서버
//dto에서는 오직 사용자가 입력한 순수 데이터만 전송(BODY만. 헤더는 X)
//entity에서는 dto로 전달받은 데이터에 id와 시간을 결합해 저장
public record NotificationRequestDto(
        @NotBlank(message = "전화번호를 입력해주세요")
        String phoneNumber
){ }
