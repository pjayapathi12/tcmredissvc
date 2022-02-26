package com.cloudathon.cloudathondemo;

import com.cloudathon.cloudathondemo.models.TcmEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TcmEventConsumer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TcmEventDispatcher dispatcher;

    //@KafkaListener(topics = "#{'${io.confluent.developer.config.topic.name}'}", id = "tcmEvents", clientIdPrefix = "tcmEventListener")
    @KafkaListener(topics = "#{'${io.confluent.developer.config.topic.name}'}")
    public void consume(final ConsumerRecord<String, String> consumerRecord)  {
        log.info("received {} {}", consumerRecord.key(), consumerRecord.value());
        try {
            TcmEvent tcmEvent = objectMapper.readValue(consumerRecord.value(), TcmEvent.class);

            dispatcher.processEvent(tcmEvent);
        }catch (Exception e) {
            log.info("Exception encountered while processing event ==> {}", e.getMessage());
        }

    }
}
