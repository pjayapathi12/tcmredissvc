package com.cloudathon.cloudathondemo.event.services.tcm;

import com.cloudathon.cloudathondemo.TcmEventProducer;
import com.cloudathon.cloudathondemo.event.interfaces.TcmEventFlowInterface;
import com.cloudathon.cloudathondemo.models.TcmEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service("TcmResourcesDbSavedMiniflowService")
public class TcmResourcesDbSavedMiniflowService implements TcmEventFlowInterface {

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private TcmEventProducer producer;


    public void process(TcmEvent tcmEvent) throws JsonProcessingException {
        log.info("received {}", tcmEvent);

        // 1. save data to redis


    }

}
