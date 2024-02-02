package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BasePage {
	protected static final Logger log = LogManager.getLogger(HomePage.class);
	public static String envURL = "https://beymen.com.tr";

	public BasePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public static String getScreenshot(String name) throws IOException {

		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";

		File finalDestination = new File(target);
		FileUtils.copyFile(source, finalDestination);
		return target;
	}

	public static WebElement waitForClickablility(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
