package TestCases;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import Log.Logs;
import pages.HomePage;
import pages.*;
import utilities.Driver;
import utilities.ExcelReader;

public class TestCase {

	HomePage home = new HomePage();
	BasketPage basket = new BasketPage();
	Logs loggers = new Logs();
	// private ExcelCellReader excelReader = new ExcelCellReader();
	Actions actions = new Actions(Driver.getDriver());
	JavascriptExecutor jre = (JavascriptExecutor) Driver.getDriver();

	@Test
	public void test_case_001() throws InterruptedException, IOException {
		home.clickAcceptButtonCookies();
		home.clickGenderManButton();
		Assert.assertEquals(home.getCurrentURL(), "https://beymen.com", "Page is not opened successfully");

		home.setSearchArea(ExcelReader.readCell(0, 0));// şort
		loggers.info("First Value Searched");

		home.clearnSearchArea();
		loggers.info("Search deleted");

		home.setSearchArea(ExcelReader.readCell(0, 1));
		loggers.info("Shirts listed");

		home.sendENTERSearchArea();

		home.selectRandomProduct();
		loggers.info("Random product opened.");

		home.WritePropertiesToFile();
		loggers.info("item deatils writing to TestFile.txt");

		String productPrice = home.getProductPrice();

		home.selectRandomSize();
		home.clickAddToBasketBtn();
		loggers.info("item added to basket");

		basket.clickBasketBtn();
		Assert.assertEquals(basket.productPriceInBasket.getText(), productPrice);

		basket.clickCountDropDown();
		basket.clickOptionTwo();

		Assert.assertEquals(basket.getCountDropDown(), "2");

		basket.clickDeleteItemBtn();
		boolean isShown = basket.emptyMessage.isDisplayed();
		Assert.assertTrue(isShown);
		if (isShown == true) {
			loggers.info("items deleted");
		} else {
			loggers.error("items cannot deleted");
		}

	}

}
