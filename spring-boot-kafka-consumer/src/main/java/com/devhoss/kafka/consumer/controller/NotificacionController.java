package com.devhoss.kafka.consumer.controller;

import com.devhoss.kafka.consumer.model.Notificacion;
import com.devhoss.kafka.consumer.service.INotificationConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificacionController {

    @Autowired
    private INotificationConsumerService iNotificationConsumerService;

    @GetMapping
    public List<Notificacion> findAll(){
        return iNotificationConsumerService.findAll();
    }


}
