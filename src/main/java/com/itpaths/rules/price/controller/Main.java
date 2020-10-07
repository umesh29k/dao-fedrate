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
@ApiOperation(value = "Price Data",
        notes = "Get price details")
public class Main {
    @Autowired
    private PriceService priceService;
    @ApiParam(
            name = "Object",
            type = "PriceRequest",
            value = "Price retrieval request criteria",
            example = "Get Price Details",
            required = true)
    @PostMapping("/get/priceDetails")
    public PriceResult calculatePayment(@RequestBody PriceRequest priceRequest) {
        PriceResult priceResult = priceService.calculate(priceRequest);
        return priceResult;
    }
}