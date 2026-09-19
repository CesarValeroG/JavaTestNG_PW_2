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

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseTest {
    protected Page page;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;

    protected CartPage cartPage;
    protected CheckOutPage checkoutPage;
    protected Logger logger;

    protected String username;
    protected String password;

    @BeforeMethod
    public void setUp() throws IOException {
        logger = LogManager.getLogger(this.getClass());
        // Llama al BrowserFactory para inicializar el hilo actual
        page = BrowserFactory.createPage("chromium", false);

        // Inicializa las páginas pasándoles la instancia activa de 'page'
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckOutPage(page);

        // Cargar credenciales
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/config/credentials.properties"));
        username = props.getProperty("username");
        password = props.getProperty("password");

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