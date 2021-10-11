package kr.legossol.Kafka.controller;

import kr.legossol.Kafka.messageDto.Message;
import kr.legossol.Kafka.service.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/kafka")
@CrossOrigin(origins = "*")
@Slf4j
public class KafkaController {
    private final Producer producer;


    public KafkaController(Producer producer) {
        this.producer = producer;
    }

//    @PostMapping
//    public String sendMessage(@RequestParam("message")String message){
//        this.producer.sendMessage(message);
//
//        return "success";
//    }

    @PostMapping("/publish")
    public void sendingMessage(@RequestBody Message message){
        log.info("Produce message : " + message.toString());
        message.setTimeStamp(LocalDateTime.now().toString());
        try{
            producer.sendMessage(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @MessageMapping("sendingMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message){
        return message;
    }
}
