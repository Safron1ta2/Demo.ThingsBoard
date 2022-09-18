package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateDeviceProfilesViewElements extends CreateDeviceViewHelper {
    public CreateDeviceProfilesViewElements(WebDriver driver) {
        super(driver);
    }

    private static final String OPTIONS = "//div[@class='cdk-overlay-pane']//span[contains(text(),'%s')]";

    public WebElement nameField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "input", "name"));
    }

    public WebElement roleField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "input", "ruleChainId"));
    }

    public WebElement dashboardField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "input", "dashboard"));
    }

    public WebElement queueNameField() {
        return waitUntilElementToBeClickable(String.format(FIELD, "input", "queueName"));
    }
    public WebElement option(String role) {
        return waitUntilElementToBeClickable(String.format(OPTIONS, role));
    }

    public WebElement addBtn() {
        return waitUntilElementToBeClickable(ADD_BTN);
    }
}
