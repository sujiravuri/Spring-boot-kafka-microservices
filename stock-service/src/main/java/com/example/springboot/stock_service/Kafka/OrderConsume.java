package com.example.springboot.stock_service.Kafka;

import com.example.springboot.base_domains.DTO.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsume {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrderConsume.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
    groupId = "${spring.kafka.consumer.group-id}")

    public void consume(OrderEvent orderEvent){

        LOGGER.info(String.format("Order is received->%s",orderEvent.toString()));
    }
}
