package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.api.Rules;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.model.PriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private Rules rules;

    public PriceResult calculate(PriceRequest priceRequest) {
        PriceResult priceResult = new PriceResult();
        priceRequest = rules.calculatePrice(priceRequest);
        System.out.println(priceRequest.toString());
        return priceResult;
    }
}
