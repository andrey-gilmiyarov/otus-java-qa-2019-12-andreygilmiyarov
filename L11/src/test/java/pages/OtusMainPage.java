package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtusMainPage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(OtusMainPage.class);

    public static final String URL = "https://otus.ru";
    private By loginButton = By.cssSelector("button[data-modal-id='new-log-reg']");
    private By user = By.cssSelector(".header2__right > .header2-menu");
    private By personalArea = By.cssSelector("a[title='Личный кабинет']");
    private By logOut = By.cssSelector("a[title='Выход']");

    public OtusMainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public OtusMainPage open() {
        driver.get(URL);
        logger.info("Open site " + URL);
        return this;
    }

    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        logger.info("Click to login button");
        return new LoginPage(driver, wait);
    }

    public PesronalInfoPage goToPersonalInfo() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(user));
        WebElement userName = driver.findElement(user);
        WebElement personalAreaElem = driver.findElement(personalArea);
        Actions actions = new Actions(driver).moveToElement(userName);
        actions.moveToElement(personalAreaElem).click().perform();
        logger.info("Go to personal info page.");
        return new PesronalInfoPage(driver, wait);
    }

    public void logOut() {
        WebElement userName = driver.findElement(user);
        WebElement logOutElem = driver.findElement(logOut);
        Actions actions = new Actions(driver).moveToElement(userName);
        actions.moveToElement(logOutElem).click().perform();
        logger.info("Logout from site");
    }

}
