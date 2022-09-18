package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeviceViewElements extends BasePage {
    public DeviceViewElements(WebDriver driver) {
        super(driver);
    }

    private static final String DEVISE_NAME = "//div[@fxlayoutalign='start center']/span[contains(text(),'%s')]";
    private static final String CLOSE_BTN = "//mat-icon[contains(text(),'close')]//..";

    public WebElement name(String name) {
        return waitUntilElementToBeClickable(String.format(DEVISE_NAME, name));
    }

    public WebElement closeBtn() {
        return waitUntilElementToBeClickable(CLOSE_BTN);
    }
}
