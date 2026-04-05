package com.catchstyle.api.notification.service;

import com.catchstyle.api.common.exception.AlreadyRegisteredException;
import com.catchstyle.api.notification.entity.NotificationRequest;
import com.catchstyle.api.notification.repository.NotificationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//컨트롤러로부터 DTO에 담긴 안전한 데이터(phoneNumber)만 받아 작업 처리
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)//속도 최적화
public class NotificationRequestService {
    private final NotificationRequestRepository notificationRequestRepository;

    //전화번호가 중복인지 확인 후 저장(그외 검사는 프엔과 맞닿은 컨트롤러와 dto에서 함)

    @Transactional//RW 둘다 됨
    public void registerNotificationRequest(String phoneNumber) {
        //1.중복 검사 & 예외 처리
        if(notificationRequestRepository.existsByPhoneNumber(phoneNumber)) {
            //커스텀 예외 발생(GlobalExceptionHandler가 이를 낚아채 409 상태코드로 변환
            throw new AlreadyRegisteredException("이미 알림 신청이 완료된 전화번호입니다.");
        }


    //2.엔티티 생성 밎 저장
    NotificationRequest entity=new NotificationRequest(phoneNumber);

    //3.db에 저장
    notificationRequestRepository.save(entity);

    }
}
