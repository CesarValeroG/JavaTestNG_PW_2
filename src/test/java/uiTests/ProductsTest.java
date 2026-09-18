package uiTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    // Verifies that adding one product to the cart updates the cart badge to 1.
    @Test
    public void testProductList() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.validateIsOnProductsPage();
        productsPage.selectItem("Sauce Labs Backpack");
        int itemCount = productsPage.getNumberOfItemsInCart();
        Assert.assertEquals(itemCount, 1, "The number of items in the cart is not as expected.");
    }

    // Verifies that adding multiple products increases the cart badge beyond 3 items.
    @Test
    public void testAddMultipleItemsToCart() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.validateIsOnProductsPage();
        productsPage.selectItem("Sauce Labs Backpack");
        productsPage.selectItem("Sauce Labs Bike Light");
        productsPage.selectItem("Sauce Labs Bolt T-Shirt");
        productsPage.selectItem("Sauce Labs Onesie");

        int itemCount = productsPage.getNumberOfItemsInCart();
        Assert.assertTrue(itemCount > 3, "The cart was expected to contain more than 3 items, but it contained: " + itemCount);
    }
}
