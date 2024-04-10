package com.sdub.pbliga.pbligawebscraper.processor;

import java.util.HashMap;
import java.util.Map;

public enum DataType {

    FreeClubs("free-clubs"),
    Unknown("unknown");

    private static final Map<String, DataType> BY_DATATYPE_URI_PATH = new HashMap<>();

    static {
        for (DataType e: values()) {
            BY_DATATYPE_URI_PATH.put(e.dataTypeUriPath, e);
        }
    }

    private final String dataTypeUriPath;

    DataType(String type) {
        dataTypeUriPath = type;
    }

    public String getDataTypeUriPath() {
        return dataTypeUriPath;
    }

    public static DataType valueOfUriPath(String uriPath) {
        DataType dataType = BY_DATATYPE_URI_PATH.get(uriPath);
        if (dataType == null) {
            return DataType.Unknown;
        }
        return dataType;
    }

}
