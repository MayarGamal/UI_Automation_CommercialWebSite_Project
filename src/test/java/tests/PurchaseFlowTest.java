package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.*;
import utils.DriverFactory;

public class PurchaseFlowTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OverviewPage overviewPage;

    @BeforeMethod
    public void setUpp() {
        DriverFactory.setDriver();
        driver = DriverFactory.getDriver();  // Ensure you're getting the driver after initialization
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
    }

    @Test
    public void testPurchaseFlow() throws InterruptedException {
        // Step 1: Login
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        // Step 2: Verify navigation to products page
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));

        // Step 3: Add most expensive two products to cart
        productsPage.addMostExpensiveProductsToCart();

        // Ensure the page has loaded and products are added (give time for page to load properly)
       Thread.sleep(3000); // You can use WebDriverWait here for a more robust solution

        // Step 4: Go to cart page
        cartPage.clickCheckout();
//        Thread.sleep(10000);
        // Step 5: Verify correct products in the cart
        Assert.assertTrue(cartPage.areProductsInCart("Sauce Labs Backpack", "Sauce Labs Fleece Jacket"));
        Thread.sleep(3000);
//        // Step 6: Fill in checkout form
        checkoutPage.clickCheckout();
        checkoutPage.fillCheckoutForm("John", "Doe", "90210");
        checkoutPage.clickContinue();

        // Step 7: Verify navigation to Overview page
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));

        // Step 8: Verify total before taxes
        String itemsTotal = overviewPage.getItemsTotalBeforeTax();
        Assert.assertTrue(itemsTotal.contains("79.98"));

        // Step 9: Verify the URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");

        // Step 10: Finish the purchase
        overviewPage.clickFinish();

        // Step 11: Verify success message
        Assert.assertTrue(driver.getPageSource().contains("Thank you for your order!"));
        Assert.assertTrue(driver.getPageSource().contains("Your order has been dispatched"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        DriverFactory.closeDriver();  // Close driver after test
    }
}
