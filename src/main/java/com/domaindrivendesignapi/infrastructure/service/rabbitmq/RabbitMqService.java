package com.domaindrivendesignapi.infrastructure.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqService.class);

    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqService(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String exchange, Object object) {
        String serializedMessage = "";
        try {
            serializedMessage = objectMapper.writeValueAsString(object);
            rabbitTemplate.convertAndSend(exchange, "", object);
        } catch (Exception e) {
            LOGGER.error("Error occurred. Message: " + serializedMessage, e);
        }
    }
}
