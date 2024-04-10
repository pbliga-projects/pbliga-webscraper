package com.sdub.pbliga.pbligawebscraper.model.datalake;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        visible = true,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FreeClub.class, name = "free-club"),
})
public class DataLakeModel {
}
