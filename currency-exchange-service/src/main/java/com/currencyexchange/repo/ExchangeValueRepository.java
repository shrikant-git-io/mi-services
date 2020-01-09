package com.currencyexchange.repo;

import com.currencyexchange.bean.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    //spring data jpa will auto find records with matching columns of from and to
    public ExchangeValue findByFromAndTo(String from, String to);
}
