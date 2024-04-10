package com.sdub.pbliga.pbligawebscraper.service;

import com.sdub.pbliga.pbligawebscraper.converter.PbligaScraperDataConverter;
import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.messaging.service.MessagingService;
import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Lazy
public class DataPullingService {

    private final PersistService persistService;
    private final ScrapingService scrapingService;

    private final Boolean kafkaSendingEnabled;

    private final MessagingService messagingService;

    public DataPullingService(PersistService persistService, ScrapingService scrapingService,
                              @Value("${scrapped.data.kafka.sending.enabled}") Boolean kafkaSendingEnabled,
                              MessagingService messagingService) {
        this.persistService = persistService;
        this.scrapingService = scrapingService;
        this.kafkaSendingEnabled = kafkaSendingEnabled;
        this.messagingService = messagingService;
    }

    public String pullData(DataType dataType) throws LoaderException {
        List<DataLakeModel> dataElements = scrapingService.scrapData(dataType);


        if ( !CollectionUtils.isEmpty(dataElements)) {
            String jsonData = PbligaScraperDataConverter.convertHtmlToJson(dataElements);
            persistService.save(jsonData);
            //if (kafkaSendingEnabled) {
                messagingService.sendMessage(jsonData);
            //}
        }
        return dataElements.toString();
    }
}
