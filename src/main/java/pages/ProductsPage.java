package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductsPage {
    WebDriver driver;

    By productContainer = By.className("inventory_item");
    By productPrice = By.className("inventory_item_price");
    By addToCartButton = By.xpath(".//button[contains(@class,'btn_inventory')]"); // Note the '.' for relative xpath

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addMostExpensiveProductsToCart() {
        List<WebElement> products = driver.findElements(productContainer);

        // Create a map to store product and its price
        Map<WebElement, Double> productPriceMap = new HashMap<>();

        for (WebElement product : products) {
            String priceText = product.findElement(productPrice).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            productPriceMap.put(product, price);
        }

        // Sort products by price in descending order
        List<Map.Entry<WebElement, Double>> sortedProducts = new ArrayList<>(productPriceMap.entrySet());
        sortedProducts.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        // Add the top two expensive products to the cart
        for (int i = 0; i < 2 && i < sortedProducts.size(); i++) {
            WebElement product = sortedProducts.get(i).getKey();
            product.findElement(addToCartButton).click();
        }
    }
}
