package com.itpaths.rules.price.controller;

import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.serice.PriceService;
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
    private PriceService priceService;
    @ApiParam(
            name = "firstName",
            type = "String",
            value = "First Name of the user",
            example = "Vatsal",
            required = true)
    @PostMapping("/products/subscriptions")
    public PriceResult calculatePayment(@RequestBody PriceRequest priceRequest) {
        PriceResult priceResult = new PriceResult();
        priceService.calculate(priceRequest);
        return new PriceResult();
    }

}