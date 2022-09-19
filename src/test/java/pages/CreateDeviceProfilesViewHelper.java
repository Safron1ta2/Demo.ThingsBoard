package pages;

import org.openqa.selenium.WebDriver;

public class CreateDeviceProfilesViewHelper extends CreateDeviceProfilesViewElements {
    public CreateDeviceProfilesViewHelper(WebDriver driver) {
        super(driver);
    }

    public CreateDeviceProfilesViewHelper fillInProfileDetails(String name, String role, String dashboard, String queue) {
        nameField().sendKeys(name);
        roleField().click();
        option(role).click();
        dashboardField().click();
        option(dashboard).click();
        queueNameField().click();
        option(queue).click();
        return this;
    }
}
