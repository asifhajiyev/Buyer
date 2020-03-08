package com.gdg.buyer.service;

import com.gdg.buyer.dto.NotificationDto;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
    ResponseEntity receiveNotification(String text);

    ResponseEntity getNotifications();

    NotificationDto getNotificationById(String id);

    String deleteNotificationById(String id);
}
