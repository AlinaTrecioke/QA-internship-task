package io.github.alinatrecioke;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PantsPage extends BasePage {


    @FindBy(css = "div:nth-of-type(11) > div[role='tab']")
    private WebElement filterPrice;

    @FindBy(css = "div:nth-of-type(11) > div[role='tabpanel'] > ol > li")
    private List<WebElement> priceOptions;

    @FindBy(css = "select#sorter")
    private WebElement dropdownSorting;

    @FindBy(css = ".product-item .price")
    private List<WebElement> prices;

    @FindBy(css = ".filter-value")
    private WebElement activePriceFilter;


    @FindBy(css = ".product-item .product-item-link")
    private List<WebElement> items;

    public PantsPage(WebDriver driver) {
        super(driver);
    }

    public List<Double> getListOfPrices() {
        List<Double> listOfPrices = new ArrayList<>();
        for (WebElement price : prices) {
            listOfPrices.add(Double.parseDouble(price.getText().substring(1)));
        }
        return listOfPrices;
    }

    public void clickPriceFilterOption() {
        filterPrice.click();
    }

    public void selectCheapestPriceRangeOption() {
        priceOptions.getFirst().click();
    }

    public Double getMinPriceRangeLimit() {

        String[] priceRanges = activePriceFilter.getText().split(" - ");
        return Double.parseDouble(priceRanges[0].substring(1));
    }

    public Double getMaxPriceRangeLimit() {

        String[] priceRanges = activePriceFilter.getText().split(" - ");
        return Double.parseDouble(priceRanges[1].substring(1));
    }

    public void selectProductWithMinPrice() {
        List<Double> listOfPrices = getListOfPrices();
        double minPrice = listOfPrices.stream().mapToDouble(Double::doubleValue).min().orElseThrow(() -> new IllegalArgumentException("List is empty"));
        items.get(listOfPrices.indexOf(minPrice)).click();
    }

    public void selectRandomItem() {
        for (WebElement item : items) {
            Random random = new Random();
            int randomIndex = random.nextInt(items.size());
            items.get(randomIndex).click();
            break;
        }
    }
}

