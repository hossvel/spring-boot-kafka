package com.devhoss.kafka.consumer.service;

import com.devhoss.kafka.consumer.dto.NotificationRequest;
import org.springframework.kafka.support.Acknowledgment;

public interface INotificationConsumerService {

    public void pushNotification(NotificationRequest notificationRequest, Acknowledgment acknowledgment);
}
