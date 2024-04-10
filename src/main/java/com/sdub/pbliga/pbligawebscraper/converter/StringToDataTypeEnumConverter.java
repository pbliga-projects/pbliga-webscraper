package com.sdub.pbliga.pbligawebscraper.converter;

import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import org.springframework.core.convert.converter.Converter;

public class StringToDataTypeEnumConverter implements Converter<String, DataType> {
    @Override
    public DataType convert(String source) {
        return DataType.valueOfUriPath(source);
    }
}
