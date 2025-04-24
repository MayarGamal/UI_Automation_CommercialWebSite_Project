package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;

    // Locators for the most expensive products (modify if necessary)
    By firstProductPrice = By.xpath("(//div[@class='inventory_item_price'])[1]");
    By secondProductPrice = By.xpath("(//div[@class='inventory_item_price'])[2]");
    By addToCartButton = By.xpath("//button[contains(@class,'btn_inventory')]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public double getFirstProductPrice() {
        String priceText = driver.findElement(firstProductPrice).getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    public double getSecondProductPrice() {
        String priceText = driver.findElement(secondProductPrice).getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    public void addMostExpensiveProductsToCart() {
        driver.findElements(addToCartButton).get(0).click(); // Add first product
        driver.findElements(addToCartButton).get(1).click(); // Add second product
    }
}
