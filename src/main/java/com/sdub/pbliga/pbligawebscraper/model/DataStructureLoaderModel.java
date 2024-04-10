package com.sdub.pbliga.pbligawebscraper.model;

import com.sdub.pbliga.pbligawebscraper.processor.ResultSetProcessor;

public class DataStructureLoaderModel {

    private String pageUrl;

    private XpathAndCountPair xpathAndCountPair;

    private ResultSetProcessor processor;

    public DataStructureLoaderModel(String pageUrl, XpathAndCountPair xpathAndCountPair, ResultSetProcessor processor) {
        this.pageUrl = pageUrl;
        this.xpathAndCountPair = xpathAndCountPair;
        this.processor = processor;
    }

    public DataStructureLoaderModel() {

    }

    public String getPageUrl() {
        return pageUrl;
    }

    public XpathAndCountPair getXpathAndCountPair() {
        return xpathAndCountPair;
    }

    public ResultSetProcessor getProcessor() {
        return processor;
    }
}
