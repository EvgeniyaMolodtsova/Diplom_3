import io.qameta.allure.junit4.DisplayName;
import utils.Drivers;
import pages.HomePageStellaBurger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestMenu {

    private WebDriver driver;

    private HomePageStellaBurger homePageStellaBurger;

    private static final int TIME_OUT = 4000;

    public void checkScroll(WebDriver driver) throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site");

        homePageStellaBurger = new HomePageStellaBurger(driver);
        homePageStellaBurger.waitForSignInIsClickable();

        Assert.assertEquals(0, homePageStellaBurger.getScrollPosition(), 1);

        homePageStellaBurger.clickToMain();

        Thread.sleep(TIME_OUT);
        Assert.assertEquals(homePageStellaBurger.getHeadlineOffset(2), homePageStellaBurger.getScrollPosition(), 1);

        homePageStellaBurger.clickToSouse();

        Thread.sleep(TIME_OUT);

        Assert.assertEquals(homePageStellaBurger.getHeadlineOffset(1), homePageStellaBurger.getScrollPosition(), 1);


        homePageStellaBurger.clickToBuns();

        Thread.sleep(TIME_OUT);
        Assert.assertEquals(homePageStellaBurger.getHeadlineOffset(0), homePageStellaBurger.getScrollPosition(), 1);
    }


    @Test
    @DisplayName("проверка перехода к начинкам, соусам и булкам в Chrome")
    public void checkScrollChrome() throws InterruptedException {
        driver = Drivers.getChromeDriver();

        checkScroll(driver);
    }

    @Test
    @DisplayName("проверка перехода к начинкам, соусам и булкам в Яндекс")
    public void checkScrollYandex() throws InterruptedException {
        driver = Drivers.getYandexDriver();

        checkScroll(driver);
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}
