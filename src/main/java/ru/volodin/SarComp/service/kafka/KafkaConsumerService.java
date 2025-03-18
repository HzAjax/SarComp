package ru.volodin.SarComp.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.topic.group}")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
