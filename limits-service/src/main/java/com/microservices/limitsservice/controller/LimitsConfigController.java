package com.microservices.limitsservice.controller;

import com.microservices.limitsservice.bean.LimitConfiguration;
import com.microservices.limitsservice.config.LimitsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {

    @Autowired
    private LimitsConfig limitsConfig;

    //http://localhost:8080/limits
   @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfig(){

       return new LimitConfiguration(limitsConfig.getMinimum(),limitsConfig.getMaximum());
   }
}
