# sampleKafka

## 카프카의 이론과 원리 구현을 학습하기 위해 샘플로 만들어 보는 디렉토리 입니다.

## 카프카 사용 명령어
카프카 서버에 접속(iterm)
/opt/kafka_2.12-2.5.0/bin<-위치
 - 존재하는 토픽 name만 확인
-> kafka-topics.sh --list --bootstrap-server localhost:9092
 - 토픽 상세 확인
-> kafka-topics.sh --bootstrap-server localhost:9092 --topic chat_topic --describe
 - 컨슈머 key-value확인
-> kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic chat_topic --property print.key=true --property key.separator="-"
