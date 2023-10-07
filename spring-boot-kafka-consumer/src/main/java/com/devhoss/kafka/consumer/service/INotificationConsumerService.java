package com.devhoss.kafka.consumer.service;

import com.devhoss.kafka.consumer.dto.NotificationRequest;
import com.devhoss.kafka.consumer.model.Notificacion;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

public interface INotificationConsumerService {

    public void pushNotification(NotificationRequest notificationRequest, Acknowledgment acknowledgment);

    public List<Notificacion> findAll();
}
