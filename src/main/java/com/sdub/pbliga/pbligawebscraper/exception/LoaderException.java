package com.sdub.pbliga.pbligawebscraper.exception;

import java.io.IOException;

public class LoaderException extends Exception {
    public LoaderException(IOException e) {
        super(e);
    }

    public LoaderException() {}
}
