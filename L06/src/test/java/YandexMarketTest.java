import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class YandexMarketTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(YandexMarketTest.class);

    @Test
    public void test() {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://ya.ru");
        Cookie cookie1 = driver.manage().getCookieNamed("yandex_gid");
        Cookie cookie2 = driver.manage().getCookieNamed("yandexuid");

        driver.get("https://market.yandex.ru/");
//        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list?hid=91491&track=pieces");
//        driver.get("https://market.yandex.ru/catalog--mobilnye-telefony/54726/list?hid=91491&glfilter=16816262%3A16816264&local-offers-first=0&onstock=1&how=aprice");
//        driver.get("https://market.yandex.ru/compare/3jts68QxiMH4WuzW5TFQn9MQaTx?hid=91491&id=401246433&id=217969159");
        logger.info("Зашли на сайт");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("27903768-tab")));
        driver.manage().addCookie(new Cookie("yandex_gid", cookie1.getValue()));
        driver.manage().addCookie(new Cookie("yandexuid", cookie2.getValue()));

        //заходим в раздел "Мобильные телефоны"
        driver.findElement(By.id("27903768-tab")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/catalog--mobilnye-telefony/54726/list?hid=91491']")));
        driver.findElement(By.cssSelector("a[href='/catalog--mobilnye-telefony/54726/list?hid=91491']")).click();
        logger.info("Зашли в раздел \"Мобильные телефоны\"");

        //выбираем только смартфоны и фильтруем по цене
        driver.findElement(By.cssSelector("fieldset[data-autotest-id='16816262'] > ul > li:last-child > div > label")).click();
        driver.findElement(By.linkText("по цене")).click();
        logger.info("Применили фильтры \"по цене\" и \"смартфон\"");

        //добавляем в сравнение
        addToComparison(BrandForComparison.HUAWEI);
        filterByBrand(BrandForComparison.HUAWEI);
        addToComparison(BrandForComparison.XIAOMI);

//        переходим в сравнение
        driver.findElement(By.cssSelector("a[href='/compare?track=head']")).click();
        logger.info("Перешли в сравнение");

        //проверяем что элементов 2
        List<WebElement> comparisonElem = driver.findElements(By.cssSelector(".n-compare-content__line.i-bem.n-compare-content__line_js_inited > div"));
        Assert.assertEquals(comparisonElem.size(), 2);
        logger.info("Проверили что элементов в сравнение 2");

        //открываем "все характеристики" и проверяем что поле "ОПЕРАЦИОННАЯ СИСТЕМА" присутствует
        driver.findElement(By.cssSelector("span.link.n-compare-show-controls__all")).click();
        List<WebElement> compareRowNames = driver.findElements(By.cssSelector(".n-compare-row-name.i-bem"));
        for (WebElement compareRowName : compareRowNames) {
            String rowName = compareRowName.getText();
            if (rowName.equalsIgnoreCase("ОПЕРАЦИОННАЯ СИСТЕМА")) {
                Assert.assertEquals(rowName.toUpperCase(), "ОПЕРАЦИОННАЯ СИСТЕМА");
                logger.info("Во вкладке \"все характеристики\" найдено поле \"ОПЕРАЦИОННАЯ СИСТЕМА\"");
            }
        }

        //открываем "различающиеся характеристики" и проверяем что поле "ОПЕРАЦИОННАЯ СИСТЕМА" отсутствует
        driver.findElement(By.cssSelector("span.link.n-compare-show-controls__diff")).click();
        List<WebElement> compareDiffRowNames = driver.findElements(By.cssSelector(".n-compare-row-name.i-bem"));
        for (WebElement compareRowName : compareDiffRowNames) {
            String rowName = compareRowName.getText();
            if (rowName.equalsIgnoreCase("ОПЕРАЦИОННАЯ СИСТЕМА"))
                logger.error("Во вкладке \"различающиеся характеристики\" найдено поле \"ОПЕРАЦИОННАЯ СИСТЕМА\"!");
            Assert.assertNotEquals(rowName.toUpperCase(), "ОПЕРАЦИОННАЯ СИСТЕМА");
        }
        logger.info("Во вкладке \"различающиеся характеристики\" поле \"ОПЕРАЦИОННАЯ СИСТЕМА\" отсутствует");

        logger.info("Тест пройден");

    }

    private void filterByBrand(BrandForComparison brandForComparison) {
        String fieldSetBrandNames = "fieldset[data-autotest-id='7893318']";
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(fieldSetBrandNames)));
        List<WebElement> brandNames = driver.findElements(By.cssSelector(fieldSetBrandNames + " > ul > li > div > a > label"));
        for (WebElement brandNameElem : brandNames) {
            if (brandNameElem.getText().equalsIgnoreCase(brandForComparison.toString())) {
                brandNameElem.click();
                break;
            }
        }
        logger.info("Применили фильтрацию по бренду " + brandForComparison.toString());
    }

    private void addToComparison(BrandForComparison brandForComparison) {
        if (!brandForComparison.isCompareToThisBrand()) {
            filterByBrand(brandForComparison);
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".preloadable__preloader_visibility_visible")));
            WebElement addToCompareButton = driver.findElement(By.cssSelector(":nth-child(1) > .n-snippet-cell2__hover > div > div > div"));
            new Actions(driver).moveToElement(addToCompareButton).click().perform();
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-informer__title")));
            String popupTitle = driver.findElement(By.className("popup-informer__title")).getText();
            Assert.assertTrue(popupTitle.toUpperCase().contains(brandForComparison.toString()));
            brandForComparison.setCompareToThisBrand(true);
            driver.findElement(By.cssSelector(".popup-informer__close.image.image_name_close")).click();
            logger.info("Смартфон бренда " + brandForComparison.toString() + " добавлен в сравнение");
        }
    }

}
