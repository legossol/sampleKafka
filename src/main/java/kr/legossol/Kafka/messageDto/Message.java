package kr.legossol.Kafka.messageDto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
public class Message {
    private String author;
    private String content;
    private LocalDateTime timeStamp;
    private int cnt;

    public Message(){}

    @Builder
    public Message(String author, String content, LocalDateTime timeStamp,int i){
        this.author = author;
        this.content = content;
        this.timeStamp = timeStamp;
        this.cnt = i;
    }
    public String getAuthor(){ return author;}
    public String getContent(){return content;}
    public int getCnt(){return cnt;}

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
                "timeStamp = '" + timeStamp + "' ---"+
                "cnt= '" + cnt + "' }";
    }

}
