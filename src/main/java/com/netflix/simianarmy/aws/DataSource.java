package com.netflix.simianarmy.aws;

public class DataSource {
    private final String dbDriver;
    private final String dbUser;
    private final String dbPass;
    private final String dbUrl;

    /**
     */
    public DataSource(String dbDriver, String dbUser, String dbPass, String dbUrl) {
        this.dbDriver = dbDriver;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        this.dbUrl = dbUrl;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public String getDbUrl() {
        return dbUrl;
    }
}
