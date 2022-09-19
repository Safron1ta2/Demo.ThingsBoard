package tests;

import base.TestInit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.DataProviderCredentials;

public class DeviceProfilesTests extends TestInit {
    String url = "http://demo.thingsboard.io/deviceProfiles";

    @Test(dataProvider = "deviceProfileDetails", dataProviderClass = DataProviderCredentials.class, groups = "positive")
    public void createProfile0FullInfo(String name, String role, String dashboard, String queue) {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        ToolbarViewHelper toolbarView = new ToolbarViewHelper(driver);
        CreateDeviceProfilesViewHelper createDeviceProfilesView = new CreateDeviceProfilesViewHelper(driver);
        DeviceProfilesPageElements deviceProfilesPage = new DeviceProfilesPageElements(driver);
        DeviceProfileViewElements deviceProfileView = new DeviceProfileViewElements(driver);

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), loginPage.getPassword());
        toolbarView.deviceProfiles().click();
        deviceProfilesPage.setCountBefore();
        deviceProfilesPage.addDeviceBtn().click();
        deviceProfilesPage.addNewDeviceBtn().click();
        createDeviceProfilesView.fillInProfileDetails(name, role, dashboard, queue).addBtn().click();
        deviceProfilesPage.setCountAfter();

        Assert.assertTrue(deviceProfilesPage.deviceProfile(name).isDisplayed());
        Assert.assertEquals(deviceProfilesPage.getCountBefore() + 1, deviceProfilesPage.getCountAfter());

        deviceProfilesPage.deviceProfile(name).click();
        Assert.assertTrue(deviceProfileView.name(name).isDisplayed());

        deviceProfileView.closeBtn().click();
        deviceProfilesPage.deleteBtn(name).click();
        deviceProfilesPage.warningPopUpYesBtn().click();
        deviceProfilesPage.setCountAfter();

        Assert.assertEquals(deviceProfilesPage.getCountBefore(), deviceProfilesPage.getCountAfter());
        Assert.assertTrue(isNotElementPresent(String.format(DeviceProfilesPageElements.DEVICE_PROFILE, name)));
    }

    @Test(priority = 20, groups = "negative")
    public void createDeviceProfileWithoutName() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        ToolbarViewHelper toolbarView = new ToolbarViewHelper(driver);
        DeviceProfilesPageElements deviceProfilesPage = new DeviceProfilesPageElements(driver);
        CreateDeviceProfilesViewHelper createDeviceProfilesView = new CreateDeviceProfilesViewHelper(driver);

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), loginPage.getPassword());
        toolbarView.deviceProfiles().click();
        deviceProfilesPage.addDeviceBtn().click();
        deviceProfilesPage.addNewDeviceBtn().click();
        createDeviceProfilesView.roleField().click();
        createDeviceProfilesView.option("Air Quality Sensors").click();
        createDeviceProfilesView.addBtn().click();

        Assert.assertTrue(createDeviceProfilesView.errorName().isDisplayed());
        Assert.assertEquals(createDeviceProfilesView.errorName().getText(), "Name is required.");
    }

    @Test(priority = 20, groups = "negative")
    public void createDeviceProfileWithSameName() {
        LoginPageHelper loginPage = new LoginPageHelper(driver);
        ToolbarViewHelper toolbarView = new ToolbarViewHelper(driver);
        DeviceProfilesPageElements deviceProfilesPage = new DeviceProfilesPageElements(driver);
        CreateDeviceProfilesViewHelper createDeviceProfilesView = new CreateDeviceProfilesViewHelper(driver);

        openUrl(url);
        loginPage.authorization(loginPage.getEmail(), loginPage.getPassword());
        toolbarView.deviceProfiles().click();
        deviceProfilesPage.addDeviceBtn().click();
        deviceProfilesPage.addNewDeviceBtn().click();
        createDeviceProfilesView.nameField().sendKeys("Charging port");
        createDeviceProfilesView.addBtn().click();

        Assert.assertTrue(createDeviceProfilesView.errorPopup().isDisplayed());
        Assert.assertEquals(createDeviceProfilesView.errorPopup().getText(), "Device profile with such name already exists!");
    }
}