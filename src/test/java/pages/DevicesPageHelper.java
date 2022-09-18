package pages;

import org.openqa.selenium.WebDriver;

public class DevicesPageHelper extends DevicesPageElements {
    public DevicesPageHelper(WebDriver driver) {
        super(driver);
    }

    private int getDevicesCount() {
        String[] i = devices().getText().split(" ");
        return Integer.parseInt(i[i.length - 1]);
    }

    private int devicesCountAfter;
    private int devicesCountBefore;

    public void setCountAfter() {
        sleep(3);
        devicesCountAfter = getDevicesCount();
    }

    public int getCountAfter() {
        return devicesCountAfter;
    }

    public void setCountBefore() {
        devicesCountBefore = getDevicesCount();
    }

    public int getCountBefore() {
        return devicesCountBefore;
    }
}
