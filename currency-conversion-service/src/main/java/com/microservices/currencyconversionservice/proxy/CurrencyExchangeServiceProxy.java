package com.microservices.currencyconversionservice.proxy;

import com.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service" , url = "http://localhost:8000" )
@FeignClient(name = "currency-exchange-service")
//client side load balancing , check app properties for ports configured, in case of eureka, it identifies based on service name
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retriveExchangeValue(@PathVariable String from, @PathVariable String to);

    }
