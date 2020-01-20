import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver create(String webDriverName) {
        WebDriver driver;
        if (webDriverName.toUpperCase().equals(BrowserDriver.CHROME.toString())) {
            driver = new ChromeDriver();
            logger.info("ChromeDriver created.");
        } else {
            driver = new FirefoxDriver();
            logger.info("FirefoxDriver created.");
        }
        return driver;
    }

}
