package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeviceProfilesPageElements extends DevicesPageHelper{
    public DeviceProfilesPageElements(WebDriver driver) {
        super(driver);
    }

    public static final String DEVICE_PROFILE = "//mat-row//span[contains(text(),'%s')]";

    public WebElement deviceProfile(String deviceOptional) {
        return waitUntilVisibilityOfElementLocated(String.format(DEVICE_PROFILE, deviceOptional));
    }
}
