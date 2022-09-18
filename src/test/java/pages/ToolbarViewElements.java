package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ToolbarViewElements extends BasePage {
    public ToolbarViewElements(WebDriver driver) {
        super(driver);
    }

    private static final String DEVICES = "//a[@href='/devices']";
    private static final String DEVICES_PROFILES = "//a[@href='/deviceProfiles']";

    public WebElement devices() {
        return waitUntilElementToBeClickable(DEVICES);
    }

    public WebElement deviceProfiles() {
        return waitUntilElementToBeClickable(DEVICES_PROFILES);
    }
}
