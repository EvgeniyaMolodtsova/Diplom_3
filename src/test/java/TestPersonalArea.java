import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestPersonalArea {

    private WebDriver driver;

    private final String page = "https://stellarburgers.nomoreparties.site";

    private String token;

    UserClient userClient = new UserClient();
    User user;

    public void signIn(HomePageStellaBurger homePageStellaBurger, PersonalArea personalArea) {
        SignIn signIn = new SignIn(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToSignIn();
        signIn.waitForSignInIsClickable();
        signIn.fillSignInForm(user.getEmail(), user.getPassword());
        homePageStellaBurger.waitForButtonMakeOrder();
        homePageStellaBurger.clickToPersonalAreaButton();
        personalArea.waitLoad();
    }

    @Before
    public void createUser() {
        user = User.generate();
        ValidatableResponse create = userClient.create(user);
        token = create.extract().path("accessToken");
    }

    @After
    public void cleanUp() {
        if (token != null) {
            userClient.delete(token);
        }
        driver.quit();
    }

    @Test
    @DisplayName("переход в личный кабинет с главной страницы для Chrome")
    public void checkClickPersonalAreaButtonChrome() {
        driver = Drivers.getChromeDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        Assert.assertTrue(personalArea.exitButtonIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор для Chrome")
    public void checkClickConstructorButtonChrome() {
        driver = Drivers.getChromeDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToConstructor();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор по клику на логотип для Chrome")
    public void checkClickLogoChrome() {
        driver = Drivers.getChromeDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToLogo();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("выход из аккаунта для Chrome")
    public void checkClickExitButtonChrome() {
        driver = Drivers.getChromeDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);
        SignIn login = new SignIn(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToExitButton();
        login.waitForSignInIsClickable();

        Assert.assertTrue(login.buttonSignInIsDisplayed());
    }

    @Test
    @DisplayName("переход в личный кабинет с главной страницы для Яндекса")
    public void checkClickPersonalAreaButtonYandex() {
        driver = Drivers.getYandexDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        Assert.assertTrue(personalArea.exitButtonIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор для Яндекса")
    public void checkClickConstructorButtonYandex() {
        driver = Drivers.getYandexDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToConstructor();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор по клику на логотип для Яндекса")
    public void checkClickLogoYandex() {
        driver = Drivers.getYandexDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToLogo();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("выход из аккаунта для Яндекса")
    public void checkClickExitButtonYandex() {
        driver = Drivers.getYandexDriver();
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);
        SignIn login = new SignIn(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToExitButton();
        login.waitForSignInIsClickable();

        Assert.assertTrue(login.buttonSignInIsDisplayed());
    }
}
