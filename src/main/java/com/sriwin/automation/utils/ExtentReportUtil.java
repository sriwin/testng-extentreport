package com.sriwin.automation.utils;

import com.sriwin.automation.config.PropertyManager;
import com.sriwin.automation.constants.AppConstants;
import com.sriwin.automation.constants.DateConstants;

import java.io.File;

public class ExtentReportUtil {
    public static String getExtentReportLocation() {
        String reportLocation = null;
        try {
            reportLocation = DirUtil.getReportDir();
            String reportFileName = getReportFileName();
            String runDate = DateUtil.getDate(DateConstants.YYYY_MM_DD_HH_MM_SS);
            String reportFilePath = reportLocation + File.separator + reportFileName + "_" + runDate + ".html";
            reportLocation = reportFilePath;

        } catch (Exception e) {
            System.out.println("Error occurred while creating the report location\n" + e.getMessage());
        }
        return reportLocation;
    }

    private static String getReportFileName() {
        PropertyManager propertyManager = PropertyManager.getInstance();
        String reportFileName = propertyManager.getPropertyValue(AppConstants.REPORT_FILE_NAME);
        return reportFileName;
    }
}