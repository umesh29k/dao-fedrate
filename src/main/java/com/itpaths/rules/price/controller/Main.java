package com.itpaths.rules.price.controller;

import com.itpaths.rules.price.model.Payment;
import com.itpaths.rules.price.model.Price;
import com.itpaths.rules.price.serice.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation(value = "Create user",
        notes = "This method creates a new user")
public class Main {
    @Autowired
    private PaymentService paymentService;
    @ApiParam(
            name = "firstName",
            type = "String",
            value = "First Name of the user",
            example = "Vatsal",
            required = true)
    @PostMapping("/products/subscriptions")
    public Payment calculatePayment(@RequestBody Price price) {
        Payment payment = new Payment();
        paymentService.calculate(price);
        return new Payment();
    }

}