package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium  .WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver(BrowserType browserType) {
        initializeDriver(browserType, false); // Default headless is false
    }

    public static void initializeDriver(BrowserType browserType, boolean headless) {
        if (driver.get() == null) {
            switch (browserType) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless");
                    }
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) {
                        edgeOptions.addArguments("--headless");
                    }
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                case SAFARI:
                    // SafariDriver does not support headless mode
                    driver.set(new org.openqa.selenium.safari.SafariDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Browser type not supported: " + browserType);
            }
            driver.get().manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver not initialized. Call initializeDriver() first.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
