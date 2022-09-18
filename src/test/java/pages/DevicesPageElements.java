package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DevicesPageElements extends BasePage {
    public DevicesPageElements(WebDriver driver) {
        super(driver);
    }

    private static final String ADD_DEVICE_BTN = "//mat-icon[contains(text(),'add')]/..";
    private static final String ADD_NEW_DEVICE_BTN = "//button[@role='menuitem'][1]";
    public static final String DEVICE = "//mat-row//span[contains(text(),'%s')]";
    private static final String DEVICES_COUNT = "//div[@class='mat-paginator-range-label']";
    private static final String DELETE_BTN = DEVICE + "/../..//mat-icon[contains(text(),' delete')]";
    private static final String WARNING_DELETE_POPUP_YES = "//tb-confirm-dialog//button[2]";

    public WebElement addDeviceBtn() {
        return waitUntilElementToBeClickable(ADD_DEVICE_BTN);
    }

    public WebElement addNewDeviceBtn() {
        return waitUntilElementToBeClickable(ADD_NEW_DEVICE_BTN);
    }

    public WebElement device(String deviceOptional) {
        return waitUntilVisibilityOfElementLocated(String.format(DEVICE, deviceOptional));
    }

    public WebElement devices() {
        return waitUntilElementToBeClickable(DEVICES_COUNT);
    }

    public WebElement deleteBtn(String deviceOptional) {
        sleep(1);
        return waitUntilElementToBeClickable(String.format(DELETE_BTN, deviceOptional));
    }

    public WebElement warningPopUpYesBtn() {
        return waitUntilVisibilityOfElementLocated(WARNING_DELETE_POPUP_YES);
    }
}
