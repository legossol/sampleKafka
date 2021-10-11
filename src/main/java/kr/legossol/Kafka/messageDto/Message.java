package kr.legossol.Kafka.messageDto;

import lombok.RequiredArgsConstructor;


public class Message {
    private String author;
    private String content;
    private String timeStamp;

    public Message(){}
    public Message(String author, String content){
        this.author = author;
        this.content = content;
    }
    public String getAuthor(){ return author;}
    public String getContent(){return content;}
    public String getTimeStamp(){return timeStamp;}

    public void setAuthor(String author){this.author = author;}
    public void setContent(String content){this.content = content;}
    public void setTimeStamp(String timeStamp){this.timeStamp = timeStamp;}

    @Override
    public String toString(){
        return "Message = {"+
                "author = '" + author + "' ---" +
                "content = '" + content + "' ---" +
                "timeStamp = '" + timeStamp + "' }";
    }

}
