package kr.legossol.Kafka.messageDto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;


public class Message {
    private String author;
    private String content;
    private LocalDateTime timeStamp;

    public Message(){}

    @Builder
    public Message(String author, String content, LocalDateTime timeStamp){
        this.author = author;
        this.content = content;
        this.timeStamp = timeStamp;
    }
    public String getAuthor(){ return author;}
    public String getContent(){return content;}

    public Message toDto(){
        return Message.builder()
            .author(this.author)
            .content(this.content)
            .timeStamp(LocalDateTime.now())
            .build();
    }
    @Override
    public String toString(){
        return "Message = {"+
                "author = '" + author + "' ---" +
                "content = '" + content + "' ---" +
                "timeStamp = '" + timeStamp + "' }";
    }

}
