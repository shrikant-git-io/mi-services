package com.currencyexchange.controller;

import com.currencyexchange.bean.ExchangeValue;
import com.currencyexchange.repo.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    //http://localhost:8000/currency-exchange/from/USD/to/INR
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        logger.info("Sleuth request id : {}",exchangeValue );
        return exchangeValue;
    }
}
