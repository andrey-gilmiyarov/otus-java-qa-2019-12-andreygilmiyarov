import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class YandexMarketTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(YandexMarketTest.class);

    @Test
    public void test() throws InterruptedException {
        driver.get("https://ya.ru");
        driver.get("https://market.yandex.ru/");
//        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list?hid=91491&track=pieces");

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        //заходим в раздел "Мобильные телефоны"
        driver.findElement(By.cssSelector(".n-w-tab__control-hamburger")).click();
        driver.findElement(By.cssSelector(".n-w-tabs__vertical-tabs > div:first-child")).click();
        driver.findElement(By.cssSelector("a[href='/catalog--mobilnye-telefony/54726/list?hid=91491']")).click();

        //указываем фильтры и сортируем по цене
        String fieldSetBrandNames = "fieldset[data-autotest-id='7893318']";
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(fieldSetBrandNames)));
        driver.findElement(By.cssSelector(fieldSetBrandNames + " > ul > li:nth-child(3) > div > a > label")).click();
        driver.findElement(By.cssSelector(fieldSetBrandNames + " > ul > li:nth-last-child(2) > div > a > label")).click();
        driver.findElement(By.cssSelector("fieldset[data-autotest-id='16816262'] > ul > li:last-child > div > label")).click();
        driver.findElement(By.linkText("по цене")).click();

//        String divWithPhones = "div[class='n-snippet-cell2 i-bem b-spy-events b-zone b-spy-visible n-snippet-cell2_type_product b-spy-visible_js_inited b-zone_js_inited n-snippet-cell2_js_inited b-spy-events_js_inited']";
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(divWithPhones)));
//        List<WebElement> phones = driver.findElements(By.cssSelector(divWithPhones));
//        List<WebElement> brandNames = driver.findElements(By.cssSelector("div[class='n-snippet-cell2__brand-name']"));
//        logger.info(brandName);
//        boolean findXiaomi = false;
//        boolean findHuawei = false;
//        for (WebElement brandNameElement : brandNames) {
//            String brandName = brandNameElement.getText().toLowerCase();
//            if (brandName.equals("xiaomi")) {
//                logger.info("find " + brandName);
//                Actions actions = new Actions(driver);
//                WebElement element = driver.findElement(By.cssSelector("i[class='image image_name_compare']"));
//                actions.moveToElement(element).build().perform();
//                driver.findElement(By.cssSelector("i[class='image image_name_compare']")).click();
//                WebElement element = driver.findElement(By.cssSelector("");
//                WebElement element = driver.findElement(By.cssSelector("div[class='n-product-toolbar__item link link_theme_minor hint n-user-lists_type_compare i-bem n-user-lists_type_compare_in-list_no n-user-lists_type_compare_js_inited hint_js_inited _popup2-destructor _popup2-destructor_js_inited link_js_inited ']"));
//                logger.info(element.getText());
//            }
//        }



//        for (WebElement phone : phones) {
//            String brandName = phone.findElement(By.cssSelector("div[class='n-snippet-cell2__brand-name']")).getText().toLowerCase();
//            if (brandName.equals("xiaomi")) {
//                logger.info("find " + brandName);
//                Actions actions = new Actions(driver);
//                actions.moveToElement(phone).build().perform();
//                WebElement element = driver.findElement(By.cssSelector("div[class='n-product-toolbar__item link link_theme_minor hint n-user-lists_type_compare i-bem n-user-lists_type_compare_in-list_no n-user-lists_type_compare_js_inited hint_js_inited _popup2-destructor _popup2-destructor_js_inited link_js_inited']"));
//                logger.info(element.getText());
//            }
//        }

        Thread.sleep(10000);

    }

}
