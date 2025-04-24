package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class loginTest {

    @BeforeEach // or @Before depending on JUnit version
    public void setUp() {
        DriverFactory.setDriver(); // this initializes ThreadLocal driver
    }
    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata.csv", numLinesToSkip = 1)

//    @ParameterizedTest
//    @CsvSource({
//            "invalid_user1, invalid_pass1",
//            "invalid_user2, invalid_pass2",
//            "invalid_user3, invalid_pass3"
//    })

    public void testInvalidLogin(String username, String password) throws InterruptedException {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("url"));
        System.out.print(ConfigReader.getProperty("url"));

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        String errorMsg = driver.findElement(By.cssSelector("[data-test='error']")).getText();

        System.out.print(errorMsg);
        assertTrue(errorMsg.contains("Epic sadface"), "Error message should appear");

        Thread.sleep(3000);
        driver.quit();
    }
}
