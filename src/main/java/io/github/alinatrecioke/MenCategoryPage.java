package io.github.alinatrecioke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenCategoryPage extends BasePage {

    @FindBy(css = ".categories-menu > ul:nth-of-type(1) > li:nth-of-type(1) > a")
    private WebElement categoryHoodiesSweatshirts;

    public MenCategoryPage(WebDriver driver) {
        super(driver);
    }

    public void selectCategoryHoodiesSweatshirts() {
        categoryHoodiesSweatshirts.click();
    }
}
