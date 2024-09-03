package io.github.alinatrecioke;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ProductPage extends BasePage {

    @FindBy(css = ".size.swatch-attribute > div[role='listbox'] > div")
    private List<WebElement> elementsSize;

    @FindBy(css = ".color.swatch-attribute > div[role='listbox'] > div")
    private List<WebElement> elementsColor;

    @FindBy(css = "input#qty")
    private WebElement inputQuantity;

    @FindBy(css = "button#product-addtocart-button > span")
    private WebElement buttonAddToCart;

    @FindBy (css =".action.showcart > .counter.qty")
    private WebElement buttonOpenMiniCart;

    @FindBy(css = ".counter-number")
    private WebElement iconCounter;

    @FindBy(css = ".action.viewcart > span")
    private WebElement linkViewCart;

    @FindBy(css = "div[role='alert'] > .message.message-success.success")
    private WebElement successMessageAddedToCart;

    @FindBy(css = "li > .product a[title='Remove item']")
    private List<WebElement> buttonsDeleteInMiniCart;

    @FindBy(css = ".action-accept.action-primary > span")
    private WebElement buttonConfirmDelete;

    @FindBy(css = "button#top-cart-btn-checkout")
    private WebElement buttonProceedToCheckoutAtMiniCart;

    @FindBy(css = "button#btn-minicart-close")
    private WebElement buttonCloseMiniCart;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectSize(String sizeToSelect) {
        for (WebElement size : elementsSize) {
            if (size.getText().equals(sizeToSelect)) {
                size.click();
            }
        }
    }

    public void selectRandomSize() {
        Random random = new Random();
        int randomIndex = random.nextInt(elementsSize.size());
        elementsSize.get(randomIndex).click();
    }

    public void selectColor(String colorToSelect) {
        for (WebElement color : elementsColor) {
            if (Objects.equals(color.getAttribute("aria-label"), colorToSelect)) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(color));
                color.click();
            }
        }
    }

    public void selectRandomColor() {
        Random random = new Random();
        int randomIndex = random.nextInt(elementsColor.size());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(elementsColor.get(randomIndex)));
        elementsColor.get(randomIndex).click();
    }

    public void selectQuantity(int quantity) {
        inputQuantity.clear();
        inputQuantity.sendKeys(String.valueOf(quantity));
    }

    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        buttonAddToCart.click();
    }

    public boolean iconCounterIsDisplayed() {
        return iconCounter.isDisplayed();
    }

    public int getItemsInCartNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By ajaxLoadingSelector = By.cssSelector(".ajax-loading");
        wait.until((ExpectedCondition<Boolean>) d -> {
            try {
                WebElement loadingElement = d.findElement(ajaxLoadingSelector);
                return false;
            } catch (Exception e) {
                return true;
            }
        });
        return Integer.parseInt(iconCounter.getText());
    }

    public void openMiniCart() {
        buttonOpenMiniCart.click();
    }

    public void clickViewCart() {
        linkViewCart.click();
    }

    public void deleteRandomItem() {
        Random random = new Random();
        int randomIndex = random.nextInt(buttonsDeleteInMiniCart.size());
        buttonsDeleteInMiniCart.get(randomIndex).click();
    }

    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonConfirmDelete));
        buttonConfirmDelete.click();
    }

    public void clickProceedToCheckout() {
        buttonProceedToCheckoutAtMiniCart.click();
    }

    public void clickButtonCloseMiniCart() {
        buttonCloseMiniCart.click();
    }
}
