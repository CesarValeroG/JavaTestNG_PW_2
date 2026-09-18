package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage extends BasePage {

    // Locators declarados como privados e inmutables
    private final Locator loginLogo;
    private final Locator userNameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    // Constructor que recibe la Page activa
    public LoginPage(Page page) {
        super(page); // Asigna la instancia de 'page' en BasePage

        // Inicialización de locators usando las mejores prácticas de Playwright
        this.loginLogo = page.getByText("Swag Labs");
        this.userNameInput = page.getByPlaceholder("Username");
        this.passwordInput = page.getByPlaceholder("Password");
        this.loginButton = page.locator("#login-button");
        this.errorMessage = page.locator(".error-message-container");
    }

    // --- ACCIONES Y MÉTODOS DE LA PÁGINA ---

    public void validateLoginPageIsDisplayed() {
        assertThat(loginLogo).isVisible();
    }

    public void enterUsername(String username) { userNameInput.fill(username); }
    public void enterPassword(String password) { passwordInput.fill(password); }
    public void clickLogin() { loginButton.click(); }

    // Metodo helper para realizar el login completo
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Get the message displayed in the error message container
    public String getErrorMessage() {
        return errorMessage.textContent();
    }
}
