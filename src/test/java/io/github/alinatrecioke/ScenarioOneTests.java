package io.github.alinatrecioke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScenarioOneTests extends BaseTest {

    @Test
    void testScenarioOne() throws InterruptedException {

        //Find mens Hoodies & Sweatshirts section.
        HomePage homePage = new HomePage(driver);
        homePage.selectCategoryMen();
        MenCategoryPage menCategoryPage = new MenCategoryPage(driver);
        menCategoryPage.selectCategoryHoodiesSweatshirts();
        HoodiesSweatshirtsPage hoodiesSweatshirtsPage = new HoodiesSweatshirtsPage(driver);

        //Assert that the displayed number of jackets matches the selected number of jackets displayed per page
        List<String> itemsDisplayedPerPage = hoodiesSweatshirtsPage.getListOfDisplayedItems();
        int expected = hoodiesSweatshirtsPage.getSelectedNumberOfItemsPerPage();
        int actual = itemsDisplayedPerPage.size();
        boolean isDisplayedItemsWithinLimit = expected <= actual;
        Assertions.assertTrue(isDisplayedItemsWithinLimit, "Number of displayed items does no match the selected limit.");

        //Select “Frankie Sweatshirt”, open its details, select size, colour and quantity.
        String productNameToSelect = "Frankie Sweatshirt";
        hoodiesSweatshirtsPage.selectItem(productNameToSelect);
        ProductPage productPage = new ProductPage(driver);
        String sizeToSelect = "L";
        productPage.selectSize(sizeToSelect);
        String colorToSelect = "Green";
        productPage.selectColor(colorToSelect);
        int numberOfItemsAddedToCart = 3;
        productPage.selectQuantity(numberOfItemsAddedToCart);
        productPage.clickAddToCart();

        //Assert that counter of items added to cart is displayed
        Assertions.assertTrue(productPage.iconCounterIsDisplayed(), "Counter is not displayed.");

        //Assert that cart icon is updated with product quantity
        Assertions.assertEquals(numberOfItemsAddedToCart, productPage.getItemsInCartNumber(), "Quantity of items added to cart does not match.");

        //Assert that product properties matches the ones added to the cart.
        productPage.openMiniCart();
        productPage.clickViewCart();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assertions.assertEquals(productNameToSelect, shoppingCartPage.getProductName(), "Product name does not match.");
        Assertions.assertEquals(sizeToSelect, shoppingCartPage.getProductSize(), "Product size does not match.");
        Assertions.assertEquals(colorToSelect, shoppingCartPage.getProductColor(), "Product color does not match.");
        Assertions.assertEquals(numberOfItemsAddedToCart, shoppingCartPage.getQuantity(), "Product quantity added to cart does not match.");


        //Proceed Checkout and fill the form.
        shoppingCartPage.clickProceedToCheckout();
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

        //Complete the order.
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.clickButtonPlaceTheOrder();

        //Assert that order is confirmed successfully.
        SuccessPage successPage = new SuccessPage(driver);
        Assertions.assertEquals("Thank you for your purchase!", successPage.getSuccessMessageText(), "Success message does not match.");
        Assertions.assertTrue(successPage.successMessageIsDisplayed());
    }
}

