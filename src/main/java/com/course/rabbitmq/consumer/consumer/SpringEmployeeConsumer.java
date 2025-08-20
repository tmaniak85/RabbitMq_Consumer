package com.course.rabbitmq.consumer.consumer;

import com.course.rabbitmq.consumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpringEmployeeConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SpringEmployeeConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.spring2.accounting.work")
    public void listenAccounting(String message) throws JsonProcessingException {
        var emp = objectMapper.readValue(message, Employee.class);

        LOG.info("Consuming accounting : " + emp);

        if (emp.getName() == null || emp.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }

        LOG.info("On accounting : " + emp);
    }

    @RabbitListener(queues = "q.spring2.marketing.work")
    public void listenMarketing(String message) throws JsonProcessingException {
        var emp = objectMapper.readValue(message, Employee.class);

        LOG.info("Consuming marketing : " + emp);
        LOG.info("On marketing : " + emp);
    }
}
