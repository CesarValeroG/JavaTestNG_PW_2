package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;


public class CheckOutPage extends BasePage {

    private final Locator checkOutTitle;
    private final Locator verifyItemNames;
    private final Locator totalCost;
    private final Locator finishButton;

    public CheckOutPage(Page page) {
        super(page);
        // Initialize locators using Playwright's best practices
        this.checkOutTitle = page.getByTestId("title");
        this.verifyItemNames = page.getByTestId("inventory-item-name");
        this.totalCost = page.getByTestId("total-label");
        this.finishButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Finish"));

    }

    public String getCheckOutTitle() {
        return checkOutTitle.textContent();
    }

    // 2. Verificar que los productos elegidos están en el resumen
    public boolean isProductInOrder(String productName) {
        return verifyItemNames.filter(new Locator.FilterOptions().setHasText(productName)).isVisible();
    }

    public List<String> getAllItemNames() {
        return verifyItemNames.allTextContents();
    }

    // 3. Verificar que el total no sea mayor a 60
    public double getTotal() {
        String text = totalCost.textContent(); // "Total: $28.06"
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public void clickFinish() {
        finishButton.click();
    }
}
