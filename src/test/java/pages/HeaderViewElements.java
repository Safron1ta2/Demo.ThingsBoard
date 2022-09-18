package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderViewElements extends BasePage {
    public HeaderViewElements(WebDriver driver) {
        super(driver);
    }

    private static final String USERNAME = "//span[@class='tb-user-display-name ng-star-inserted']";
    private static final String USER_AUTHORITY = "//span[@class='tb-user-authority ng-star-inserted']";

    public WebElement username() {
        return waitUntilVisibilityOfElementLocated(USERNAME);
    }

    public WebElement userAuthority() {
        return waitUntilVisibilityOfElementLocated(USER_AUTHORITY);
    }
}
