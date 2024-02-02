package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1920,1080", "--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);

		return driver;

	}

	public static void PageScroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll down by 500 pixels
		js.executeScript("window.scrollBy(0,-500)");

	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
