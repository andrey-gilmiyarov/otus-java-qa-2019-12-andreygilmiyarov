package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private By email = By.cssSelector("div > input[name='email']");
    private By password = By.cssSelector("div > input[name='password']");
    private By submit = By.cssSelector("div > button[type='submit']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void login(String login, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(email));
        driver.findElement(email).sendKeys(login);
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.password));
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(submit).click();
        logger.info("Login into site.");
    }

}
