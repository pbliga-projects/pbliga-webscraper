package com.sdub.pbliga.pbligawebscraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    protected String getResourceFileContent(String resourceFilePath) throws IOException, URISyntaxException {
        URL file = this.getClass().getResource(resourceFilePath);
        return new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
    }
}
