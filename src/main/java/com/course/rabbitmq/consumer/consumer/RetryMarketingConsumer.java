package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class RetryMarketingConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeJsonConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.guideline2.marketing.work")
    public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        var emp = objectMapper.readValue(message.getBody(), Employee.class);

        LOG.info("On marketing: {}", emp);
        channel.basicAck(tag, false);
    }
}
