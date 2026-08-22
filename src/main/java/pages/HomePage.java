package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pages.BasePage.driver;
import static utils.PropertiesReader.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory
                .initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOut;

    public boolean isBtnSignOut() {
        return isElementPresent(btnSignOut);
    }


    public void clickBtnLogin(){
        btnLogin.click();
    }

    public boolean isUrlContainsText(String text){
        try{
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.urlContains(text));
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return false;
    }
}



//    public void method(){
//        WebElement login = driver
//                .findElement(By.xpath("//a[text()='LOGIN']"));
//        login.click();
//        WebElement inputEmail = driver
//                .findElement(By.xpath("//form/input[1]"));
//        inputEmail.sendKeys("ggh@gmail.com");
//    }
//
//    public void ajaxMethod(){
//        btnLogin.click();
//        inputEmail.sendKeys("vvvv@gmail");
//    }


