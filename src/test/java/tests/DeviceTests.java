package tests;

import base.TestInit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.DataProviderCredentials;

public class DeviceTests extends TestInit {
    String url = "http://demo.thingsboard.io/devices";

    @Test(priority = 10, dataProvider = "deviceDetails", dataProviderClass = DataProviderCredentials.class, groups = "positive")
    public void createDeviceFullInfo(String name, String label, String description, String customer) {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        ToolbarViewHelper toolbarView = new ToolbarViewHelper(driver);
        DevicesPageHelper devicesPage = new DevicesPageHelper(driver);
        CreateDeviceViewHelper createDeviceView = new CreateDeviceViewHelper(driver);
        DeviceViewHelper deviceView = new DeviceViewHelper(driver);

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), loginPage.getPassword());
        toolbarView.devices().click();

        devicesPage.setCountBefore();
        devicesPage.addDeviceBtn().click();
        devicesPage.addNewDeviceBtn().click();
        createDeviceView.fillInDeviceDetails(name, label, description)
                .goToCustomerOptional()
                .chooseCustomer(customer)
                .addBtn().click();
        devicesPage.setCountAfter();

        Assert.assertTrue(devicesPage.device(name).isDisplayed());
        Assert.assertEquals(devicesPage.getCountBefore() + 1, devicesPage.getCountAfter());

        devicesPage.device(name).click();
        Assert.assertTrue(deviceView.name(name).isDisplayed());

        deviceView.closeBtn().click();
        devicesPage.deleteBtn(name).click();
        devicesPage.warningPopUpYesBtn().click();
        devicesPage.setCountAfter();

        Assert.assertEquals(devicesPage.getCountBefore(), devicesPage.getCountAfter());
        Assert.assertTrue(isNotElementPresent(String.format(DevicesPageElements.DEVICE, name)));
    }

    @Test(priority = 20, groups = "negative")
    public void createDeviceWithoutName() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        ToolbarViewHelper toolbarView = new ToolbarViewHelper(driver);
        DevicesPageHelper devicesPage = new DevicesPageHelper(driver);
        CreateDeviceViewHelper createDeviceView = new CreateDeviceViewHelper(driver);

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), loginPage.getPassword());
        toolbarView.devices().click();
        devicesPage.addDeviceBtn().click();
        devicesPage.addNewDeviceBtn().click();
        createDeviceView.descriptionField().sendKeys("test");
        createDeviceView.addBtn().click();

        Assert.assertTrue(createDeviceView.errorName().isDisplayed());
        Assert.assertEquals(createDeviceView.errorName().getText(), "Название обязательно.");
    }

    @Test(priority = 20, groups = "negative")
    public void createDeviceProfileWithSameName() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        ToolbarViewHelper toolbarView = new ToolbarViewHelper(driver);
        DevicesPageHelper devicesPage = new DevicesPageHelper(driver);
        CreateDeviceViewHelper createDeviceView = new CreateDeviceViewHelper(driver);

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), loginPage.getPassword());
        toolbarView.devices().click();
        devicesPage.addDeviceBtn().click();
        devicesPage.addNewDeviceBtn().click();
        createDeviceView.nameField().sendKeys("Charging Port 2");
        createDeviceView.addBtn().click();

        Assert.assertTrue(createDeviceView.errorPopup().isDisplayed());
        Assert.assertEquals(createDeviceView.errorPopup().getText(), "Device with such name already exists!");
    }
}
