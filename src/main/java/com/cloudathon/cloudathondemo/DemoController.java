package com.cloudathon.cloudathondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String TCM_CACHE = "TCM_ERRORSTATS_CACHE";
    private HashOperations<String, String, String> hashOperations;


    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }



    @GetMapping(value = "/getTcmErrorstat/{tcmId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String publish(@PathVariable String tcmId) {
        return hashOperations.get(TCM_CACHE, tcmId);
    }

    @GetMapping(value = "/test")
    public String test() {
        return "Test API returned data successfully in Azure";
    }

}
