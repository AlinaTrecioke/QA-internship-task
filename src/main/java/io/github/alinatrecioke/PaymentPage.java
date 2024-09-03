package io.github.alinatrecioke;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class PaymentPage extends BasePage {

    @FindBy(css = "button[title='Place Order']")
    private WebElement buttonPlaceTheOrder;

    @FindBy(css = "div#checkout-payment-method-load .step-title")
    private WebElement titlePaymentMethod;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonPlaceTheOrder() {
        WebElement placeOrderButton = driver.findElement(By.cssSelector("button[title='Place Order']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);

    }
}
