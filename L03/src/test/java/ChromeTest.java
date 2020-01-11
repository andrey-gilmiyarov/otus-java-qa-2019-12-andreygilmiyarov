import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class ChromeTest {

    private static final Logger logger = LogManager.getLogger(ChromeTest.class);
    private WebDriver driver;
    private String site = "https://otus.ru/";

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        logger.info("Setup is completed.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver is teardown.");
    }

    @Test
    public void test() {
        driver.get(site);
        logger.info("Site " + site + " is open.");
    }

}
