package io.github.alinatrecioke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessPage extends BasePage {

    @FindBy(css = "[data-ui-id]")
    private WebElement successMessage;

    @FindBy(css = ".checkout-success > p:nth-of-type(1)")
    private WebElement orderNumber;

    public SuccessPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(orderNumber));
        return successMessage.getText();
    }

    public boolean successMessageIsDisplayed() {
        return successMessage.isDisplayed();
    }
}
