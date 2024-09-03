package io.github.alinatrecioke;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HoodiesSweatshirtsPage extends BasePage {
    @FindBy(css = ".product-item .product-item-link")
    private List<WebElement> items;

    @FindBy(css = ".toolbar-products:nth-child(5) .control [selected='selected']")
    private WebElement numberOfItemsDisplayedPerPage;

    public HoodiesSweatshirtsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getListOfDisplayedItems() {
        List<String> itemNames = new ArrayList<>();
        for (WebElement item : items) {
            itemNames.add(item.getText());
        }
        return itemNames;
    }

    public int getSelectedNumberOfItemsPerPage() {
        return Integer.parseInt(numberOfItemsDisplayedPerPage.getText());
    }

    public void selectItem(String itemToSelect) {
        for (WebElement item : items) {
            if (item.getText().equals(itemToSelect)) {
                Actions actions = new Actions(driver);
                actions.moveToElement(item).perform();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOf(item));
                item.click();
                break;
            }
        }
    }
}
