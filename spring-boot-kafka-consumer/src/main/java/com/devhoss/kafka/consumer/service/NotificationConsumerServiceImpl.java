package com.devhoss.kafka.consumer.service;

import com.devhoss.kafka.consumer.dto.NotificationRequest;
import com.devhoss.kafka.consumer.model.Notificacion;
import com.devhoss.kafka.consumer.repository.INotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumerServiceImpl implements INotificationConsumerService{

    @Autowired
    private INotificationRepository iNotificationRepository;

    @Override
    @KafkaListener(topics = {"topic-name-1"}, groupId = "my-group-id", containerFactory = "customKafkaFactory" )
    public void pushNotification(NotificationRequest notificationRequest, Acknowledgment acknowledgment) {
        log.info("Message queue {}", notificationRequest);

        Notificacion notification = Notificacion.builder()
                // .notificationId(notificationRequest.getToCustomerId())
                .toCustomerId(notificationRequest.getToCustomerId())
                .toCustomerEmail(notificationRequest.getToCustomerName())
                .message(notificationRequest.getMessage())
                .sender("Hernan")
                .sentAt(LocalDateTime.now())
                .build();
        iNotificationRepository.save(notification);

        acknowledgment.acknowledge();


    }
}
