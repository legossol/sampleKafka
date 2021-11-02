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
@RequestMapping("/api")
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

    @PostMapping("/send")
    public void sendingMessage(@RequestBody Message message){
        log.info("GENERATE TOPIC DUE TO USER SEND MESSAGE : " + message.toString());
        try{
            producer.sendMessage(message.toDto());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
//    @MessageMapping("sendingMessage")
//    @SendTo("/topic/group")
//    public Message broadcastGroupMessage(@Payload Message message){
//        return message;
//    }

}
