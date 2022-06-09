package com.example.springamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@Import(RabbitConfiguration.class)
@SpringBootApplication
public class SpringAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAmqpApplication.class, args);
    }

}
