package com.sdub.pbliga.pbligawebscraper;

import com.sdub.pbliga.pbligawebscraper.config.TestConfig;
import com.sdub.pbliga.pbligawebscraper.controller.DataPullingController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true"},
        classes = {TestConfig.class})
public class PbligaWebscraperApplicationTests {
    @InjectMocks
    private DataPullingController scraperController;

    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Test
    void controllerLoads() {
        Assertions.assertThat(scraperController).isNotNull();
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
