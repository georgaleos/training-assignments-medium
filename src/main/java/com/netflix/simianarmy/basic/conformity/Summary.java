package com.netflix.simianarmy.basic.conformity;

public class Summary {
    private final StringBuilder message;
    private final String summaryName;

    public Summary(StringBuilder message, String summaryName) {
        this.message = message;
        this.summaryName = summaryName;
    }

    public StringBuilder getMessage() {
        return message;
    }

    public String getSummaryName() {
        return summaryName;
    }
}
