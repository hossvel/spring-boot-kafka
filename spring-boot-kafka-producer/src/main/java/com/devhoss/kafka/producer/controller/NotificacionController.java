package com.devhoss.kafka.producer.controller;

import com.devhoss.kafka.producer.model.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificacionController {


    @Autowired
    private  KafkaTemplate<String, Object> customKafkaTemplate;


    // private final KafkaTemplate<String, String> customKafkaTemplate;

   @Value("${spring.kafka.topic.notification}")
   private String topicNotification;

    @GetMapping("push/notification/{id}")
    public ResponseEntity<Object> pushNotificationId(@PathVariable Integer id) {
        customKafkaTemplate.send(topicNotification,
                new NotificationRequest(id,
                        "Hossmell",
                        "Welcome to Hossvel")
        );

        return ResponseEntity.ok("Successfully");
    }

    @PostMapping("push/notification")
    public ResponseEntity<Object> pushNotification(@RequestBody NotificationRequest request) {
        customKafkaTemplate.send(topicNotification, request);

        return ResponseEntity.ok("Successfully");
    }

}
