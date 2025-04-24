package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton = By.id("continue");
    By checkoutButton = By.id("checkout");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
}
