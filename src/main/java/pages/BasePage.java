package pages;

import manager.AppManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    static WebDriver driver;

    public void setDriver(WebDriver wd){
        driver = wd;
    }

    public Logger logger = LoggerFactory.getLogger(BasePage.class);

    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e){
//            e.printStackTrace();
//            System.out.println("created exception");
            logger.error("Created exception", e);
        }
        return false;
    }

    public String closeAlert() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean isElementPresent(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
