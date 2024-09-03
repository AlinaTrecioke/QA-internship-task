package io.github.alinatrecioke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScenarioTwoTests extends BaseTest {

    @Test
    void testScenarioTwo() {

        //Find women pants section
        HomePage homePage = new HomePage(driver);
        homePage.selectCategoryWomen();
        WomenCategoryPage womenCategoryPage = new WomenCategoryPage(driver);
        womenCategoryPage.selectCategoryPants();

        //Filter section to show the cheapest products available.
        PantsPage pantsPage = new PantsPage(driver);
        pantsPage.clickPriceFilterOption();
        pantsPage.selectCheapestPriceRangeOption();

        //Assert that prices of filtered items are in the range of selected price filter.
        List<Double> pricesOfItems = pantsPage.getListOfPrices();
        boolean pricesAreInRange = true;
        for (Double price : pricesOfItems) {
            if (price < pantsPage.getMinPriceRangeLimit() || price > pantsPage.getMaxPriceRangeLimit()) {
                pricesAreInRange = false;
                break;
            }
        }
        Assertions.assertTrue(pricesAreInRange, "Prices of filtered items does not correspond the selected price range.");

        //Select the cheapest pants and add them to the cart.
        pantsPage.selectProductWithMinPrice();
        ProductPage productPage = new ProductPage(driver);
        productPage.selectRandomSize();
        productPage.selectRandomColor();
        productPage.clickAddToCart();

        //Add 2 more products to the cart.
        int numberOfProductsAddedToCart = 2;
        for (int i = 0; i < numberOfProductsAddedToCart; i++) {
            driver.navigate().back();
            pantsPage.selectRandomItem();
            productPage.selectRandomSize();
            productPage.selectRandomColor();
            productPage.clickAddToCart();
        }

        //Assert that counter of items added to cart is displayed
        Assertions.assertTrue(productPage.iconCounterIsDisplayed());

        //Assert that cart icon is updated with product quantity
        int expectedProductsInCartNumber = 1 + numberOfProductsAddedToCart;
        Assertions.assertEquals(expectedProductsInCartNumber, productPage.getItemsInCartNumber(), "Added quantity does not match displayed number of items in cart.");

        //Remove product from the cart.
        productPage.openMiniCart();
        productPage.deleteRandomItem();
        productPage.confirmDelete();

        //Assert that number of items in the cart was reduced.
        int expectedNumberOfItemsInCart = expectedProductsInCartNumber - 1;
        Assertions.assertEquals(expectedNumberOfItemsInCart, productPage.getItemsInCartNumber(), "Number of items in cart was not reduced.");

        //Add product to the cart from suggested products.
        productPage.clickButtonCloseMiniCart();
        productPage.selectRandomSize();
        productPage.selectRandomColor();
        productPage.clickAddToCart();

        //Proceed Checkout and fill the form.
        productPage.openMiniCart();
        productPage.clickProceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterEmail(DataGenerator.getRandomEmail());
        checkoutPage.enterFirstName(DataGenerator.getRandomFirstName());
        checkoutPage.enterLastName(DataGenerator.getRandomLastName());
        checkoutPage.enterStreet(DataGenerator.getRandomAddress());
        checkoutPage.enterCity(DataGenerator.getRandomCity());
        checkoutPage.selectRandomCountry();
        checkoutPage.selectRandomRegion();
        checkoutPage.enterPostalCode(DataGenerator.getRandomPostalCode());
        checkoutPage.enterPhoneNumber(DataGenerator.getRandomPhoneNumber());
        checkoutPage.clickButtonNext();

        //Complete the order
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.clickButtonPlaceTheOrder();

        //Assert that order is confirmed successfully.
        SuccessPage successPage = new SuccessPage(driver);
        Assertions.assertEquals("Thank you for your purchase!", successPage.getSuccessMessageText(), "Success message does not match.");
        Assertions.assertTrue(successPage.successMessageIsDisplayed());
    }
    }