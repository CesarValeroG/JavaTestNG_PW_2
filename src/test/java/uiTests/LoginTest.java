package uiTests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    // Verifies that submitting empty username and password shows the required username validation message.
    @Test
    public void emptyCredentialsShowsError() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("", "");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Expected 'Username is required' error message.");
        logger.info("Error message displayed when a value is missing: " + loginPage.getErrorMessage());
    }

    // Verifies that leaving the password empty shows the required password validation message.
    @Test
    public void emptyPasswordShowsError() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("standard_user", "");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"), "Expected 'Password is required' error message.");
        logger.info("Error message displayed when a value is missing: " + loginPage.getErrorMessage());
    }

    // Verifies that entering invalid credentials shows the authentication error message.
    @Test
    public void invalidUsernameShowsError() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("invalid_user", "invalid_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"), "Expected authentication error message.");
        logger.info("Error message displayed when invalid credentials are used: " + loginPage.getErrorMessage());
    }

    // Verifies that valid credentials redirect the user to the Products page after login.
    @Test
    public void testSuccessfulLogin() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login(username, password);

        productsPage.validateIsOnProductsPage();
        logger.info("Successfully logged in with valid credentials.");
    }
}
