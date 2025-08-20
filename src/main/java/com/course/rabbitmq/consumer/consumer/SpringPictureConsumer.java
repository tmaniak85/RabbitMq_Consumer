package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class SpringPictureConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(FixedRateConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.spring.image.work")
    public void listenImage(String message) throws IOException {
        var picture = objectMapper.readValue(message, Picture.class);

        LOG.info("Consuming image : " + picture);

        if (picture.getSize() > 9000) {
            throw new IOException("Image " + picture.getName() + " size too large : " + picture.getSize());
        }

        LOG.info("Processing image : " + picture);
    }

    @RabbitListener(queues = "q.spring.vector.work")
    public void listenVector(String message) throws JsonProcessingException {
        var picture = objectMapper.readValue(message, Picture.class);

        LOG.info("Consuming vector : " + picture);
        LOG.info("Processing vector : " + picture);
    }
}
