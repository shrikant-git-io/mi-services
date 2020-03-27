package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;


    //http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/12
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("from",from);
        paramsMap.put("to",to);
        ResponseEntity<CurrencyConversionBean> forEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, paramsMap);
        CurrencyConversionBean response = forEntity.getBody();
        return new CurrencyConversionBean(response.getId(),from, to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()));
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){

        CurrencyConversionBean response = currencyExchangeServiceProxy.retriveExchangeValue(from,to);
        logger.info("Sleuth request id : {}", response);
        return new CurrencyConversionBean(response.getId(),from, to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()));
    }



}
