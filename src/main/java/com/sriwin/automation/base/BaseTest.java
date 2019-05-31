package com.sriwin.automation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sriwin.automation.config.PropertyManager;
import com.sriwin.automation.constants.AppConstants;
import com.sriwin.automation.exceptions.CoreException;
import com.sriwin.automation.utils.DirUtil;
import com.sriwin.automation.utils.ExceptionUtil;
import com.sriwin.automation.utils.ExtentReportUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.sriwin.automation.constants.ExtentReportConstants.HTML_BREAK_TAG;

public class BaseTest {
    protected static ExtentReports extentReports;
    protected static Map<String, ExtentTest> extentTestMap = new HashMap<String, ExtentTest>();

    protected static void initLog4J() throws CoreException {
        try {
            String log4jFilePath = DirUtil.getUserDir() + "log4j.properties";
            PropertyConfigurator.configure(log4jFilePath);
        } catch (Exception e) {
        }
    }

    protected static void loadProperties() throws CoreException {
        try {
            String configPropFilePath = DirUtil.getUserDir() + AppConstants.CONFIG_PROPERTIES_FILE;
            PropertyManager propertyManager = PropertyManager.getInstance();
            propertyManager.loadProperties(configPropFilePath);
        } catch (Exception e) {
            throw ExceptionUtil.handleCoreException("Exception occurred while loading config.properties file", e);
        }
    }


    public static void initExtentReport() {
        try {
            String filePath = ExtentReportUtil.getExtentReportLocation();

            File file = new File(filePath);

            FileUtils.forceMkdir(new File(file.getParent()));

            PropertyManager propertyManager = PropertyManager.getInstance();
            String reportTitle = propertyManager.getStringValue(AppConstants.REPORT_TITLE);

            ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(filePath);
            extentHtmlReporter.config().setChartVisibilityOnOpen(true);
            extentHtmlReporter.config().setDocumentTitle(reportTitle);
            extentHtmlReporter.config().setReportName(reportTitle);
            extentHtmlReporter.config().setTheme(Theme.DARK);

            extentReports = new ExtentReports();
            extentReports.attachReporter(extentHtmlReporter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTestCase(String custId) {
        ExtentTest extentTest = extentReports.createTest(custId);
        extentTestMap.put(custId, extentTest);
    }

    public static void successReport(String custId,
                                     String stepName,
                                     String stepDescription) {

        StringBuilder builder = new StringBuilder();
        builder.append(stepName + HTML_BREAK_TAG);
        builder.append(stepDescription + HTML_BREAK_TAG);
        builder.append("Screenshot" + HTML_BREAK_TAG);

        ExtentTest extentTest = extentTestMap.get(custId);
        extentTest.pass(builder.toString());
        extentReports.flush();
    }


    public static void failReport(String custId,
                                  String stepName,
                                  String stepDescription) {

        StringBuilder builder = new StringBuilder();
        builder.append(stepName + HTML_BREAK_TAG);
        builder.append(stepDescription + HTML_BREAK_TAG);
        builder.append("Screenshot" + HTML_BREAK_TAG);

        ExtentTest extentTest = extentTestMap.get(custId);
        extentTest.fail(builder.toString());
        extentReports.flush();
    }
}
