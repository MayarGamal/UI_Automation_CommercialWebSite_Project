package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    WebDriver driver;

    By cartProductNames = By.className("inventory_item_name");
    By checkoutButton = By.className("shopping_cart_link");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public boolean areProductsInCart(String firstProductName, String secondProductName) {
        List<WebElement> products = driver.findElements(cartProductNames);

        // Check if there are at least two products in the cart
        if (products.size() < 2) {
            return false;
        }

        // Verify the product names
        return products.get(0).getText().contains(firstProductName) &&
                products.get(1).getText().contains(secondProductName);
    }
}
