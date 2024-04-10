package com.sdub.pbliga.pbligawebscraper.processor;

import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;
import org.openqa.selenium.WebElement;

@FunctionalInterface
public interface ResultSetProcessor {

    DataLakeModel process(WebElement element);
}
