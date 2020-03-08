package com.gdg.buyer.controller;

import com.gdg.buyer.dto.NotificationDto;
import com.gdg.buyer.service.impl.NotificationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationServiceImpl notificationService;

    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @ApiOperation(value = "Gets all notifications")
    @GetMapping
    public ResponseEntity getNotifications() {
        return notificationService.getNotifications();
    }

    @ApiOperation(value = "Add or receive notification")
    @PostMapping("send-notification")
    public ResponseEntity receiveNotification(@RequestBody String text) {
        return notificationService.receiveNotification(text);
    }

    @ApiOperation(value = "Gets notification by give id")
    @GetMapping("{id}")
    public ResponseEntity getNotificationById(@PathVariable String id) {
        return new ResponseEntity(notificationService.getNotificationById(id), HttpStatus.OK);
    }


    @ApiOperation(value = "Deletes notification by given id")
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity deleteNotificationById(@PathVariable String id){
        return new ResponseEntity(notificationService.deleteNotificationById(id), HttpStatus.OK);
    }
}
