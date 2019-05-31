package com.sriwin.automation.tests;

import com.sriwin.automation.base.BaseTest;
import com.sriwin.automation.exceptions.CoreException;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestNGExtentReportTest extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        try {
            initLog4J();
            loadProperties();
            initExtentReport();
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
    }


    @BeforeMethod
    public synchronized void beforeMethod(Method method, Object args[]) throws Exception {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod(Method method, Object args[]) throws Exception {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        try {
            String testCaseName = "CustId # " + "101";
            addTestCase(testCaseName);
            successReport(testCaseName, "Verify CustId in XML", "CustId Matched with XML");
            successReport(testCaseName, "Verify CustId in DB", "CustId Matched with DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            String testCaseName = "CustId # " + "102";
            addTestCase(testCaseName);
            successReport(testCaseName, "Verify CustId in XML", "CustId Matched with XML");
            failReport(testCaseName, "Verify CustId in DB", "CustId Matched with DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}