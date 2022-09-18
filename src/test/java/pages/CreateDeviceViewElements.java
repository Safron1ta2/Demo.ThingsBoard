package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateDeviceViewElements extends DevicesPageHelper {
    public CreateDeviceViewElements(WebDriver driver) {
        super(driver);
    }

    protected static final String FIELD = "//div[contains(@id,'cdk-overlay')]//%s[@formcontrolname='%s']";
    protected static final String NEXT_BTN = "//span[contains(text(),'Next')]";
    protected static final String CUSTOMER = "//span/span[contains(text(),'%s')]";
    protected static final String ADD_BTN = "//div[@class='mat-dialog-actions']//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']";
    protected static final String ERROR_NAME = "//div[contains(@id,'cdk-overlay')]//input[@formcontrolname='name']/../../..//mat-error";
    protected static final String ERROR_POPUP = "//tb-snack-bar-component/div/div";

    public WebElement nameField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "input", "name"));
    }

    public WebElement labelField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "input", "label"));
    }

    public WebElement descriptionField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "textarea", "description"));
    }

    public WebElement nextBtn() {
        return waitUntilElementToBeClickable(NEXT_BTN);
    }

    public WebElement customerField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "input", "entity"));
    }

    public WebElement customer(String customer) {
        return waitUntilElementToBeClickable(String.format(CUSTOMER, customer));
    }

    public WebElement addBtn() {
        return waitUntilElementToBeClickable(ADD_BTN);
    }

    public WebElement errorName() {
        return waitUntilVisibilityOfElementLocated(ERROR_NAME);
    }

    public WebElement errorPopup() {
        return waitUntilVisibilityOfElementLocated(ERROR_POPUP);
    }
}
