package tests;

import utils.BrowserType;
import utils.ConfigManager;
import utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

/**
 * Base test class that handles setup and teardown of WebDriver.
 */
public class BaseTest {
    public WebDriver driver;
    protected String baseUrl = ConfigManager.getProperty("baseUrl");

    /**
     * Sets up the WebDriver before any tests are run.
     *
     * @param browser  The browser type to use for testing.
     * @param headless Whether to run the browser in headless mode.
     */
    @BeforeClass(alwaysRun = true)
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, @Optional("false") String headless) {
        String browserName = (browser != null && !browser.isEmpty()) ? browser : ConfigManager.getProperty("browser");
        boolean isHeadless = (headless != null && !headless.isEmpty()) ? Boolean.parseBoolean(headless) : Boolean.parseBoolean(ConfigManager.getProperty("headless"));

        BrowserType browserType;
        try {
            browserType = BrowserType.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Browser type not supported: " + browserName);
        }
        DriverManager.initializeDriver(browserType, isHeadless);
        driver = DriverManager.getDriver();
        driver.get(baseUrl);

        // Set implicit waits if desired
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigManager.getProperty("implicitWait"))));
    }

    /**
     * Tears down the WebDriver after all tests have run.
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
