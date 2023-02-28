import api.client.UserClient;
import api.model.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.ForgotPassword;
import pages.HomePageStellaBurger;
import pages.SignIn;
import pages.SignUp;
import utils.Drivers;

@RunWith(Parameterized.class)
public class TestSignIn {
    private final static WebDriver chromeDriver = Drivers.getChromeDriver();
    private final static WebDriver yandexDriver = Drivers.getYandexDriver();
    private final WebDriver driver;
    private final UserClient userClient = new UserClient();
    private String token;
    private User user;

    public TestSignIn(WebDriver driver, String nameDriver) {
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

    public void checkSignIn(HomePageStellaBurger homePageStellaBurger) {
        SignIn signIn = new SignIn(driver);

        signIn.waitForSignInIsClickable();
        signIn.fillSignInForm(user.getEmail(), user.getPassword());
        homePageStellaBurger.waitForButtonMakeOrder();

        Assert.assertTrue(homePageStellaBurger.buttonMakeOrderIsDisplayed());
    }

    @Before
    public void createUser() {
        user = User.generate();
        ValidatableResponse create = userClient.create(user);
        token = userClient.getUserAccessToken(create);
    }

    @After
    public void cleanUp() {
        if (token != null) {
            userClient.delete(token);
        }
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной Chrome и Яндекс")
    public void loginThroughButtonSingIn() {
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет» Chrome и Яндекс")
    public void loginInThroughPersonalArea() {
        driver.get("https://stellarburgers.nomoreparties.site");

        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        homePageStellaBurger.waitForSignInIsClickable();
        homePageStellaBurger.clickToPersonalAreaButton();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации Chrome и Яндекс")
    public void loginInThroughSignUpForm() {
        driver.get("https://stellarburgers.nomoreparties.site/register");

        SignUp signUp = new SignUp(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        signUp.waitForLoad();
        signUp.clickToSignIn();

        checkSignIn(homePageStellaBurger);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля Chrome и Яндекс")
    public void loginThroughForgotPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        ForgotPassword forgotPassword = new ForgotPassword(driver);
        HomePageStellaBurger homePageStellaBurger = new HomePageStellaBurger(driver);

        forgotPassword.waitLoad();
        forgotPassword.clickForSignInButton();

        checkSignIn(homePageStellaBurger);
    }
}
