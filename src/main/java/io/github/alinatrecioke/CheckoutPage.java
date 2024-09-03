package io.github.alinatrecioke;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CheckoutPage extends BasePage {

    @FindBy(css = "[data-role='email-with-possible-login'] #customer-email")
    private WebElement inputEmail;

    @FindBy(css = "input[name='firstname']")
    private WebElement inputFirstName;

    @FindBy(css = "input[name='lastname']")
    private WebElement inputLastName;

    @FindBy(css = "input[name='street[0]']")
    private WebElement inputStreetAddress;

    @FindBy(css = "input[name='city']")
    private WebElement inputCity;

    @FindBy(css = "select[name='region_id']")
    private WebElement dropdownRegion;

    @FindBy(css = "select[name='region_id'] > option")
    private List<WebElement> listOfRegionsFromDropdown;

    @FindBy(css = "input[name='postcode']")
    private WebElement inputPostalCode;

    @FindBy(css = "select[name='country_id']")
    private WebElement dropdownCountry;

    @FindBy(css = "select[name='country_id'] > option")
    private List<WebElement> listOfCountriesFromDropdown;

    @FindBy(css = "input[name='telephone']")
    private WebElement inputPhone;

    @FindBy(css = "input[type = 'radio']")
    private WebElement radioButtonRate;

    @FindBy(css = ".action.button.continue.primary")
    private WebElement buttonNext;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void enterFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }

    public void enterStreet(String street) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", inputStreetAddress);
        inputStreetAddress.sendKeys(street);
    }

    public void enterCity(String city) {
        inputCity.sendKeys(city);
    }

    public void selectCountry(String country) {
        Select dropdown = new Select(dropdownCountry);
        dropdown.selectByVisibleText(country);
    }

    public void selectRandomCountry() {
        Select dropdown = new Select(dropdownCountry);
        Random random = new Random();
        int randomIndex = random.nextInt(listOfCountriesFromDropdown.size());
        dropdown.selectByIndex(randomIndex);
    }

    public void selectRandomRegion() {
        Select select = new Select(dropdownRegion);
        Random random = new Random();
        int randomIndex = random.nextInt(listOfRegionsFromDropdown.size());
        select.selectByIndex(randomIndex);
    }

    public void enterPostalCode(int postalCode) {
        inputPostalCode.sendKeys(String.valueOf(postalCode));
    }

    public void enterPhoneNumber(String phone) {
        inputPhone.sendKeys(phone);
    }

    public void clickButtonNext() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> {
            WebElement radioButton = driver.findElement(By.cssSelector("input[type = 'radio']"));
            return radioButton.isSelected();
        });
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", buttonNext);
        buttonNext.click();
    }
}
