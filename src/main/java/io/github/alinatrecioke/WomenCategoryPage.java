package io.github.alinatrecioke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenCategoryPage extends BasePage {

    @FindBy(css = "ul:nth-of-type(2) > li:nth-of-type(1) > a")
    private WebElement categoryPants;

    public WomenCategoryPage(WebDriver driver) {
        super(driver);
    }

    public void selectCategoryPants() {
        categoryPants.click();
    }
}
