import api.client.UserClient;
import api.model.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.HomePageStellaBurger;
import pages.PersonalArea;
import pages.SignIn;
import utils.Drivers;

@RunWith(Parameterized.class)
public class TestPersonalArea {
    private final static WebDriver chromeDriver = Drivers.getChromeDriver();
    private final static WebDriver yandexDriver = Drivers.getYandexDriver();
    private final WebDriver driver;

    private final String page = "https://stellarburgers.nomoreparties.site";
    UserClient userClient = new UserClient();
    User user;
    private String token;

    public TestPersonalArea(WebDriver driver, String nameDriver) {
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
    public static void close() {
        chromeDriver.quit();
        yandexDriver.quit();
    }

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
    }

    @Test
    @DisplayName("переход в личный кабинет с главной страницы для Chrome и Яндекса")
    public void checkClickPersonalAreaButton() {
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        Assert.assertTrue(personalArea.exitButtonIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор для Chrome и Яндекса")
    public void checkClickConstructorButton() {
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToConstructor();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("переход из личного кабинета в конструктор по клику на логотип для Chrome и Яндекса")
    public void checkClickLogo() {
        driver.get(page);

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);
        PersonalArea personalArea = new PersonalArea(driver);

        signIn(homePageStellaBurger, personalArea);

        personalArea.clickToLogo();
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Test
    @DisplayName("выход из аккаунта")
    public void checkClickExitButton() {
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
