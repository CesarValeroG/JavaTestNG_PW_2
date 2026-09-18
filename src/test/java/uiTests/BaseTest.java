package uiTests;

import com.microsoft.playwright.Page;
import config.PlaywrightConfig;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.LoginPage;
import utils.BrowserFactory;
import pageObjects.ProductsPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

public abstract class BaseTest {
    protected Page page;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckOutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        // Llama al BrowserFactory para inicializar el hilo actual
        page = BrowserFactory.createPage("chromium", false);

        // Inicializa las páginas pasándoles la instancia activa de 'page'
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckOutPage(page);

        // Navega a la URL inicial
        page.navigate(PlaywrightConfig.BASE_URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        takeScreenShotIfError(result);

        // Cierra los recursos asignados al hilo actual
        BrowserFactory.closePage();
    }

    public void takeScreenShotIfError(ITestResult result) {
        // Captura de pantalla si la prueba falla
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getName();
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("test-output/screenshots/" + testName + ".png"))
                    .setFullPage(true));
        }
    }
}