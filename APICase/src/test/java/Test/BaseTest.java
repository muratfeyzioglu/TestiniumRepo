package Test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import Utilities.ExtentReportListener;
import Utilities.FileandEnv;

@Listeners(ExtentReportListener.class)
public class BaseTest extends ExtentReportListener {

    @BeforeClass
    public void baseTest() {

        RestAssured.baseURI = FileandEnv.loadFileAndEnv().get("BaseUrl");

    }
}
