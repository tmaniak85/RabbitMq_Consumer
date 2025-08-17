package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.ReportRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class ReportRequestConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.delayed")
    public void listen(String message) throws JsonProcessingException {
        var p = objectMapper.readValue(message, ReportRequest.class);

        LOG.info("On report request: {}", p);
    }
}
