package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    WebDriver driver;

    By itemsTotalBeforeTax = By.className("summary_subtotal_label");
    By finishButton = By.id("finish");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemsTotalBeforeTax() {
        return driver.findElement(itemsTotalBeforeTax).getText();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }
}
