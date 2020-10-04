package com.itpaths.rules.price.serice;

import com.itpaths.rules.price.api.Rules;
import com.itpaths.rules.price.model.Payment;
import com.itpaths.rules.price.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private Rules rules;

    public Payment calculate(Price price) {
        Payment payment = new Payment();
        price = rules.calculatePrice(price);

        return payment;
    }
}
