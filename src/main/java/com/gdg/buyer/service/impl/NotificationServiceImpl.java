package com.gdg.buyer.service.impl;

import com.gdg.buyer.dto.NotificationDto;
import com.gdg.buyer.entity.Notification;
import com.gdg.buyer.mapper.NotificationMapper;
import com.gdg.buyer.repository.NotificationRepository;
import com.gdg.buyer.service.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ResponseEntity receiveNotification(String text) {
        Notification notification = new Notification();
        notification.setText(text);
        notificationRepository.save(notification);
        return new ResponseEntity<>("Notification sent!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity getNotifications() {
        Iterable<Notification> notifications = notificationRepository.findAll();
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        for (Notification n : notifications) {
            notificationDtoList.add(NotificationMapper.entityToDto(n));
        }
        return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
    }

    @Override
    public NotificationDto getNotificationById(String id) {
        Notification notification = null;
        try {
            notification = notificationRepository.findById(Long.valueOf(id)).orElseThrow(() -> new Exception("Product doesn't exist"));
        } catch (Exception e) {
            e.getMessage();
        }
        return NotificationMapper.entityToDto(notification);
    }

    @Override
    public String deleteNotificationById(String id) {
        String response;
        NotificationDto notificationDto = getNotificationById(id);
        if (notificationDto == null) {
            response = "Notification doesn't exist";
        } else {
            notificationRepository.deleteById(Long.valueOf(id));
            response = "Deleted";
        }
        return response;
    }
}
