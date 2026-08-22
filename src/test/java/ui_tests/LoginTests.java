package ui_tests;

import dto.UserLombock;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import static utils.PropertiesReader.*;
import static utils.UserFactory.*;


public class LoginTests extends AppManager {
    LoginPage loginPage;
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

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
        softAssert.assertTrue(homePage.isBtnSignOut(),
                "validateSignOutBtn");
        softAssert.assertTrue(homePage.isUrlContainsText("contacts"),
                "validateUrl");
        softAssert.assertAll();

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

    @Test
    public void loginNegativeWrongEmailTest() {
        UserLombock user = UserLombock.builder()
                .username(getProperty("base.properties", "wrong_email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }

    @Test
    public void loginNegativeEmptyPasswordTest() {
        UserLombock user = UserLombock.builder()
                .username(getProperty("base.properties", "email"))
                .password("")
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }

    @Test
    public void loginNegativeEmptyEmailTest() {
        UserLombock user = UserLombock.builder()
                .username("")
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }

    @Test
    public void loginNegativeEmptyFieldsTest() {
        loginPage.clickBtnLogin();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password"));
    }

}
