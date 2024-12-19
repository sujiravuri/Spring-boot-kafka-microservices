package com.example.springboot.order_service.kafka;


import com.example.springboot.base_domains.DTO.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrderProducer.class);
    @Autowired
    private NewTopic topic;
    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendMessage(OrderEvent event){

       LOGGER.info(String.format("Event send is -> %s",event.toString()));

        Message<OrderEvent> message =
                MessageBuilder.withPayload(event).
                        setHeader(KafkaHeaders.TOPIC,topic.name()).build();
        kafkaTemplate.send(message);
    }
}
