package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.OtusMainPage;
import pages.PesronalInfoPage;

import java.util.concurrent.TimeUnit;

public class OtusTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(OtusTest.class);

    private String name = "Name";
    private String lname = "LastName";
    private String birthDate = "16.02.2020";
    private String country = "Россия";
    private String city = "Москва";

    @Parameters({"username", "password"})
    @Test
    public void test(String username, String password) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        OtusMainPage otusMainPage = new OtusMainPage(driver, wait);
        otusMainPage.open().clickLoginButton().login(username, password);
        otusMainPage.goToPersonalInfo()
                .getPersonalInfo()
                .addLocationInfo()
                .addPersonalInfo(name, lname, name, birthDate)
                .saveInfo();
        otusMainPage.logOut();

        otusMainPage.open().clickLoginButton().login(username, password);
        PesronalInfoPage pesronalInfoPage = otusMainPage.goToPersonalInfo()
                .getPersonalInfo();

        Assert.assertEquals(pesronalInfoPage.getFname(), name, "First name is not equals!");
        Assert.assertEquals(pesronalInfoPage.getLname(), lname, "Last name is not equals!");
        Assert.assertEquals(pesronalInfoPage.getBlogName(), name, "Blog name is not equals!");
        Assert.assertEquals(pesronalInfoPage.getBirthDate(), birthDate, "Birth date is not equals!");
        Assert.assertEquals(pesronalInfoPage.getExpectedCountry(), country, "Country is not equals!");
        Assert.assertEquals(pesronalInfoPage.getExpectedCity(), city, "City is not equals!");

        logger.info("Test completed.");

    }

}
