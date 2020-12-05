package com.jrstep.controller;

import com.jrstep.exception.ApiException;
import com.jrstep.model.RInput;
import com.jrstep.service.DataRetrievalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiOperation(value = "Data",
        notes = "Get details")
public class Main {
    @Autowired
    private DataRetrievalService dataRetrievalService;
    /*@Autowired
    InsertService insertService;*/
    @ApiParam(
            name = "Object",
            type = "Request",
            value = "Retrieval request criteria",
            example = "Get Details",
            required = true)
    @PostMapping("/get/data")
    public List<RInput> calculatePayment(@RequestBody RInput input) {
        List<RInput> dataResult = null;
        try {
            dataRetrievalService.calculate();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return dataResult;
    }
}