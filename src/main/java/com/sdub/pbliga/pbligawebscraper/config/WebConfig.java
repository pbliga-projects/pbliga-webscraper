package com.sdub.pbliga.pbligawebscraper.config;

import com.sdub.pbliga.pbligawebscraper.converter.StringToDataTypeEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDataTypeEnumConverter());
    }
}
