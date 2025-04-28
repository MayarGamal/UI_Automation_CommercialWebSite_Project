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

        if (products.size() < 2) {
            return false;
        }

        boolean firstProductFound = false;
        boolean secondProductFound = false;

        for (WebElement product : products) {
            String productName = product.getText();
            if (productName.contains(firstProductName)) {
                firstProductFound = true;
            }
            if (productName.contains(secondProductName)) {
                secondProductFound = true;
            }
        }

        return firstProductFound && secondProductFound;
    }
}