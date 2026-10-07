package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import static utils.PropertiesReader.*;

@Listeners(TestNGListener.class)
public class LoginTests extends AppManager {
    LoginPage loginPage;
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void goToRegistrationLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test(groups = {"smoke", "regress", "user", "positive"})
    public void loginPositiveTest() {
        UserLombok user = UserLombok.builder()
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
        UserLombok user = UserLombok.builder()
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
        UserLombok user = UserLombok.builder()
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
        UserLombok user = UserLombok.builder()
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
        UserLombok user = UserLombok.builder()
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
