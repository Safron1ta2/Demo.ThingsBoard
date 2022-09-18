package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageHelper extends LoginPageElements {
    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    private final String email = "test.user.thigs@gmail.com";
    private final String password = "CqJGdaqhDF9@guQ";

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return password;
    }

    public LoginPageHelper authorization(String email, String password) {
        emailField().sendKeys(email);
        passwordField().sendKeys(password);
        submitBtn().click();
        return this;
    }

    public void assertions(String errorMessage, String urlPath) {
        Assert.assertTrue(errorPopup().isDisplayed());
        Assert.assertEquals(errorPopup().getText(), errorMessage);
        Assert.assertTrue(urlContains(urlPath));
    }
}
