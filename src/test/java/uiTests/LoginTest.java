package uiTests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // Verifies that submitting empty username and password shows the required username validation message.
    @Test
    public void emptyCredentialsShowsError() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("", "");
        assert loginPage.getErrorMessage().contains("Username is required");
    }

    // Verifies that leaving the password empty shows the required password validation message.
    @Test
    public void emptyPasswordShowsError() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("standard_user", "");
        assert loginPage.getErrorMessage().contains("Password is required");
    }

    // Verifies that entering invalid credentials shows the authentication error message.
    @Test
    public void invalidUsernameShowsError() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("invalid_user", "invalid_password");
        assert loginPage.getErrorMessage().contains("Username and password do not match any user in this service");
    }

    // Verifies that valid credentials redirect the user to the Products page after login.
    @Test
    public void testSuccessfulLogin() {
        loginPage.validateLoginPageIsDisplayed();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.validateIsOnProductsPage();
    }
}
