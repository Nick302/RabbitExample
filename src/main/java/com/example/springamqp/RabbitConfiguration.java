package com.example.springamqp;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class RabbitConfiguration {
    Logger logger = Logger.getLogger(String.valueOf(RabbitConfiguration.class));

    //настраиваем соединение с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    // для для регистрации/отмены регистрации очередей и т.п.;
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    //  для отправки сообщений (producer);
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    //объявляем очередь с именем queue1
    @Bean
    public Queue myQueue1() {
        return new Queue("query-example-4-1");
    }

    @Bean
    public Queue myQueue2() {
        return new Queue("query-example-4-2");
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("exchange-example-4");
    }

    @Bean
    public Binding errorBinding1(){
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("error");
    }

    @Bean
    public Binding errorBinding2(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("error");
    }

    @Bean
    public Binding infoBinding(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("info");
    }

    @Bean
    public Binding warningBinding(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("warning");
    }

  /*  @Bean
    public Binding binding1(){
        return BindingBuilder.bind(myQueue1()).to(fanoutExchangeA());
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(myQueue2()).to(fanoutExchangeA());
    }*/


    //объявляем контейнер, который будет содержать листенер для сообщений , принимает сообщения consumer
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer1() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("queue1");
//        container.setMessageListener(new MessageListener() {
//            //тут ловим сообщения из queue1
//            public void onMessage(Message message) {
//                logger.info("received from queue1 : " + new String(message.getBody()));
//            }
//        });
//        return container;
//    }
    //юзать будем на анотациях RabbitMqListener
}
