package com.sdub.pbliga.pbligawebscraper.converter;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;

import java.io.IOException;
import java.util.List;

public class PbligaScraperDataConverter {

    public static String convertHtmlToJson(List<DataLakeModel> dataElements) throws LoaderException {

        try {
            PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                    .allowIfSubType("com.sdub.pbliga.pbligawebscraper.model.datalake")
                    .allowIfSubType("java.util.ArrayList")
                    .build();

            ObjectMapper objectMapper = new ObjectMapper().activateDefaultTyping(ptv,
                    ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, JsonTypeInfo.As.PROPERTY);
            ObjectWriter ow = objectMapper.writer();
            return ow.writeValueAsString(dataElements);
        } catch (IOException e) {
            throw new LoaderException(e);
        }
    }
}
