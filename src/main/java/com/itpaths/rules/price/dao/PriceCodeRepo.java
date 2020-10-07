package com.itpaths.rules.price.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PriceCodeRepo extends CrudRepository<PriceCode, String> {
    List<PriceCode> findByPriceCd(String priceCd);
}