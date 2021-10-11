# sampleKafka

## 카프카의 이론과 원리 구현을 학습하기 위해 샘플로 만들어 보는 디렉토리 입니다.

## 카프카 사용 명령어
docker-compose 실행
프로젝트 내의 docker-compse
$docker-compose -f docker-compose.yml up

카프카 서버에 접속(iterm)
 -카프카에 접속(우선)
-> $docker container exec -it kafka bash

/opt/kafka_2.12-2.5.0/bin<-위치
 - 존재하는 토픽 name만 확인
-> kafka-topics.sh --list --bootstrap-server localhost:9092
 - 토픽 상세 확인
-> kafka-topics.sh --bootstrap-server localhost:9092 --topic chat_topic --describe
 - 컨슈머 key-value확인
-> kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic chat_topic --property print.key=true --property key.separator="-"
```
 키기 zookeeper -> server
/usr/local/kafka/bin/zookeeper-server-start.sh /usr/local/kafka/config/zookeeper.properties
/usr/local/kafka/bin/kafka-server-start.sh /usr/local/kafka/config/server.properties

## show topic list
/usr/local/kafka/bin/kafka-topics.sh --list --zookeeper localhost:2181

## create the topic
/usr/local/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
/usr/local/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic numtest

## show the topic
/usr/local/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic numtest

## show the topic partiton 1
/usr/local/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --partition 1 --topic numtest

## delete the topic
/usr/local/kafka/bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic my_topic

## 끄기 server -> zookeeper
bin/kafka-server-stop.sh config/server.properties
bin/zookeeper-server-stop.sh config/zookeeper.properties

## consumer groups check!
/usr/local/kafka/bin/kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --list

## consumer status and offset check!
/usr/local/kafka/bin/kafka-consumer-groups.sh  --bootstrap-server localhost:9092 --group sr --describe

## consumer group delete
/usr/local/kafka/bin/kafka-consumer-groups.sh --zookeeper localhost:2181 --delete --group <group_name>

## topic leader follower check
/usr/local/kafka/bin/kafka-topics.sh --zookeeper localhost:2181 --topic my_topic --describe

## server log check
cat /usr/local/bin/kafka/logs/server.log
```

## 참고 사이트
 - https://www.confluent.io/blog/apache-kafka-spring-boot-application/
 - https://gaemi606.tistory.com/entry/Spring-Boot-Kafka%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EC%B1%84%ED%8C%85-3-%EB%A9%94%EC%8B%9C%EC%A7%80-%EC%A3%BC%EA%B3%A0%EB%B0%9B%EA%B8%B0-ReactJS?category=745027