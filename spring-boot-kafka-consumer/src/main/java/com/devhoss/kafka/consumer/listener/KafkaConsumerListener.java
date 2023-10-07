package com.devhoss.kafka.consumer.listener;


import com.devhoss.kafka.consumer.dto.NotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

//@Configuration
public class KafkaConsumerListener {

    //private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

  //  @KafkaListener(topics = {"topic-name-1"}, groupId = "my-group-id", containerFactory = "customKafkaFactory" )
  //  public void listener(NotificationRequest message, Acknowledgment acknowledgment){
   //     LOGGER.info("Received message LISTENER: " + message);
   //     acknowledgment.acknowledge();
    //}
}