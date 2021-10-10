package kr.legossol.Kafka.controller;

import kr.legossol.Kafka.service.Producer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@CrossOrigin(origins = "*")
public class KafkaController {
    private final Producer producer;


    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestParam("message")String message){
        this.producer.sendMessage(message);

        return "success";
    }
}
