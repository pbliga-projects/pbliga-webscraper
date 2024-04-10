package com.sdub.pbliga.pbligawebscraper.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.sdub.pbliga.pbligawebscraper.exception.LoaderException;
import com.sdub.pbliga.pbligawebscraper.model.datalake.DataLakeModel;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class PersistService {

    private final String storagePath;

    public PersistService(@Value("${data.storage.path}") String storagePath) {
        this.storagePath = storagePath;
    }

    public void save(String jsonData) {

        Path path = Paths.get(storagePath, DataType.FreeClubs.name() + "_" + LocalDate.now());
        try {
            Files.write(path, jsonData.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to save data to File: " + path);
            e.printStackTrace();
        }
    }
}
