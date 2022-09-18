package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

abstract public class TestInit extends Base {
    protected WebDriver driver;
    private final Logger log = LogManager.getLogger(TestInit.class);

    Dimension dimension = new Dimension(WIDTH, HEIGHT);

    private static final int WIDTH = 1680;
    private static final int HEIGHT = 1050;
    private static final boolean HEADLESS = false;

    @Parameters({"browser"})
    @BeforeMethod
    public void openBrowser(@Optional("chrome") String browser) throws Exception {
        log.info("Setup driver");
        log.info("Starting " + browser + " driver");
        switch (browser) {
            case "chrome" -> {
                if (HEADLESS == true) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--headless");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                } else {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            case "firefox" -> {
                if (HEADLESS == true) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--headless");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(options);
                } else {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new ChromeDriver();
                }
            }
            default -> throw new Exception("You chose not valid browser!");
        }
        driver.manage().window().setSize(dimension);
    }

    @AfterMethod
    public void closeBrowser() {
        log.info("Teardown");
        driver.quit();
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public boolean isNotElementPresent(String locator) {
        try {
            driver.findElement(By.xpath(locator));
            return false;
        } catch (WebDriverException e) {
            return true;
        }
    }
}

