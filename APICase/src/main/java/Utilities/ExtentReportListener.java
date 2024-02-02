package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListener implements ITestListener {

    protected static ExtentReports reports;
    protected static ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = reports.startTest(result.getMethod().getMethodName());
        test.log(LogStatus.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(LogStatus.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(LogStatus.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(LogStatus.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        reports.endTest(test);
        reports.flush();
    }

    // Other methods like onStart, onTestFailedButWithinSuccessPercentage can be
    // implemented as needed
}
