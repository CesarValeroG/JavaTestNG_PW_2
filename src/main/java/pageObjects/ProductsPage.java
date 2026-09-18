package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductsPage extends BasePage {

    private final Locator inventoryTitle;
    private final Locator inventoryItemName;
    private final Locator addToCartButton;
    private final Locator backToProductsButton;
    private final Locator shoppingCartBadge;



    public ProductsPage(Page page) {
        super(page);
        this.inventoryTitle = page.locator("[data-test='title']");
        this.inventoryItemName = page.locator(".inventory_item_name");
        this.addToCartButton = page.locator("[data-test^='add-to-cart-']");
        this.backToProductsButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Back to products"));
        this.shoppingCartBadge = page.locator("[data-test='shopping-cart-badge']"); //badge that shows the number of items in the cart

    }

    public void validateIsOnProductsPage() {
        assertThat(inventoryTitle).hasText("Products");
    }

    public void selectItem(String itemName) {
        Locator itemLocator = inventoryItemName.filter(new Locator.FilterOptions().setHasText(itemName));
        assertThat(itemLocator).isVisible();
        itemLocator.click();
        
        // Use first() to avoid strict mode violation with multiple "Add to cart" buttons
        Locator addToCartForItem = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart")).first();
        assertThat(addToCartForItem).isVisible();
        addToCartForItem.click();
        backToProductsButton.click();
    }

    public void goToCart() {
        shoppingCartBadge.click();
    }

 /*   public void addMultipleItemsToCart(List<String> itemNames) {
        for (String name : itemNames) {
            selectItem(name);
        }
    }  */

    //return the number of items in the cart
    public int getNumberOfItemsInCart() {
        String badgeText = shoppingCartBadge.textContent();
        return badgeText != null ? Integer.parseInt(badgeText) : 0;
    }
}