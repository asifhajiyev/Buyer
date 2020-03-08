package com.gdg.buyer.mapper;

import com.gdg.buyer.dto.NotificationDto;
import com.gdg.buyer.entity.Notification;

public class NotificationMapper {

    public static Notification dtoToEntity(NotificationDto notificationDto) {
        if (notificationDto == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setText(notificationDto.getText());
        return notification;
    }

    public static NotificationDto entityToDto(Notification notification) {
        if (notification == null) {
            return null;
        }
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setText(notification.getText());
        notificationDto.setSendingTime(notification.getSendingTime());
        return notificationDto;
    }
}
