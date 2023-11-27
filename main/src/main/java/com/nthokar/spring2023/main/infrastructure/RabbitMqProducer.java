package com.nthokar.spring2023.main.infrastructure;

import lombok.extern.slf4j.Slf4j;

import com.nthokar.spring2023.main.application.MoveDTO;
import com.nthokar.spring2023.main.application.services.GameService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMqProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing2.key}")
    private String routingKey2;
    private RabbitTemplate template;

    @Autowired GameService gameService;


    public RabbitMqProducer(RabbitTemplate rabbitTemplate){
        this.template = rabbitTemplate;
        template.setExchange(exchange);
    }

    public void sendMessage(MoveDTO move){
        log.info(String.format("message sent -> %s", move));
        template.convertAndSend(routingKey2, move);
    }
}
