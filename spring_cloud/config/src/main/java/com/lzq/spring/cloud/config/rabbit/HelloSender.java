package com.lzq.spring.cloud.config.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "hello " + new Date();
        System.out.println("Sender: "+context);
        this.rabbitTemplate.convertAndSend("hello",context);
    }

    public void send(int i){
        String context = "hello " + i + ","+ new Date();
        System.out.println("Sender: "+context);
        this.rabbitTemplate.convertAndSend("hello",context);
    }

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }
}
