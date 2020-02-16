package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PesronalInfoPage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(PesronalInfoPage.class);

    private By personal = By.cssSelector(".nav__items > a[title='О себе']");

    private By fname = By.id("id_fname");
    private By lname = By.id("id_lname");
    private By blogName = By.id("id_blog_name");
    private By datepicker = By.cssSelector("input[data-toggle='datepicker']");

//    private By addContact = By.linkText("Добавить");
//    private By contact2 = By.cssSelector("contact-2-service");
//    private By contact3 = By.cssSelector("contact-3-service");

    //    private By contact = By.cssSelector("label > div.lk-cv-block__input");

    private By save = By.cssSelector("button[name='continue']");

    public PesronalInfoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public PesronalInfoPage getPersonalInfo() {
        driver.findElement(personal).click();
        logger.info("Go to \"О себе\" page.");
        return this;
    }

    public PesronalInfoPage addPersonalInfo(String fname, String lname, String blogName, String birthDate) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(this.fname));

        driver.findElement(this.fname).clear();
        driver.findElement(this.lname).clear();
        driver.findElement(this.blogName).clear();
        driver.findElement(this.datepicker).clear();

        driver.findElement(this.fname).sendKeys(fname);
        driver.findElement(this.lname).sendKeys(lname);
        driver.findElement(this.blogName).sendKeys(blogName);
        driver.findElement(this.datepicker).sendKeys(birthDate);

        logger.info("Add some personal info.");
        return this;
    }

//    public void addContactInfo() {
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("document.getElementsByName(" + contact2 + ")[0].setAttribute('type', 'visible');");
//        driver.findElement(contact2).sendKeys();
//    }

    public void saveInfo() {
        driver.findElement(save).click();
        logger.info("Save personal info.");
    }

    public String getFname() {
        return driver.findElement(fname).getAttribute("value");
    }

    public String getLname() {
        return driver.findElement(lname).getAttribute("value");
    }

    public String getBlogName() {
        return driver.findElement(blogName).getAttribute("value");
    }

    public String getBirthDate() {
        return driver.findElement(datepicker).getAttribute("value");
    }
}
