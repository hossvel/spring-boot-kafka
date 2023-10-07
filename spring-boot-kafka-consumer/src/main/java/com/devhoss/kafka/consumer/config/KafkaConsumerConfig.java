package com.devhoss.kafka.consumer.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

        @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapServer;

        @Value("${spring.kafka.consumer.group-id}")
        private String groupId;


        public Map<String, Object> propStringObjectMap() {
            return new HashMap<>(){{
                put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
                put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
                put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
                put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
                put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
            }};
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, String> simpleKafkaFactory() {
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new
                    ConcurrentKafkaListenerContainerFactory<>();

            factory.setConsumerFactory(simpleConsumerFactory());
            return factory;
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, String> customKafkaFactory() {
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new
                    ConcurrentKafkaListenerContainerFactory<>();

            factory.setConsumerFactory(customConsumerFactory());
            factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
            return factory;
        }

        private ConsumerFactory<String, String> simpleConsumerFactory() {
            Map<String, Object> props = this.propStringObjectMap();
            return new DefaultKafkaConsumerFactory<>(props);
        }

        private ConsumerFactory<String, Object> customConsumerFactory() {

            Map<String, Object> props = this.propStringObjectMap();
            props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
            props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);//tener en cuenta paquete del producto y consumidor
            props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.devhoss.kafka.consumer.dto.NotificationRequest");//
            return new DefaultKafkaConsumerFactory<>(props);
        }

    /*
    public Map<String, Object> consumerConfig(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> consumer(ConsumerFactory consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
    */
}