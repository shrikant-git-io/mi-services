package com.microservices.limitsservice.controller;

import com.microservices.limitsservice.bean.LimitConfiguration;
import com.microservices.limitsservice.config.LimitsConfig;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.*;

@RestController
public class LimitsConfigController {

    @Autowired
    private LimitsConfig limitsConfig;

    //http://localhost:8080/limits
   @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfig(){

       return new LimitConfiguration(limitsConfig.getMinimum(),limitsConfig.getMaximum());
   }

   @GetMapping("/faultTolerenceExample")
   @HystrixCommand(fallbackMethod = "retriveConfigFallback")
    public LimitConfiguration retriveConfig(){
       throw new RuntimeException();
   }

   public LimitConfiguration retriveConfigFallback(){
       return new LimitConfiguration(100,1000);
   }
}
