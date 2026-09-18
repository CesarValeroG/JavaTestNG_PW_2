package uiTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckOutTest extends BaseTest {

    // Verifies that the checkout process completes successfully with valid information.
    @Test
    public void testSuccessfulCheckout() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login(username, password);

        productsPage.validateIsOnProductsPage();
        productsPage.selectItem("Sauce Labs Backpack");
        productsPage.selectItem("Sauce Labs Onesie");
        productsPage.goToCart();

        cartPage.completeCheckout("John", "Doe", "12345");

        //Validate that is on the checkout overview page
        Assert.assertEquals(checkoutPage.getCheckOutTitle(), "Checkout: Overview", "Not on the Checkout Overview page.");

        // Validate that the selected products are in the checkout overview
        List<String> expectedProducts = List.of("Sauce Labs Backpack", "Sauce Labs Onesie");
        List<String> actualProducts = checkoutPage.getAllItemNames();
        Assert.assertTrue(actualProducts.containsAll(expectedProducts));

        // Validate that the total price is not greater than 60
        Assert.assertTrue(checkoutPage.getTotal() <= 60, "Total price is greater than 60: " + checkoutPage.getTotal());

        checkoutPage.clickFinish();
    }
}
