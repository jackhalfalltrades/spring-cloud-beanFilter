package com.maat.bestbuy.integration.web.controller;

import com.maat.bestbuy.integration.model.Payload;
import com.maat.bestbuy.integration.service.OperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class OperationsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationsController.class);

    private OperationsService operationsService;

    @Autowired
    public OperationsController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @PostMapping(value = "/load/operations/dropdown", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @CrossOrigin
    @ResponseBody
    DeferredResult<MappingJacksonValue>/*Observable<List<Dropdown>>*/ getDropdown(@Valid @RequestBody Payload payload) {
        LOGGER.debug("getDropdowns(): Payload: {} ", payload);
        LOGGER.info("getDropdowns(): -> initiated");
        MappingJacksonValue mappingJacksonValue = operationsService.getDropdown(payload);
        DeferredResult<MappingJacksonValue> deferredResult = new DeferredResult<>();
        deferredResult.setResult(mappingJacksonValue);
        deferredResult.setErrorResult(mappingJacksonValue);
        LOGGER.info("getDropdowns(): -> completed");
        return deferredResult;
       // return operationsService.loadDropdown(payload);
    }
}
