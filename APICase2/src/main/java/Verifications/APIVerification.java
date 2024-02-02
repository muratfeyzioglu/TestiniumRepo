package Verifications;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExtentReportListener;
import io.restassured.response.Response;
import org.testng.Assert;

public class APIVerification extends ExtentReportListener {

    public static void verifyResponseCode(Response response, int expectedStatusCode) {
        try {
            int actualStatusCode = response.getStatusCode();
            Assert.assertEquals(actualStatusCode, expectedStatusCode);
            test.log(LogStatus.PASS, "Successfully validated status code. Expected: " + expectedStatusCode
                    + ", Actual: " + actualStatusCode);
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL, "Status code validation failed: " + e.getMessage());
            test.log(LogStatus.FAIL, "Expected status code: " + expectedStatusCode + ", Actual status code: "
                    + response.getStatusCode());
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Exception occurred while validating status code: " + e.getMessage());
        }
    }

    public static void verifyResponseTime(Response response) {
        try {
            long responseTime = response.time();
            test.log(LogStatus.INFO, "API response time: " + responseTime + " milliseconds");
        } catch (Exception e) {
            test.log(LogStatus.FAIL, "Exception occurred while validating response time: " + e.getMessage());
        }
    }
}
