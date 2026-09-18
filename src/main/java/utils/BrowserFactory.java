package utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    // Se declaran aquí como variables de ThreadLocal para soportar ejecuciones en paralelo
    private static final ThreadLocal<Playwright> playwrightTL = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserTL = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextTL = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageTL = new ThreadLocal<>();

    public static Page createPage(String browserName, boolean headless) {
        System.out.println("Setting up browser: " + browserName + " (Headless: " + headless + ")...");
        // 1. Instanciar Playwright
        playwrightTL.set(Playwright.create());

        // Esta linea es opcional de configurar el atributo de testId para facilitar la localización de elementos en pruebas
        // Esto permite usar selectores como page.getByTestId("my-element") en lugar de depender de clases o IDs que pueden cambiar
        playwrightTL.get().selectors().setTestIdAttribute("data-test");

        // 2. Configurar y lanzar el Browser
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(headless);

        switch (browserName.toLowerCase()) {
            case "chrome":
                launchOptions.setChannel("chrome");
                browserTL.set(playwrightTL.get().chromium().launch(launchOptions));
                break;
            case "firefox":
                browserTL.set(playwrightTL.get().firefox().launch(launchOptions));
                break;
            case "webkit":
                browserTL.set(playwrightTL.get().webkit().launch(launchOptions));
                break;
            default: // chromium / chrome
                browserTL.set(playwrightTL.get().chromium().launch(launchOptions));
                break;
        }

        // 3. Crear el BrowserContext (aislamiento estilo sesión de incógnito)
        contextTL.set(browserTL.get().newContext());

        // 4. Crear y retornar la Page
        pageTL.set(contextTL.get().newPage());

        return pageTL.get();
    }

    public static void closePage() {
        // Cierre ordenado en reversa para liberar recursos de memoria
        if (pageTL.get() != null) {
            pageTL.get().close();
            pageTL.remove();
        }
        if (contextTL.get() != null) {
            contextTL.get().close();
            contextTL.remove();
        }
        if (browserTL.get() != null) {
            browserTL.get().close();
            browserTL.remove();
        }
        if (playwrightTL.get() != null) {
            playwrightTL.get().close();
            playwrightTL.remove();
        }
    }
}