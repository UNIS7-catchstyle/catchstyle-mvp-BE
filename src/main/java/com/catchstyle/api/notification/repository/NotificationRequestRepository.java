package com.catchstyle.api.notification.repository;

import com.catchstyle.api.notification.entity.NotificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRequestRepository extends JpaRepository<NotificationRequest, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
}
