package com.itpaths.rules.price.controller;

import com.itpaths.rules.price.dao.model.Calndr;
import com.itpaths.rules.price.exception.ApiException;
import com.itpaths.rules.price.model.PriceResult;
import com.itpaths.rules.price.model.PriceRequest;
import com.itpaths.rules.price.serice.InsertService;
import com.itpaths.rules.price.serice.PriceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiOperation(value = "Price Data",
        notes = "Get price details")
public class Main {
    @Autowired
    private PriceService priceService;
    /*@Autowired
    InsertService insertService;*/
    @ApiParam(
            name = "Object",
            type = "PriceRequest",
            value = "Price retrieval request criteria",
            example = "Get Price Details",
            required = true)
    @PostMapping("/get/priceDetails")
    public List<PriceResult> calculatePayment(@RequestBody PriceRequest priceRequest) {
        List<PriceResult> priceResult = null;
        try {
            priceResult = priceService.calculate(priceRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return priceResult;
    }
/*

    @ApiParam(
            name = "Object",
            type = "InsertData",
            required = true)
    @PostMapping("/get/insert")
    public void insertService(@RequestBody Calndr calndr) {
        insertService.insertCalndr(calndr);
    }
*/

}