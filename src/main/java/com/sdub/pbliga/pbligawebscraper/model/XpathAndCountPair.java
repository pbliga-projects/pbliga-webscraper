package com.sdub.pbliga.pbligawebscraper.model;

public class XpathAndCountPair {

    private String xpath;
    private boolean multipleElements;
    private boolean terminalOperation = false;
    private XpathAndCountPair xpathAndCountPair;

    public XpathAndCountPair(String xpath, boolean multipleElements, boolean termanalOperation) {
        this.xpath = xpath;
        this.multipleElements = multipleElements;
        this.terminalOperation = termanalOperation;
        this.xpathAndCountPair = xpathAndCountPair;
    }

    public String getXpath() {
        return xpath;
    }

    public boolean isMultipleElements() {
        return multipleElements;
    }

    public boolean isTerminalOperation() {
        return terminalOperation;
    }
}
