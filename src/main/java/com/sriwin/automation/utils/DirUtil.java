package com.sriwin.automation.utils;

import java.io.File;

public class DirUtil {
    public static final String FILE_SEPARATOR = File.separator;
    public static final String WEBDRIVER_PATH = "webdriver";
    public static final String TESTDATA_PATH = "testData";
    public static final String REPORT_PATH = "report";

    public static String getUserDir() {
        String parentFilePath = System.getProperty("user.dir") + FILE_SEPARATOR;
        return parentFilePath;
    }

    public static String getWebDriverDir() {
        String parentFilePath = getUserDir() + WEBDRIVER_PATH + FILE_SEPARATOR;
        return parentFilePath;
    }

    public static String getReportDir() {
        String parentFilePath = getUserDir() + REPORT_PATH + FILE_SEPARATOR;
        return parentFilePath;
    }

    public static String getTestDataDir() {
        String parentFilePath = getUserDir() + TESTDATA_PATH + FILE_SEPARATOR;
        return parentFilePath;
    }

}