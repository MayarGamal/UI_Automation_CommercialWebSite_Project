package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void setDriver() {

        String browser = ConfigReader.getProperty("browser");

        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        webDriver.manage().window().maximize();
        threadLocalDriver.set(webDriver);
    }

    public static void quitDriver() {
        WebDriver currentDriver = threadLocalDriver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            threadLocalDriver.remove();
        }
    }

    public static void closeDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocalDriver.remove();
        }
    }
}
