package com.cloudathon.cloudathondemo.event.services.tcm;

import com.cloudathon.cloudathondemo.TcmEventProducer;
import com.cloudathon.cloudathondemo.event.interfaces.TcmEventFlowInterface;
import com.cloudathon.cloudathondemo.models.TcmEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Log4j2
@Service("TcmResourcesDbSavedMiniflowService")
public class TcmResourcesDbSavedMiniflowService implements TcmEventFlowInterface {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    private TcmEventProducer producer;

    private final String TCM_CACHE = "TCM_ERRORSTATS_CACHE";
    private HashOperations<String, String, String> hashOperations;


    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void process(TcmEvent tcmEvent) throws JsonProcessingException {
        log.info("received {}", tcmEvent);

        //hashOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(tcmEvent.getTcmId()) ) {
            hashOperations.put(TCM_CACHE, tcmEvent.getTcmId(), objectMapper.writeValueAsString(tcmEvent));
        }
        else {
            hashOperations.put(TCM_CACHE, tcmEvent.getTcmId(), objectMapper.writeValueAsString(tcmEvent));
        }

        String redisValue = hashOperations.get(TCM_CACHE, tcmEvent.getTcmId());
        //String redisValue = ops.ent.getTcmId());
        log.info("value returned from redis is {}", redisValue);

        log.info("event processed successfully");

    }

}
