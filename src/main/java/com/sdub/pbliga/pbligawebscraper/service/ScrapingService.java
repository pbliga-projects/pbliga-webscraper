package com.sdub.pbliga.pbligawebscraper.service;

import com.sdub.pbliga.pbligawebscraper.config.PbligaPagesLoaderConfig;
import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.model.DataStructureLoaderModel;
import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Lazy
public class ScrapingService {

    private static final Logger LOGGER = LogManager.getLogger(ScrapingService.class);

    private final WebDriver webDriver;

    private final PbligaPagesLoaderConfig pagesLoaderConfig;

    public ScrapingService(WebDriver webDriver, PbligaPagesLoaderConfig pagesLoaderConfig) {
        this.webDriver = webDriver;
        this.pagesLoaderConfig = pagesLoaderConfig;
    }

    public List<DataLakeModel> scrapData(DataType dataType) throws LoaderException {

        DataStructureLoaderModel loaderConfig = pagesLoaderConfig.getPages().get(dataType);
        if (loaderConfig == null) {
            throw new LoaderException();
        }

        LOGGER.error("Before open page");
        webDriver.get(loaderConfig.getPageUrl());
        LOGGER.error("After open page");
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        List<DataLakeModel> modelElements = new ArrayList<>();

        if (loaderConfig.getXpathAndCountPair().isMultipleElements() ) {
            List<WebElement> elements = webDriver.findElements(By.xpath(loaderConfig.getXpathAndCountPair().getXpath()));
            LOGGER.error("Find elements");
            Optional.ofNullable(elements).orElse(Collections.emptyList()).stream().forEach(
                    element ->  { modelElements.add(loaderConfig.getProcessor().process(element)); LOGGER.error("After Iterate element"); }
            );
        }
        else {
            WebElement element = webDriver.findElement(By.xpath(loaderConfig.getXpathAndCountPair().getXpath()));
            modelElements.add(loaderConfig.getProcessor().process(element));
        }
        webDriverEndSession(webDriver);
        return modelElements;
    }

    private void webDriverEndSession(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(1));
        driver.manage().deleteAllCookies();
        if (driver instanceof WebDriver) {
            System.out.println("river instanceof WebDriver");
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("localStorage.clear();");
        }
    }
}
