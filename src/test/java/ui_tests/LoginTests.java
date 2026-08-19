package ui_tests;

import dto.UserLombock;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import static utils.PropertiesReader.*;



public class LoginTests extends AppManager {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void goToRegistrationLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest() {
        UserLombock user = UserLombock.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(homePage.isBtnSignOut());
    }

    @Test
    public void loginNegativeWrongPasswordTest() {
        UserLombock user = UserLombock.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "wrong_password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }
}
