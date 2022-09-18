package pages;

import org.openqa.selenium.WebDriver;

public class CreateDeviceViewHelper extends CreateDeviceViewElements {
    public CreateDeviceViewHelper(WebDriver driver) {
        super(driver);
    }

    public CreateDeviceViewHelper fillInDeviceDetails(String name, String label, String description) {
        nameField().sendKeys(name);
        labelField().sendKeys(label);
        descriptionField().sendKeys(description);
        return this;
    }

    public CreateDeviceViewHelper goToCustomerOptional() {
        doubleClick(nextBtn());
        return this;
    }

    public CreateDeviceViewHelper chooseCustomer(String customer) {
        customerField().click();
        customer(customer).click();
        return this;
    }
}
