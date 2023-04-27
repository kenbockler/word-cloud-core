package com.elemroot.wordcloudcore.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;
    private static final int CHUNK_SIZE = 1024 * 1024; // 1 MB

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTextFileChunks(UUID id, byte[] fileContent) {
        int totalChunks = (int) Math.ceil((double) fileContent.length / CHUNK_SIZE);

        for (int i = 0, chunkNumber = 1; i < fileContent.length; i += CHUNK_SIZE, chunkNumber++) {
            int end = Math.min(i + CHUNK_SIZE, fileContent.length);
            byte[] chunkBytes = new byte[end - i];
            System.arraycopy(fileContent, i, chunkBytes, 0, end - i);

            String chunk = new String(chunkBytes);
            TextFileMessage message = new TextFileMessage(id, chunk, chunkNumber, totalChunks);
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        }
    }
}
