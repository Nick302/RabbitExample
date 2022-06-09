package com.example.springamqp;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.logging.Logger;
//В этом и следующих примерах в качестве продюссера будет контроллер, который будет посылать сообщения в rabbitmq.
@RestController
public class SampleController {
    Logger logger = Logger.getLogger(String.valueOf(SampleController.class));

    @Autowired
    RabbitTemplate template;

    @PostMapping("/emit")
    @ResponseBody
    String queue1(@RequestBody Map<String,String> mess) {
       // logger.info("Логируем queue1");
        template.setExchange("exchange-example-4");
        template.convertAndSend(mess.get("key"),mess.get("message"));
        return "Все ок: " + HttpStatus.OK;
    }
}
