package pages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Driver;
import utilities.ExcelReader;
import java.io.PrintWriter;

public class HomePage extends BasePage {

	Random random = new Random();
	ExcelReader excelreader = new ExcelReader();

	public HomePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "o-searchSuggestion__input")
	public WebElement searchArea;

	@FindBy(id = "onetrust-accept-btn-handler")
	public WebElement acceptButtonCookies;

	@FindBy(id = "genderManButton")
	public WebElement genderManButton;

	@FindBy(xpath = "//*[@id=\"o-searchSuggestion__input\"]//button]")
	public WebElement deleteBtn;

	@FindBy(xpath = "/html/body/header/div[2]/div[2]/div[2]/div/div[2]/div/div/div[1]/div/div")
	public WebElement product;

	@FindBy(xpath = "//*[@id=\"productList\"]/div")
	public List<WebElement> prodList;

	@FindBy(xpath = "/html/body/div[3]/div[1]/div[1]/div[2]/div[2]/h1/span")
	public WebElement productName;

	@FindBy(xpath = "//*[@id=\"priceNew\"]")
	public WebElement productPrice;

	@FindBy(xpath = "//*[contains(concat(' ', normalize-space(@class), ' '), 'm-variation__item')]")
	public List<WebElement> productSize;

	@FindBy(css = "button[id='addBasket']")
	public WebElement addBasketBtn;

	public void selectRandomProduct() {
		int maxProduct = prodList.size();
		int randomProduct = random.nextInt(maxProduct);
		prodList.get(randomProduct).click();
	}

	public void selectRandomSize() throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> specificSpans = new ArrayList<>();
		for (WebElement element : productSize) {
			String className = element.getAttribute("class");
			if (className.equals("m-variation__item") || className.equals("m-variation__item -criticalStock")) {
				specificSpans.add(element);
			}
		}
		int maxProductSize = specificSpans.size();
		Random random = new Random();
		int randomProductSize = random.nextInt(maxProductSize);
		specificSpans.get(randomProductSize).click();
	}

	public void setSearchArea(String text) throws InterruptedException {

		waitForClickablility(searchArea, 10);
		searchArea.click();
		searchArea.sendKeys(text);
		// ExcelCellReader.readCell(1, 1);
	}

	public void sendENTERSearchArea() {
		searchArea.sendKeys(Keys.ENTER);
	}

	public String getProductName() {
		String productNameText = productName.getText();
		return productNameText;
	}

	public String getProductPrice() {
		String productPriceText = productPrice.getText();
		return productPriceText;
	}

	public void WritePropertiesToFile() throws IOException, InterruptedException {
		Thread.sleep(1000);
		clearFile();
		String productNameText = getProductName();
		String productPriceText = getProductPrice();
		FileWriter writer = new FileWriter("TestFile.txt");
		writer.write(productNameText);
		writer.write(System.getProperty("line.separator"));

		writer.write(productPriceText);

		writer.close();
	}

	public void clearFile() throws IOException {
		FileWriter clearWriter = new FileWriter("TestFile.txt");
		PrintWriter pwOb = new PrintWriter(clearWriter);
		pwOb.print("");
		pwOb.close();
	}

	public void clickAcceptButtonCookies() {
		waitForClickablility(acceptButtonCookies, 10);
		acceptButtonCookies.click();
	}

	public void clickGenderManButton() throws InterruptedException {
		Thread.sleep(1000);
		waitForClickablility(genderManButton, 10).click();
	}

	public void clearnSearchArea() throws InterruptedException {
		Thread.sleep(1000);
		waitForClickablility(deleteBtn, 10).click();
	}

	public void clickAddToBasketBtn() throws InterruptedException {
		Thread.sleep(1000);
		waitForClickablility(addBasketBtn, 10).click();
	}

	public String getCurrentURL() {
		String currentURL = Driver.getDriver().getCurrentUrl();

		return currentURL;

	}

}
