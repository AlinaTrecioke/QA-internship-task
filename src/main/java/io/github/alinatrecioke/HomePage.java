package io.github.alinatrecioke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "li:nth-of-type(3) > a[role='menuitem'] > span:nth-of-type(2)")
    private WebElement categoryMen;

    @FindBy(css = "[data-action] > [role] > [role='presentation']:nth-of-type(2) > [tabindex] span:nth-of-type(2)")
    private WebElement categoryWomen;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectCategoryMen() {
        categoryMen.click();
    }

    public void selectCategoryWomen() {
        categoryWomen.click();
    }
}
