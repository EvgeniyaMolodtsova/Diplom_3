import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.HomePageStellaBurger;
import utils.Drivers;

@RunWith(Parameterized.class)
public class TestMenu {

    private final static WebDriver chromeDriver = Drivers.getChromeDriver();
    private final static WebDriver yandexDriver = Drivers.getYandexDriver();
    private static final int TIME_OUT = 4000;
    private final WebDriver driver;
    private HomePageStellaBurger homePageStellaBurger;

    public TestMenu(WebDriver driver, String nameDriver) {
        this.driver = driver;
    }

    @Parameterized.Parameters(name = "{index}: driver={1}")
    public static Object[][] getDriver() {
        return new Object[][]{
                {chromeDriver, "Chrome"},
                {yandexDriver, "Yandex"},
        };
    }

    @AfterClass
    public static void cleanUp() {
        chromeDriver.quit();
        yandexDriver.quit();
    }

    public void checkScroll(WebDriver driver) {
        driver.get("https://stellarburgers.nomoreparties.site");

        homePageStellaBurger = new HomePageStellaBurger(driver);
        homePageStellaBurger.waitForSignInIsClickable();

        Assert.assertEquals(0, homePageStellaBurger.getScrollPosition(), 1);
    }

    @Test
    @DisplayName("проверка перехода к начинкам в Chrome и Яндекс")
    public void checkScrollMain() throws InterruptedException {
        checkScroll(driver);

        homePageStellaBurger.clickToMain();

        Thread.sleep(TIME_OUT);
        Assert.assertEquals(homePageStellaBurger.getHeadlineOffset(2), homePageStellaBurger.getScrollPosition(), 1);
    }

    @Test
    @DisplayName("проверка перехода к соусам в Chrome и Яндекс")
    public void checkScrollSouse() throws InterruptedException {
        checkScroll(driver);

        homePageStellaBurger.clickToSouse();

        Thread.sleep(TIME_OUT);

        Assert.assertEquals(homePageStellaBurger.getHeadlineOffset(1), homePageStellaBurger.getScrollPosition(), 1);
    }

    @Test
    @DisplayName("проверка перехода к булкам в Chrome и Яндекс")
    public void checkScrollBuns() throws InterruptedException {
        checkScroll(driver);

        homePageStellaBurger.clickToSouse();

        Thread.sleep(TIME_OUT);

        Assert.assertEquals(homePageStellaBurger.getHeadlineOffset(1), homePageStellaBurger.getScrollPosition(), 1);

        homePageStellaBurger.clickToBuns();

        Thread.sleep(TIME_OUT);
        Assert.assertEquals(homePageStellaBurger.getHeadlineOffset(0), homePageStellaBurger.getScrollPosition(), 1);
    }
}
