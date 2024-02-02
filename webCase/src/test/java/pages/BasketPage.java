package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.Driver;

public class BasketPage extends BasePage {

    @FindBy(className = "m-productPrice__salePrice")
    public WebElement productPriceInBasket;

    @FindBy(id = "removeCartItemBtn0-key-0")
    public WebElement deleteItem;

    @FindBy(className = "m-notification__close bwi-close")
    public WebElement closeNotification;

    @FindBy(className = "m-empty__message")
    public WebElement emptyMessage;

    @FindBy(id = "quantitySelect0-key-0")
    public WebElement countDropdown;

    @FindBy(xpath = "//*[@id=\"quantitySelect0-key-0\"]/option[2]")
    public WebElement countOptionTwo;

    @FindBy(xpath = "//a[@title='Sepetim']")
    public WebElement BasketBtn;

    public void clickCountDropDown() {
        waitForClickablility(countDropdown, 10);
        countDropdown.click();
    }

    public String getCountDropDown() {
        waitForClickablility(countDropdown, 10);
        String count = countDropdown.getText();
        return count;

    }

    public void clickOptionTwo() {
        waitForClickablility(countOptionTwo, 10);
        countOptionTwo.click();
    }

    public void clickDeleteItemBtn() {
        waitForClickablility(deleteItem, 10).click();
    }

    public void clickBasketBtn() {
        waitForClickablility(closeNotification, 10).click();
        Driver.PageScroll();
        waitForClickablility(BasketBtn, 10).click();
    }

}
