package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageElements extends BasePage {
    public LoginPageElements(WebDriver driver) {
        super(driver);
    }

    private static final String EMAIL_FIELD = "//input[@id='username-input']";
    private static final String PASSWORD_FIELD = "//input[@id='password-input']";
    private static final String SUBMIT_BTN = "//button[@type='submit']";
    private static final String INVALID_EMAIL_ERROR = "//mat-error";
    private static final String ERROR_POPUP = "//div[@fxlayoutalign='start center']//div";

    public WebElement emailField(){
        return waitUntilElementToBeClickable(EMAIL_FIELD);
    }

    public WebElement passwordField(){
        return waitUntilElementToBeClickable(PASSWORD_FIELD);
    }

    public WebElement submitBtn(){
        return waitUntilElementToBeClickable(SUBMIT_BTN);
    }
    public WebElement invalidEmailError(){
        return waitUntilVisibilityOfElementLocated(INVALID_EMAIL_ERROR);
    }

    public WebElement errorPopup(){
        return waitUntilVisibilityOfElementLocated(ERROR_POPUP);
    }
}
