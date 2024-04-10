package com.sdub.pbliga.pbligawebscraper.service;

import com.sdub.pbliga.pbligawebscraper.config.PbligaPagesLoaderConfig;
import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ScrapingServiceTest {

    @InjectMocks
    private ScrapingService scrapingService;

    @Spy
    private PbligaPagesLoaderConfig pagesLoaderConfig;

    @Mock
    private WebDriver webDriver;

    @Test
    public void scrapData_nullDataType_thrownException() throws LoaderException {

        assertThrows(LoaderException.class, () -> {
            scrapingService.scrapData(null);
        });
    }

    @Test
    public void scrapData_unknownDataType_thrownException() throws LoaderException {

        assertThrows(LoaderException.class, () -> {
            scrapingService.scrapData(DataType.Unknown);
        });
    }
}
