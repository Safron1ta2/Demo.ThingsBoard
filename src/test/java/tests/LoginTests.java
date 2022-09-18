package tests;

import base.TestInit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HeaderViewHelper;
import pages.LoginPageHelper;

import java.io.IOException;

public class LoginTests extends TestInit {
    String url = "http://demo.thingsboard.io/login";

    @Test(priority = 10, groups = "positive")
    public void loginPositiveTest() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        HeaderViewHelper headerView = new HeaderViewHelper(driver);

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), loginPage.getPassword());

        Assert.assertTrue(headerView.username().isDisplayed());
        Assert.assertTrue(headerView.userAuthority().isDisplayed());
        Assert.assertTrue(headerView.urlContains("home"));
    }

    @Test(priority = 20, groups = "negative")
    public void loginNegativeFormatEmailTest() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        String invalidFormatEmail = loginPage.getEmail().replaceAll("@", "");

        openUrl(url);
        loginPage.authorization(invalidFormatEmail, loginPage.getPassword());

        Assert.assertTrue(loginPage.invalidEmailError().isDisplayed());
        Assert.assertTrue(loginPage.invalidEmailError().getText().contains("Неправильный формат эл. адреса"));
        Assert.assertTrue(loginPage.urlContains("login"));
    }

    @Test(priority = 20, groups = "negative")
    public void loginNegativeEmailTest() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        String invalidEmail = loginPage.getEmail().replace("t", "s");

        openUrl(url);
        loginPage.authorization(invalidEmail, loginPage.getPassword())
                .assertions("Invalid username or password", "login");
    }

    @Test(priority = 20, groups = "negative")
    public void loginNegativePasswordTest() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        String invalidPassword = loginPage.getPassword().replace("Q", "s");

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), invalidPassword)
                .assertions("Invalid username or password", "login");
    }

    @Test(priority = 20, groups = "negative")
    public void loginWithoutPasswordTest() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);

        openUrl(url);
        loginPage.emailField().sendKeys(loginPage.getPassword());
        loginPage.submitBtn().click();
        loginPage.assertions("Authentication failed", "login");
    }
}
