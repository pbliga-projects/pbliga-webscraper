package com.sdub.pbliga.pbligawebscraper.controller;

import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import com.sdub.pbliga.pbligawebscraper.service.DataPullingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Lazy
public class DataPullingController {

    @Value("${version}")
    String appVersion;

    private final DataPullingService dataPullingService;

    public DataPullingController(DataPullingService dataPullingService) {
        this.dataPullingService = dataPullingService;
    }

    @GetMapping("/version")
    public String getVersion() {
        return appVersion;
    }

    @GetMapping("/pull/{dataType}")
    public String pullData(@PathVariable("dataType") DataType dataType) throws LoaderException {
        return dataPullingService.pullData(dataType);
    }
}
