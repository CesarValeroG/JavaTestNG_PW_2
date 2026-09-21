package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage extends BasePage {

    private final Locator checkoutButton;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator postalCodeInput;
    private final Locator continueButton;

    public CartPage(Page page) {
        super(page);
        // Initialize locators using Playwright's best practices
        // Using different methods to locate elements: by role, placeholder, label, and test ID
        this.checkoutButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Checkout"));
        this.firstNameInput = page.getByPlaceholder("First Name");
        this.lastNameInput = page.getByLabel("Last Name");
        this.postalCodeInput = page.getByTestId("postalCode");
        this.continueButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"));
    }

    public void clickCheckout() {
        assertThat(checkoutButton).isVisible();
        checkoutButton.click();
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        assertThat(firstNameInput).isVisible();
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        postalCodeInput.fill(postalCode);
    }

    // Combined method (optional, for the happy path)
    public void completeCheckout(String firstName, String lastName, String postalCode) {
        clickCheckout();
        fillCheckoutInformation(firstName, lastName, postalCode);
        clickContinue();
    }
}