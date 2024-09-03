package io.github.alinatrecioke;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class ShoppingCartPage extends BasePage {

    @FindBy(css = ".col.item  .product-item-name")
    private WebElement productName;

    @FindBy(css = ".col.item  dl > dd:nth-of-type(1)")
    private WebElement productSize;

    @FindBy(css = ".col.item  dl > dd:nth-of-type(2)")
    private WebElement productColor;

    @FindBy(css = ".input-text.qty")
    private WebElement inputQuantity;

    @FindBy(css = "button[title='Proceed to Checkout'] > span")
    private WebElement buttonProceedToCheckout;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductSize() {
        return productSize.getText();
    }

    public String getProductColor() {
        return productColor.getText();
    }

    public int getQuantity() {
        return Integer.parseInt(Objects.requireNonNull(inputQuantity.getAttribute("value")));
    }

    public void clickProceedToCheckout() {
        buttonProceedToCheckout.click();
    }

}
