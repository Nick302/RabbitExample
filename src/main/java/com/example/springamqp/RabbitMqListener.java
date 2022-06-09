package com.example.springamqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.logging.Logger;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(String.valueOf(RabbitMqListener.class));
    Random random = new Random();

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        logger.info("Received from queue 1: " + message);
    }

    /*@RabbitListener(queues = "query-example-3-1")
    public void worker1(String message) {
        logger.info("Листнер 1 : " + message);
    }

    @RabbitListener(queues = "query-example-3-2")
    public void worker2(String message) {
        logger.info("Листнер 2 : " + message);
    }*/

    @RabbitListener(queues = "query-example-4-1")
    public void worker1(String message) {
        logger.info("Листнер 1 : " + message);
    }

    @RabbitListener(queues = "query-example-4-2")
    public void worker2(String message) {
        logger.info("Листнер 2 : " + message);
    }
}
