package com.hoteladactin.qa.listener;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Called once before any test starts
    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "./test-output/ExtentReport_" + timeStamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("Hotel Adactin Automation Report");
        spark.config().setDocumentTitle("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "YourName");
        extent.setSystemInfo("Platform", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    // Called when a test case starts
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    // Called when a test case passes
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    // Called when a test case fails
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail("Test Failed");
        test.fail(result.getThrowable());
        // You can add screenshot code here
    }

    // Called when a test case is skipped
    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }

    // Called once after all tests finish
    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Save the report
    }
}
